package com.example.wmseasyexpert.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse {

    private boolean success;
    private String errorTime;
    private String errorType;
    private String errorCode;
    private String errorMessage;

}
