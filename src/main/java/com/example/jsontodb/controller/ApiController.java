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
    public String meta() throws IOException{
        System.out.println("meta 파일 실행");
        path("\\meta\\", 3).forEach(p -> metaService.save(p));
        return "성공";
    }

    @ApiOperation(value = "Object 파일 DB 저장 용도", notes = "Meta, category 파일 먼저 저장한 후 실행")
    @GetMapping("/object")
    public String object() throws IOException {
        System.out.println("object 파일 실행");
        path("\\labels\\", 2).forEach(p -> objectService.save(p));
        return "성공";
    }

    @ApiOperation(value = "Object 파일 DB 저장 용도", notes = "Meta, category 파일 먼저 저장한 후 실행")
    @GetMapping("/project")
    public String project() throws IOException {
        System.out.println("project 파일 실행");
        path("\\\\", 1).forEach(p -> projectService.save(p));
        return "성공";
    }

    @ApiOperation(value = "JSON 파일로 뽑아보기")
    @GetMapping("/response")
    public ResponseDto response() {
        return responseService.response();
    }

    @ApiOperation(value = "JSON 파일 쓰기")
    @GetMapping("/write")
    public String write() {
        responseService.write();
        return "성공";
    }

    // 파일 경로 리스트 반환
    public List<String> path(String file_type, int max_depth) throws IOException {

        // 최상위 폴더 저장된 위치
        Path parent_path = Paths.get(folder_path);

        Stream<String> folder_walk = Files.walk(parent_path, 1).map(p -> p.toString());

        // 최상위 폴더 리스트
        List<String> parent_folder;
        parent_folder = folder_walk.skip(1).collect(Collectors.toList());

        // 받은 Type에 해당하는 파일 전체 저장 용도
        List<String> result = new ArrayList<>();

        for(String path : parent_folder) {
            // 받은 type("meta", "object", "project") file 상위 폴더 위치
            Path folder_path = Paths.get(path + file_type);
            Stream<Path> file_walk = Files.walk(folder_path, max_depth);

            // file list 저장
            List<String> file_path;
            file_path = file_walk.filter(Files::isRegularFile)
                    .map(p -> p.toString())
                    .collect(Collectors.toList());

            // result에 file list 합치기
            result.addAll(file_path);
        }


        return result;
    }

}
