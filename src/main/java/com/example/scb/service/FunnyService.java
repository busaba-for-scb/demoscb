package com.example.scb.service;

import com.example.scb.model.FunnyStrResponse;
import com.example.scb.model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.example.scb.constant.ResponseMapping.BAD_REQUEST;
import static com.example.scb.constant.ResponseMapping.SUCCESS;

@Service
public class FunnyService {

    private StringService stringService;

    @Autowired
    public FunnyService(StringService stringService) {
        this.stringService = stringService;
    }

    public ResponseEntity doService(String funnyStr) {

        String decodedStr = stringService.decodeBase64String(funnyStr);
        boolean isStringValid = stringService.isValidateString(decodedStr);

        if (isStringValid) {

            StringBuilder resultString = stringService.resultTheString(decodedStr);
            String strReverse = stringService.reverseString(resultString.toString());
            return new ResponseEntity(new FunnyStrResponse(SUCCESS, stringService.encodeBase64String(strReverse)), HttpStatus.OK);
        } else {
            return new ResponseEntity(new StatusCode(BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

}
