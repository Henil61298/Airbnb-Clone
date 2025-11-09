package com.henil.personalProject.airbnbApp.Advice;

import com.henil.personalProject.airbnbApp.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> resourceNotFound(ResourceNotFoundException exception){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return createGenericResponse(error);
    }

    private ResponseEntity<ApiResponse<ApiError>> createGenericResponse(ApiError error){
        return new ResponseEntity<>( new ApiResponse<>(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ApiError>> genericException(Exception exception){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return createGenericResponse(error);
    }
}
