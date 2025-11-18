package com.ibandorta.taskmanager.taskmanager.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
public class ApiResponse<T> {

    private int statusCode;
    private T data;
    private String error;
    private LocalDateTime timestamp;


    public ApiResponse(){

    }

    public ApiResponse(int statusCode, T data, String error, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.data = data;
        this.error = error;
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static <T> ApiResponse<T> success(int statusCode, T data) {
       ApiResponse<T> r = new ApiResponse<>();
       r.setStatusCode(statusCode);
       r.setData(data);
       r.setTimestamp(LocalDateTime.now());
       return r;
    }

    public static <T>ApiResponse<T> error(int statusCode, String message){
        ApiResponse<T> r = new ApiResponse<>();
        r.setStatusCode(statusCode);
        r.setError(message);
        r.setTimestamp(LocalDateTime.now());
        return r;
    }
}
