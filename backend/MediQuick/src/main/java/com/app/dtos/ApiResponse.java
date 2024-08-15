package com.app.dtos;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@AllArgsConstructor
public class ApiResponse {
	public static enum Status {
		SUCCESS, FAIL, ERROR
	}
	
	private Status status;
	private Object data;
	private String message;
	
	// success ctor
	public ApiResponse(Status status, Object data) {
		this.status = status;
		this.data = data;
	}
	
	// error ctor
	public ApiResponse(Status status, String message) {
		this.status = status;
		this.message = message;
	}
	
	
	
	public static ResponseEntity<ApiResponse> success() {
		return ResponseEntity.ok(new ApiResponse(Status.SUCCESS, null));
	}
	
	public static ResponseEntity<ApiResponse> success(Object data) {
		return ResponseEntity.ok(new ApiResponse(Status.SUCCESS, data));
	}
	
	public static ResponseEntity<ApiResponse> success(String message, Object data) {
		return ResponseEntity.ok(new ApiResponse(Status.SUCCESS, data, message));
	}
	
	public static ResponseEntity<ApiResponse> fail(Object data) {
		return ResponseEntity.ok(new ApiResponse(Status.FAIL, data));
	}

	public static ResponseEntity<ApiResponse> fail(String message, Object data) {
		return ResponseEntity.ok(new ApiResponse(Status.FAIL, data, message));
	}
	
	public static ResponseEntity<ApiResponse> error(String message) {
		return ResponseEntity.ok(new ApiResponse(Status.ERROR, message));
	}

	public static ResponseEntity<ApiResponse> error(String message, Object data) {
		return ResponseEntity.ok(new ApiResponse(Status.ERROR, data, message));
	}
	
}
