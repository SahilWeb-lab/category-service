package com.ecom.mapper;

import org.springframework.data.domain.Page;

import com.ecom.payload.PageResponse;

public class PageResponseMapper {

	 public static <T> PageResponse<T> fromPage(Page<T> page, String sortBy, String sortDir) {

	        return PageResponse.<T>builder()
	                .content(page.getContent())
	                .page(
	                        PageResponse.PageMetaData.builder()
	                                .number(page.getNumber())
	                                .size(page.getSize())
	                                .totalElements(page.getTotalElements())
	                                .totalPages(page.getTotalPages())
	                                .first(page.isFirst())
	                                .last(page.isLast())
	                                .build()
	                )
	                .sort(
	                        PageResponse.SortMetaData.builder()
	                                .sortedBy(sortBy)
	                                .direction(sortDir.toUpperCase())
	                                .build()
	                )
	                .build();
	    }
	
}
