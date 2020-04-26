package com.example.scb.controller;

import com.example.scb.service.FunnyService;
import com.example.scb.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunnyController {

    private FunnyService funnyService;

    @Autowired
    public FunnyController(FunnyService funnyService, StringService stringService) {
        this.funnyService = funnyService;
    }

    @GetMapping(path = "/funnystr/{funnyStr}")
    public ResponseEntity funnyStr(@PathVariable String funnyStr) {
        return funnyService.doService(funnyStr);
    }
}
