package com.urdgz.docker_exam.exception;

import org.springframework.http.HttpStatus;

/**
 * CustomApiException is a custom exception class that extends RuntimeException.
 * This exception is used to represent API-related errors with an associated HTTP status code.
 * @author Ulises Rodríguez García.
 */
public class CustomApiException extends RuntimeException {

    /**
     * The HTTP status code associated with this exception.
     */
    private final HttpStatus status;

    /**
     * Constructs a new CustomApiException with the specified HTTP status and detail message.
     * @param status  the HTTP status code associated with the exception
     * @param message the detail message explaining the reason for the exception
     */
    public CustomApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Returns the HTTP status code associated with this exception.
     * @return the HTTP status code
     */
    public HttpStatus getStatus() {
        return status;
    }
}