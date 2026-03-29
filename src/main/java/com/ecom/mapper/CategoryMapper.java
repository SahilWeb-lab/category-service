package com.ecom.mapper;

import org.springframework.stereotype.Component;

import com.ecom.dto.CategoryRequest;
import com.ecom.dto.CategoryResponse;
import com.ecom.model.Category;

@Component
public class CategoryMapper {

	private CategoryMapper() {}

	public Category toEntity(CategoryRequest categoryRequest) {
		return Category.builder().name(categoryRequest.getName()).description(categoryRequest.getDescription())
				.parentId(categoryRequest.getParentId()).status(categoryRequest.getStatus()).build();
	}

	public CategoryResponse toDTO(Category category) {
		return CategoryResponse.builder()
				.id(category.getId())
				.name(category.getName())
				.description(category.getDescription())
				.status(category.getStatus())
				.parentId(category.getParentId())
				.createdAt(category.getCreatedAt())
				.updatedAt(category.getUpdatedAt())
				.build();
	}
}
