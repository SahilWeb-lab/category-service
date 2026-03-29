package com.ecom.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dto.CategoryRequest;
import com.ecom.dto.CategoryResponse;
import com.ecom.exception.CategoryNotFoundException;
import com.ecom.exception.ResourceStateException;
import com.ecom.mapper.CategoryMapper;
import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.service.CategoryService;
import com.ecom.util.PageableUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Override
	@Transactional
	public CategoryResponse createCategory(CategoryRequest request) {

		if (request.getParentId() != null)
			categoryExistsById(request.getParentId());

		Category category = categoryMapper.toEntity(request);
		categoryRepository.save(category);
		return categoryMapper.toDTO(category);
	}

	@Override
	@Transactional
	public CategoryResponse updateCategory(Long id, CategoryRequest request) {

		if (request.getParentId() != null)
			categoryExistsById(request.getParentId());

		Category existingCategory = categoryExistsById(id);
		existingCategory.setDescription(request.getDescription());
		existingCategory.setName(request.getName());
		existingCategory.setParentId(request.getParentId());
		existingCategory.setStatus(request.getStatus());
		existingCategory.setUpdatedAt(LocalDateTime.now());

		Category updatedCategory = categoryRepository.save(existingCategory);

		return categoryMapper.toDTO(updatedCategory);
	}

	@Override
	public CategoryResponse getCategoryByIdForUser(Long id) {
		Category category = categoryRepository.findByIdAndStatusTrue(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id : [" + id + "]"));
		return categoryMapper.toDTO(category);
	}

	@Override
	public CategoryResponse getCategoryByIdForAdmin(Long id) {
		Category category = categoryExistsById(id);
		return categoryMapper.toDTO(category);
	}

	@Override
	public Page<CategoryResponse> getAllCategoriesForUser(Integer pageNo, Integer pageSize, String sortBy,
			String sortDir) {

		Pageable pageable = PageableUtil.createPageable(pageNo, pageSize, sortBy, sortDir);

		Page<Category> categoryPage = categoryRepository.findByStatusTrue(pageable);
		return categoryPage.map(categoryMapper::toDTO);
	}

	@Override
	public Page<CategoryResponse> getAllCategoriesForAdmin(Integer pageNo, Integer pageSize, String sortBy,
			String sortDir, String status) {

		Pageable pageable = PageableUtil.createPageable(pageNo, pageSize, sortBy, sortDir);

		Page<Category> categoryPage = null;

		if (status.equals("active"))
			categoryPage = categoryRepository.findByStatusTrue(pageable);
		else if (status.equals("inactive"))
			categoryPage = categoryRepository.findByStatusFalse(pageable);
		else
			categoryPage = categoryRepository.findAll(pageable);

		return categoryPage.map(categoryMapper::toDTO);
	}

	@Override
	@Transactional
	public void deleteCategory(Long id) {
		categoryExistsById(id);
		categoryRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void activateCategory(Long id) {
		Category existingCategory = categoryExistsById(id);
		if (existingCategory.getStatus())
			throw new ResourceStateException("Category already activated!");

		existingCategory.setStatus(true);
		categoryRepository.save(existingCategory);
	}

	@Override
	@Transactional
	public void deactivateCategory(Long id) {
		Category existingCategory = categoryExistsById(id);
		if (!existingCategory.getStatus())
			throw new ResourceStateException("Category already deactivated!");

		existingCategory.setStatus(false);
		categoryRepository.save(existingCategory);
	}

	@Override
	public Page<CategoryResponse> getCategoriesByParentIdForAdmin(Long parentId, Integer pageNo, Integer pageSize,
			String sortBy, String sortDir) {

		Pageable pageable = PageableUtil.createPageable(pageNo, pageSize, sortBy, sortDir);
		Page<Category> categoryPage = categoryRepository.findByParentId(parentId, pageable);
		return categoryPage.map(categoryMapper::toDTO);
	}

	@Override
	public Page<CategoryResponse> getCategoriesByParentIdForUser(Long parentId, Integer pageNo, Integer pageSize,
			String sortBy, String sortDir) {
		Pageable pageable = PageableUtil.createPageable(pageNo, pageSize, sortBy, sortDir);
		Page<Category> categoryPage = categoryRepository.findByParentIdAndStatusTrue(parentId, pageable);
		return categoryPage.map(categoryMapper::toDTO);
	}

	@Override
	public Page<CategoryResponse> searchCategories(String keyword, Integer pageNo, Integer pageSize, String sortBy,
			String sortDir, Boolean status) {
		Pageable pageable = PageableUtil.createPageable(pageNo, pageSize, sortBy, sortDir);
		Page<Category> categoryPage = categoryRepository.searchCategories(keyword, status, pageable);
		return categoryPage.map(categoryMapper::toDTO);
	}

	private Category categoryExistsById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id : [" + id + "]"));
	}

}
