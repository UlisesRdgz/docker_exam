package com.urdgz.docker_exam.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * SuccessResponse is a generic class that represents a successful response returned to the client.
 * It contains details such as the timestamp, status code, message, and the data of type T.
 * @param <T> the type of the data contained in the response
 * @author Ulises Rodríguez García
 */
public class SuccessResponse<T> {

    /**
     * The timestamp when the response is created.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;

    /**
     * The HTTP status code of the response.
     */
    private Integer status;

    /**
     * A descriptive message of the success response.
     */
    private String message;

    /**
     * The data contained in the response.
     */
    private T data;

    /**
     * Constructs a new SuccessResponse with the specified status, message, and data.
     * The timestamp is set to the current date and time.
     * @param status  the HTTP status of the response
     * @param message the descriptive message of the success response
     * @param data    the data contained in the response
     */
    public SuccessResponse(HttpStatus status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    /**
     * Gets the timestamp when the response is created.
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the response is created.
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the HTTP status code of the response.
     * @return the status code
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the response.
     * @param status the status code to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the descriptive message of the success response.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the descriptive message of the success response.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the data contained in the response.
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data contained in the response.
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
