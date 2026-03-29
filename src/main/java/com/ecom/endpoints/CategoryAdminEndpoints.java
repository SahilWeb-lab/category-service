package com.ecom.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.dto.CategoryRequest;
import com.ecom.dto.CategoryResponse;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/admin/categories")
@Tag(name = "Admin Category APIs", description = "APIs for admin to manage categories")
public interface CategoryAdminEndpoints {

    @PostMapping
    @Operation(summary = "Create category", description = "Admin can create a new category")
    ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Parameter(description = "Category request payload", required = true)
            @Valid @RequestBody CategoryRequest request
    );

    @PutMapping("/{id}")
    @Operation(summary = "Update category", description = "Update existing category")
    ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @Parameter(description = "Category ID")
            @PathVariable Long id,

            @Parameter(description = "Updated category request")
            @Valid @RequestBody CategoryRequest request
    );

    @GetMapping
    @Operation(summary = "Get all categories", description = "Fetch paginated categories with sorting")
    ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategories(

            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") Integer pageNo,

            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") Integer pageSize,

            @Parameter(description = "Sort by field")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction: asc or desc")
            @RequestParam(defaultValue = "asc") String sortDir
    );

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Soft delete a category")
    ResponseEntity<ApiResponse<Void>> deleteCategory(

            @Parameter(description = "Category ID")
            @PathVariable Long id
    );

    @PatchMapping("/{id}/activate")
    @Operation(summary = "Activate category", description = "Mark category as active")
    ResponseEntity<ApiResponse<Void>> activateCategory(

            @Parameter(description = "Category ID")
            @PathVariable Long id
    );

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate category", description = "Mark category as inactive")
    ResponseEntity<ApiResponse<Void>> deactivateCategory(

            @Parameter(description = "Category ID")
            @PathVariable Long id
    );

}