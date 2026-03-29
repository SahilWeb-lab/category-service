package com.ecom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecom.dto.CategoryRequest;
import com.ecom.dto.CategoryResponse;

public interface CategoryService {

	CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    CategoryResponse getCategoryById(Long id);

    public Page<CategoryResponse> getAllCategories(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    void deleteCategory(Long id); // soft delete

    void activateCategory(Long id);

    void deactivateCategory(Long id);

    public Page<CategoryResponse> getCategoriesByParentId(Long parentId, Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    public Page<CategoryResponse> searchCategories(String keyword, Integer pageNo, Integer pageSize, String sortBy, String sortDir);
	
}
