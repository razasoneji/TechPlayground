package com.worktwo.HW2.Advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T> {
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy") // jackson converter
    private LocalDateTime timestamp;

    private T data;

    private ApiError apiError;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError){
        this();
        this.apiError = apiError;
    }
}
