package com.example.jsontodb.controller;

import com.example.jsontodb.service.LabelService;
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
    private final LabelService labelService;

    @PostMapping("/meta")
    public String meta() throws IOException, ParseException {
        metaService.metaSave();
        return "성공";
    }

    @PostMapping("/label")
    public String label() throws IOException, ParseException {
        labelService.labelSave();
        return "성공";
    }
}
