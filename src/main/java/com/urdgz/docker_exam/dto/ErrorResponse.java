package com.urdgz.docker_exam.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Represents an error response that is returned to the client when an exception occurs.
 * This class encapsulates the details of the error such as timestamp, status, error type,
 * message, and the path where the error occurred.
 * @author Ulises Rodríguez García.
 */ 
public class ErrorResponse {

    /**
     * The timestamp when the error occurred.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private final LocalDateTime timestamp;

    /**
     * The HTTP status code of the error.
     */
    private final Integer status;

    /**
     * The HTTP status reason phrase associated with the error.
     */
    private final String error;

    /**
     * A descriptive message of the error.
     */
    private final String message;

    /**
     * The path where the error occurred.
     */
    private final String path;

    /**
     * Constructs a new ErrorResponse with the given status, message, and path.
     * The timestamp is set to the current date and time.
     * @param status  the HTTP status of the error
     * @param message the descriptive message of the error
     * @param path    the path where the error occurred
     */
    public ErrorResponse(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    /**
     * Gets the timestamp when the error occurred.
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the HTTP status code of the error.
     * @return the status code
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Gets the HTTP status reason phrase associated with the error.
     * @return the HTTP status reason phrase
     */
    public String getError() {
        return error;
    }

    /**
     * Gets the descriptive message of the error.
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the path where the error occurred.
     * @return the error path
     */
    public String getPath() {
        return path;
    }
}