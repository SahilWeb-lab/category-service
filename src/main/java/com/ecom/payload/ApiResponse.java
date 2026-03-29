package com.ecom.payload;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse<T> {

	private Boolean status;
	
	private String message;
	
	private LocalDateTime timestamp;
	
	private T data;
	
}
