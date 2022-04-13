package com.example.jsontodb.service;

import com.example.jsontodb.domain.Object;
import com.example.jsontodb.dto.*;
import com.example.jsontodb.mapper.ResponseMapper;
import com.example.jsontodb.repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {
    
    private final ObjectRepository objectRepository;
    private final ResponseMapper mapper;

    @Transactional
    public ResponseDto response() {
        try {
            Object object = objectRepository.findById("21XX-8d91d2b3-e4d3-4d04-9993-b34bbf6a8165").orElseThrow();
            ResponseDto responseDto = mapper.toDto(object);

            responseDto.getAnnotations().getProperty().setUnit(object.getProject().getPropertyUnit());
            responseDto.getAnnotations().getProperty().setName(object.getProject().getPropertyName());
            responseDto.getAnnotations().setCategoryId(object.getProject().getClassId());

            return responseDto;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
