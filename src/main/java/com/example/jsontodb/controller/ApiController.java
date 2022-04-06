package com.example.jsontodb.controller;

import com.example.jsontodb.dto.ResponseDto;
import com.example.jsontodb.service.ObjectService;
import com.example.jsontodb.service.MetaService;
import com.example.jsontodb.service.CategoryService;
import com.example.jsontodb.service.ResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
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
    private final ResponseService responseService;

    @Value("${env.meta}")
    private String meta_folder;

    @Value("${env.object}")
    private String object_folder;

    @Value("${env.category}")
    private String category_folder;

    @ApiOperation(value = "Meta 폴더 DB 저장 용도", notes = "Object 파일보다 먼저 실행")
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

    @ApiOperation(value = "Object 파일 DB 저장 용도", notes = "Meta, category 파일 먼저 저장한 후 실행")
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

    @ApiOperation(value = "Category 파일 DB 저장 용도", notes = "Object 파일보다 먼저 실행")
    @GetMapping("/category")
    public String category() throws IOException, ParseException {
        try{
            categoryService.save(category_folder);
        } catch (UnexpectedRollbackException e) {

        }
        return "성공";
    }

    @GetMapping("/response")
    public ResponseDto response() throws JsonProcessingException {
        return responseService.response();
    }

}
