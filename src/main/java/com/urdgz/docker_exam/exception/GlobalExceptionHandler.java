package com.urdgz.docker_exam.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.urdgz.docker_exam.dto.ErrorResponse;

/**
 * GlobalExceptionHandler is a controller advice class that handles exceptions globally
 * across the whole application. It extends ResponseEntityExceptionHandler to provide
 * custom exception handling for specific exception types.
 * @author Ulises Rodríguez García.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles CustomApiException and constructs a detailed error response.
     * @param exception the CustomApiException thrown
     * @param request   the WebRequest that resulted in the exception
     * @return a ResponseEntity containing the error response and the appropriate HTTP status code
     */
    @ExceptionHandler(CustomApiException.class)
    protected ResponseEntity<ErrorResponse> handleCustomApiException(CustomApiException exception, WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatus(), exception.getMessage(), path);

        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }
}
