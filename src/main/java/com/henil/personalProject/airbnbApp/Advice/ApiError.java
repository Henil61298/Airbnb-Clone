package com.henil.personalProject.airbnbApp.Advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
