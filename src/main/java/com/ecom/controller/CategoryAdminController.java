package com.ecom.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CategoryRequest;
import com.ecom.dto.CategoryResponse;
import com.ecom.endpoints.CategoryAdminEndpoints;
import com.ecom.mapper.PageResponseMapper;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.PageResponse;
import com.ecom.service.CategoryService;
import com.ecom.util.ApiResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryAdminController implements CategoryAdminEndpoints {

	 private final CategoryService categoryService;
	
	 // Create Category
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
           @Valid @RequestBody CategoryRequest request) {
    	log.info("Category creation started!!");
        CategoryResponse response = categoryService.createCategory(request);
        log.info("Category created successfully!");
        return ApiResponseUtil.success(response, "Category created successfully!", HttpStatus.CREATED);
    }
    
    // Update Category
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequest request) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, request);
        return ApiResponseUtil.success(categoryResponse, "Category updated successfully!", HttpStatus.OK);
    }
    
    // Get Category By Id
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(
            @PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryByIdForAdmin(id);
        return ApiResponseUtil.success(categoryResponse, "Category fetched successfully!", HttpStatus.OK);
    }
    
    // Get All Categories (Pagination + Sorting)
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "all") String status) {

       Page<CategoryResponse> page = categoryService.getAllCategoriesForAdmin(pageNo, pageSize, sortBy, sortDir, status);
       PageResponse<CategoryResponse> pageResponse = PageResponseMapper.fromPage(page, sortBy, sortDir);
       long totalCategoriesFound = page.getTotalElements();
       return ApiResponseUtil.success(pageResponse, totalCategoriesFound + " categories found!", HttpStatus.OK);
    }

    // Soft Delete Category
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponseUtil.success(null, "Category deleted successfully!", HttpStatus.OK);
    }

    // Activate Category
    public ResponseEntity<ApiResponse<Void>> activateCategory(@PathVariable Long id) {
    	categoryService.activateCategory(id);
    	return ApiResponseUtil.success(null, "Category activated successfully!", HttpStatus.OK);
    }

    // Deactivate Category
    public ResponseEntity<ApiResponse<Void>> deactivateCategory(@PathVariable Long id) {
    	categoryService.deactivateCategory(id);
    	return ApiResponseUtil.success(null, "Category deactivated successfully!", HttpStatus.OK);
    }

	@Override
	public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getCategoriesByParentId(Long parentId,
			Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		 Page<CategoryResponse> categoriesByParentId = categoryService.getCategoriesByParentIdForAdmin(parentId, pageNo, pageSize, sortBy, sortDir);
	       PageResponse<CategoryResponse> pageResponse = PageResponseMapper.fromPage(categoriesByParentId, sortBy, sortDir);
	       long totalCategoriesFound = categoriesByParentId.getTotalElements();
	       return ApiResponseUtil.success(pageResponse, totalCategoriesFound + " categories found!", HttpStatus.OK);
	}
	
}
