package com.example.scb.controller;

import com.example.scb.model.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.scb.constant.ResponseMapping.BAD_REQUEST;
import static com.example.scb.constant.ResponseMapping.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity encodeBase64ErrorHandler() {
        return  new ResponseEntity<>(new StatusCode(BAD_REQUEST), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity internalServerErrorHandler() {
        return  new ResponseEntity<>(new StatusCode(INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
