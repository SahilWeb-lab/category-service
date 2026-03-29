package com.ecom.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

	private Long id;

    private String name;

    private String description;

    private Long parentId;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
	
}
