package com.example.jsontodb.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/test")
    public String test(@RequestBody HashMap<Object, Object> param) {
        param.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
        return "good";
    }
}
