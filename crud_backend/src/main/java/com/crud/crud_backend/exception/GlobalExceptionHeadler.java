package com.crud.crud_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHeadler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException exception) {
		return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("errors", exception.getBindingResult().getFieldErrors().stream()
				.collect(LinkedHashMap::new, (errors, error) -> errors.put(error.getField(), error.getDefaultMessage()),
						Map::putAll));
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleUnexpected(Exception exception) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
	}

	private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("status", status.value());
		response.put("message", message);
		return ResponseEntity.status(status).body(response);
	}
}
