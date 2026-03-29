package com.ecom.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CategoryResponse;
import com.ecom.endpoints.CategoryUserEndpoints;
import com.ecom.mapper.PageResponseMapper;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.PageResponse;
import com.ecom.service.CategoryService;
import com.ecom.util.ApiResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryUserController implements CategoryUserEndpoints {

    private final CategoryService categoryService;

    // Get Category By Id
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(
            @PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryByIdForUser(id);
        return ApiResponseUtil.success(categoryResponse, "Category fetched successfully!", HttpStatus.OK);
    }

    // Search Categories
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> searchCategories(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false, defaultValue = "true") Boolean status) {

    	Page<CategoryResponse> searchedCategories = categoryService.searchCategories(keyword, pageNo, pageSize, sortBy, sortDir, status);
    	PageResponse<CategoryResponse> pageResponse = PageResponseMapper.fromPage(searchedCategories, sortBy, sortDir);
    	long totalCategoriesFound = searchedCategories.getTotalElements();
    	return ApiResponseUtil.success(pageResponse, totalCategoriesFound + " categories found!", HttpStatus.OK);
    }
    
    // ✅ Get Categories By Parent Id
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getCategoriesByParentId(
            @PathVariable Long parentId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

       Page<CategoryResponse> categoriesByParentId = categoryService.getCategoriesByParentIdForUser(parentId, pageNo, pageSize, sortBy, sortDir);
       PageResponse<CategoryResponse> pageResponse = PageResponseMapper.fromPage(categoriesByParentId, sortBy, sortDir);
       long totalCategoriesFound = categoriesByParentId.getTotalElements();
       return ApiResponseUtil.success(pageResponse, totalCategoriesFound + " categories found!", HttpStatus.OK);
    }
    
 // Get All Categories (Pagination + Sorting)
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

       Page<CategoryResponse> page = categoryService.getAllCategoriesForAdmin(pageNo, pageSize, sortBy, sortDir, "active");
       PageResponse<CategoryResponse> pageResponse = PageResponseMapper.fromPage(page, sortBy, sortDir);
       long totalCategoriesFound = page.getTotalElements();
       return ApiResponseUtil.success(pageResponse, totalCategoriesFound + " categories found!", HttpStatus.OK);
    }
    
}
