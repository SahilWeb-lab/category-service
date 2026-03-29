package com.ecom.payload;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PageResponse<T> {

	private List<T> content;
	
	private PageMetaData page;
	
	private SortMetaData sort;
	
	@Builder
	@Data
	public static class PageMetaData {
		private Integer number;
		private Integer size;
		private Long totalElements;
		private Integer totalPages;
		private Boolean first;
		private Boolean last;
	} 
	
	@Builder
	@Data
	public static class SortMetaData {
		private String sortedBy;
		private String direction;
	}
	
}
