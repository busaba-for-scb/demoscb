package com.example.scb.model;

import com.example.scb.constant.ResponseMapping;

public class FunnyStrResponse extends StatusCode {
    String funnyStr;

    public FunnyStrResponse(ResponseMapping statusCode, String funnyStr) {
        super(statusCode);
        this.funnyStr = funnyStr;
    }

    public String getFunnyStr() {
        return funnyStr;
    }
}
