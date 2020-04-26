package com.example.scb.service;

import com.example.scb.model.FunnyStrResponse;
import com.example.scb.model.StatusCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.scb.constant.ResponseMapping.BAD_REQUEST;
import static com.example.scb.constant.ResponseMapping.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FunnyServiceTest {
    
    @InjectMocks
    private FunnyService funnyService;

    @Mock
    private StringService stringService;

    @Test
    public void testDoService_Success() {
        when(stringService.decodeBase64String("QSBIRU4gIEhBUyAgTUFOWSAgIENISUNLUw==")).thenReturn("A HEN  HAS  MANY   CHICKS");
        when(stringService.isValidateString("A HEN  HAS  MANY   CHICKS")).thenReturn(true);
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("a1hen2has2many3chicks");
        when(stringService.resultTheString("A HEN  HAS  MANY   CHICKS")).thenReturn(returnStr);
        when(stringService.reverseString(returnStr.toString())).thenReturn("skcihc3ynam2sah2neh1a");
        String returnString = "c2tjaWhjM3luYW0yc2FoMm5laDFh";
        when(stringService.encodeBase64String("skcihc3ynam2sah2neh1a")).thenReturn(returnString);
        ResponseEntity responseEntity = funnyService.doService("QSBIRU4gIEhBUyAgTUFOWSAgIENISUNLUw==");

        ResponseEntity<FunnyStrResponse> expectedResponseEntity = new ResponseEntity<>(new FunnyStrResponse(SUCCESS, returnString), HttpStatus.OK);
        assertEquals(expectedResponseEntity.getStatusCode(), responseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getStatusCodeValue(), responseEntity.getStatusCodeValue());
        FunnyStrResponse actualResponse = (FunnyStrResponse) responseEntity.getBody();
        FunnyStrResponse expectedResponse = expectedResponseEntity.getBody();
        assertEquals(expectedResponse.getFunnyStr(), actualResponse.getFunnyStr());
    }

    @Test
    public void testDoService_Error_decodeBase64String() {
        when(stringService.decodeBase64String(any())).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            funnyService.doService(any());
        });

    }

    @Test
    public void testDoService_Error_notValidString_EmptyString() {
        when(stringService.decodeBase64String("IA==")).thenReturn(" ");
        when(stringService.isValidateString(" ")).thenReturn(false);

        ResponseEntity responseEntity = funnyService.doService("IA==");

        ResponseEntity<StatusCode> expectedResponseEntity = new ResponseEntity<>(new StatusCode(BAD_REQUEST), HttpStatus.BAD_REQUEST);
        assertEquals(expectedResponseEntity.getStatusCode(), responseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getStatusCodeValue(), responseEntity.getStatusCodeValue());
        StatusCode actualResponse = (StatusCode) responseEntity.getBody();
        StatusCode expectedResponse = expectedResponseEntity.getBody();
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getCode(), actualResponse.getCode());
    }

    @Test
    public void testDoService_Error_notValidString_StringNotInCondition() {
        when(stringService.decodeBase64String("SGVsbG8gMSAyIDMgNCBIZWxsbw==")).thenReturn("Hello 1 2 3 4 Hello");
        when(stringService.isValidateString("Hello 1 2 3 4 Hello")).thenReturn(false);

        ResponseEntity responseEntity = funnyService.doService("SGVsbG8gMSAyIDMgNCBIZWxsbw==");

        ResponseEntity<StatusCode> expectedResponseEntity = new ResponseEntity<>(new StatusCode(BAD_REQUEST), HttpStatus.BAD_REQUEST);
        assertEquals(expectedResponseEntity.getStatusCode(), responseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getStatusCodeValue(), responseEntity.getStatusCodeValue());
        StatusCode actualResponse = (StatusCode) responseEntity.getBody();
        StatusCode expectedResponse = expectedResponseEntity.getBody();
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getCode(), actualResponse.getCode());
    }
}
