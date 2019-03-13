package com.example.actuate;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RestControllerEndpoint(id = "custom2")
public class Custom2ControllerEndpoint {

    @GetMapping
    public ResponseEntity<Map<String, LocalDateTime>> hello() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(Map.of("now", LocalDateTime.now()));
    }
}
