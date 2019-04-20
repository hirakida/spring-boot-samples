package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class ApiController {

    @GetMapping("/api")
    public Response include() {
        return new Response(LocalDateTime.now());
    }

    @GetMapping("/api/exclude")
    public Response exclude() {
        return new Response(LocalDateTime.now());
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private LocalDateTime dateTime;
    }
}
