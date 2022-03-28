package com.example.jsontodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonFile {
    @GetMapping("/a")
    public String jsonToDB(){
        return "new";
    }
}
