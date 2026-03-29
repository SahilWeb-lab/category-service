package com.ecom.service;

import org.springframework.data.domain.Page;

import com.ecom.dto.CategoryRequest;
import com.ecom.dto.CategoryResponse;

public interface CategoryService {

	CategoryResponse createCategory(CategoryRequest request);

	CategoryResponse updateCategory(Long id, CategoryRequest request);

	CategoryResponse getCategoryByIdForUser(Long id);

	CategoryResponse getCategoryByIdForAdmin(Long id);

	public Page<CategoryResponse> getAllCategoriesForAdmin(Integer pageNo, Integer pageSize, String sortBy,
			String sortDir, String status);

	public Page<CategoryResponse> getAllCategoriesForUser(Integer pageNo, Integer pageSize, String sortBy,
			String sortDir);

	void deleteCategory(Long id); // soft delete

	void activateCategory(Long id);

	void deactivateCategory(Long id);

	public Page<CategoryResponse> getCategoriesByParentIdForAdmin(Long parentId, Integer pageNo, Integer pageSize,
			String sortBy, String sortDir);

	public Page<CategoryResponse> getCategoriesByParentIdForUser(Long parentId, Integer pageNo, Integer pageSize,
			String sortBy, String sortDir);

	public Page<CategoryResponse> searchCategories(String keyword, Integer pageNo, Integer pageSize, String sortBy,
			String sortDir, Boolean status);

}
