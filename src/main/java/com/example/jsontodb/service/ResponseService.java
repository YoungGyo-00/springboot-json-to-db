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
            Object object = objectRepository.findById("82XX-17318b2b-b743-4f05-8612-a8b78aaef99c").orElseThrow();
            ResponseDto responseDto = mapper.toDto(object);

            JSONParser parser = new JSONParser();

            String s = object.getPoints();
            JSONArray points = (JSONArray) parser.parse(s);

            List<PointDto> point_temp = new ArrayList<>();

            for (java.lang.Object o : points) {
                JSONObject point = (JSONObject) o;

                PointDto pointDto = PointDto.builder()
                        .x(Integer.parseInt(String.valueOf(point.get("x"))))
                        .y(Integer.parseInt(String.valueOf(point.get("y"))))
                        .build();

                point_temp.add(pointDto);
            }

            responseDto.setPoints(point_temp);

            return responseDto;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
