package com.ecom.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecom.dto.CategoryResponse;
import com.ecom.payload.ApiResponse;


public class ApiResponseUtil {

//	For Success Response:
	public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message, HttpStatus status) {
		ApiResponse<T> apiResponse = ApiResponse.<T>builder()
		.data(data)
		.message(message)
		.status(true)
		.timestamp(LocalDateTime.now())
		.build();
		return new ResponseEntity<ApiResponse<T>>(apiResponse, status);
	}
	
//	For Error Response:
	public static <T> ResponseEntity<ApiResponse<T>> error(String message, HttpStatus status) {
		ApiResponse<T> apiResponse = ApiResponse.<T>builder()
		.message(message)
		.status(false)
		.timestamp(LocalDateTime.now())
		.build();
		
		return new ResponseEntity<>(apiResponse, status);
	}
}
