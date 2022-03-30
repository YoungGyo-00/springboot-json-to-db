package com.example.jsontodb.controller;

import com.example.jsontodb.service.LabelService;
import com.example.jsontodb.service.MetaService;
import com.example.jsontodb.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ProjectService projectService;
    private final MetaService metaService;
    private final LabelService labelService;

    @PostMapping("/project")
    public String project() throws IOException, ParseException {
        projectService.save();
        return "성공";
    }

    @PostMapping("/meta")
    public String meta() throws IOException, ParseException {
        metaService.save();
        return "성공";
    }

    @PostMapping("/label")
    public String label() throws IOException, ParseException {
        labelService.save();
        return "성공";
    }
}
