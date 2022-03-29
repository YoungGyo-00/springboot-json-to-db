package com.example.jsontodb.controller;

import com.example.jsontodb.service.MetaService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final MetaService metaService;

    @PostMapping("/test")
    public String test(@RequestBody HashMap<Object, Object> params) throws IOException, ParseException {
        metaService.save(params);
        return "hello";
    }
}
