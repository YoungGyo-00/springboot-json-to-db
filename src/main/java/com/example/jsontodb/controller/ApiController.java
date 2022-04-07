package com.example.jsontodb.controller;

import com.example.jsontodb.dto.ResponseDto;
import com.example.jsontodb.service.ObjectService;
import com.example.jsontodb.service.MetaService;
import com.example.jsontodb.service.ProjectService;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final MetaService metaService;
    private final ObjectService objectService;
    private final ProjectService projectService;
    private final ResponseService responseService;

    @Value("${env.folder}")
    private String folder_path;

    @ApiOperation(value = "Meta 폴더 DB 저장 용도", notes = "Object 파일보다 먼저 실행")
    @GetMapping("/meta")
    public String meta() throws IOException, ParseException {
        String meta_folder = path() + "meta\\";
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
        // 최상위 폴더 위치
        List<String> object_folder = path();

        for(String path : object_folder) {
            // object file 상위 폴더 위치
            Path object_path = Paths.get(path + "\\labels\\");
            Stream<Path> walk = Files.walk(object_path);

            // object files 저장
            List<String> result;
            result = walk.filter(Files::isRegularFile)
                    .map(p -> p.toString())
                    .collect(Collectors.toList());

            result.forEach(p -> {
                try {
                    objectService.save(p);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
        return "성공";
    }

    @GetMapping("/response")
    public ResponseDto response() throws JsonProcessingException {
        return responseService.response();
    }

    public List<String> path() throws IOException {

        Path path = Paths.get(folder_path);

        List<String> result;
        Stream<String> walk = Files.walk(path, 1).map(p -> p.toString());

        result = walk.skip(1).collect(Collectors.toList());

        return result;
    }

}
