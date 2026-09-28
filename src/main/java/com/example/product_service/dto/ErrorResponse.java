package com.example.product_service.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private String status;
    private int error;
    private String message;
    private String path;
    private Map<String, String> errors;

    public ErrorResponse(){

    }

    public ErrorResponse(LocalDateTime timestamp, int error, String status, String message, String path) {
        this.timestamp = timestamp;
        this.error = error;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
