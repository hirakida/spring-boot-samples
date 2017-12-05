package com.example;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

@Controller
public class WebController {

    @GetMapping("/{condition}")
    public String index(@MatrixVariable Map<String, String> condition, Model model) {
        model.addAttribute("condition", condition);
        return "index";
    }
}