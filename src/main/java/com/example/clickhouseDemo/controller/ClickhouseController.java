package com.example.clickhouseDemo.controller;

import com.example.clickhouseDemo.service.ClickhouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ClickhouseController {

    private final ClickhouseService clickhouseService;

    public ClickhouseController(ClickhouseService clickhouseService) {
        this.clickhouseService = clickhouseService;
    }

    @GetMapping("/load")
    public ResponseEntity<String> load() {
       return clickhouseService.processApiData();
    }
}
