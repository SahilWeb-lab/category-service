package com.ecom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

	@NotBlank(message = "Category name is required")
	@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
	private String name;

	@Size(max = 500, message = "Description cannot exceed 500 characters")
	private String description;

	@Positive(message = "Parent ID must be positive")
	private Long parentId;

	@NotNull(message = "Active status is required")
	private Boolean status;

}
