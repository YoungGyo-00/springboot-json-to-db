package com.example.jsontodb.controller;

import com.example.jsontodb.service.LabelService;
import com.example.jsontodb.service.MetaService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final MetaService metaService;
    private final LabelService labelService;

    @Value("${env.meta}")
    private String meta_folder;

    @Value("${env.label}")
    private String label_folder;

    @PostMapping("/meta")
    public String meta() throws IOException, ParseException {
        File dir = new File(meta_folder);
        for (String file : dir.list()){
            try{
                metaService.save(meta_folder, file);
            } catch (UnexpectedRollbackException e){

            }
        }
        return "성공";
    }

    @PostMapping("/label")
    public String label() throws IOException, ParseException {
        File dir = new File(label_folder);
        for (String file : dir.list()){
            try{
                labelService.save(label_folder, file);
            } catch (UnexpectedRollbackException e){

            }
        }
        return "성공";
    }


}
