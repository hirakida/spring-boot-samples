package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValueAnnotationController {
    private final String str1;
    private final String str2;
    private final long number1;
    private final long number2;

    public ValueAnnotationController(@Value("${app.string.value1}") String str1,
                                     @Value("${app.string.value2:@Value default}") String str2,
                                     @Value("${app.number.value1}") long number1,
                                     @Value("${app.number.value2:0}") long number2) {
        this.str1 = str1;
        this.str2 = str2;
        this.number1 = number1;
        this.number2 = number2;
    }

    @GetMapping("/value")
    public Response value() {
        return new Response(str1, str2, number1, number2);
    }
}
