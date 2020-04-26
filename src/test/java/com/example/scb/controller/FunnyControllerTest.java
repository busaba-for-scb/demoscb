package com.example.scb.controller;

import static com.example.scb.constant.ResponseMapping.BAD_REQUEST;
import static com.example.scb.constant.ResponseMapping.SUCCESS;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.scb.model.FunnyStrResponse;
import com.example.scb.model.StatusCode;
import com.example.scb.service.FunnyService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FunnyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FunnyService funnyService;

    @Test
    public void testFunnyStr_Success_ShouldReturnSuccess() throws Exception {
        when(funnyService.doService(anyString())).thenReturn(
                new ResponseEntity(new FunnyStrResponse(SUCCESS, "c2tjaWhjM3luYW0yc2FoMm5laDFh"), HttpStatus.OK));
        this.mockMvc.perform(get("/funnystr/QSBIRU4gIEhBUyAgTUFOWSAgIENISUNLUw==")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SUCCESS.getStatus()))
                .andExpect(jsonPath("$.code").value(SUCCESS.getCode()))
                .andExpect(jsonPath("$.funnyStr").value("c2tjaWhjM3luYW0yc2FoMm5laDFh"));
    }

    @Test
    public void testFunnyStr_Error_ShouldReturnBadRequest() throws Exception {
        when(funnyService.doService(anyString())).thenReturn(new ResponseEntity(new StatusCode(BAD_REQUEST), HttpStatus.BAD_REQUEST));
        this.mockMvc.perform(get("/funnystr/TestString")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(BAD_REQUEST.getStatus()))
                .andExpect(jsonPath("$.code").value(BAD_REQUEST.getCode()));
    }

    @Test
    public void testFunnyStr_Error_ShouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/funnystr/")).andDo(print()).andExpect(status().isNotFound());
    }
}
