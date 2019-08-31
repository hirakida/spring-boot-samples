package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PutMapping("/users/{id}")
    public User updateCache(@PathVariable int id) {
        return userService.updateCache(id);
    }

    @DeleteMapping("/users")
    public void deleteCache() {
        userService.deleteCache();
    }

    @DeleteMapping("/users/{id}")
    public void deleteCache(@PathVariable int id) {
        userService.deleteCache(id);
    }
}