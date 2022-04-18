package com.example.jsontodb.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler({IOException.class})
    public ResponseEntity exFolder(Exception e) {
        System.out.println(e);
        return ResponseEntity.status(403).body("경로에 폴더가 없음");
    }
}
