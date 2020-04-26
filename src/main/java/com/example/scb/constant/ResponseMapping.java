package com.example.scb.constant;

public enum  ResponseMapping {
    SUCCESS("Success", "200"),
    BAD_REQUEST("Bad Request", "400"),
    INTERNAL_SERVER_ERROR("Internal Server Error", "500");

    private String status;
    private String code;

    ResponseMapping(String status, String code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
