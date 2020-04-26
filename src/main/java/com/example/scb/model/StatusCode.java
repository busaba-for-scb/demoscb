package com.example.scb.model;

import com.example.scb.constant.ResponseMapping;

public class StatusCode {

    public String status;
    public String code;

    public StatusCode(ResponseMapping statusCode) {
        this.status = statusCode.getStatus();
        this.code = statusCode.getCode();
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}