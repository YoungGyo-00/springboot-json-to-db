package com.example.jsontodb.controller;

import com.example.jsontodb.service.ObjectService;
import com.example.jsontodb.service.MetaService;
import com.example.jsontodb.service.CategoryService;
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
    private final ObjectService objectService;
    private final CategoryService categoryService;

    @Value("${env.meta}")
    private String meta_folder;

    @Value("${env.object}")
    private String object_folder;

    @Value("${env.category}")
    private String category_folder;

    @GetMapping("/meta")
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

    @GetMapping("/object")
    public String object() throws IOException, ParseException {
        File dir = new File(object_folder);
        for (String file : dir.list()){
            try{
                objectService.save(object_folder, file);
            } catch (UnexpectedRollbackException e){

            }
        }
        return "성공";
    }

    @GetMapping("/category")
    public String category() throws IOException, ParseException {
        try{
            categoryService.save(category_folder);
        } catch (UnexpectedRollbackException e) {

        }
        return "성공";
    }

}
