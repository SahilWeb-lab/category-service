package com.ecom.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.dto.CategoryResponse;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RequestMapping("/api/v1/user/categories")
@Tag(name = "User Category APIs", description = "APIs for users to view and search categories")
public interface CategoryUserEndpoints {

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Fetch category details by ID")
    ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById(

            @Parameter(description = "Category ID")
            @PathVariable Long id
    );

    @GetMapping("/search")
    @Operation(summary = "Search categories", description = "Search categories by keyword with pagination")
    ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> searchCategories(

            @Parameter(description = "Search keyword", required = true)
            @RequestParam String keyword,

            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") Integer pageNo,

            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") Integer pageSize,

            @Parameter(description = "Sort by field")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction: asc or desc")
            @RequestParam(defaultValue = "asc") String sortDir
    );

    @GetMapping("/parent/{parentId}")
    @Operation(summary = "Get categories by parent ID", description = "Fetch categories by parent category")
    ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getCategoriesByParentId(

            @Parameter(description = "Parent category ID")
            @PathVariable Long parentId,

            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") Integer pageNo,

            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") Integer pageSize,

            @Parameter(description = "Sort by field")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction")
            @RequestParam(defaultValue = "asc") String sortDir
    );
}