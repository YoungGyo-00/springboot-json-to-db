package com.example.jsontodb.service;

import com.example.jsontodb.domain.Object;
import com.example.jsontodb.dto.*;
import com.example.jsontodb.mapper.ResponseMapper;
import com.example.jsontodb.repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResponseService {
    
    private final ObjectRepository objectRepository;
    private final ResponseMapper mapper;

    @Transactional
    public ResponseDto response() {
        try {
            Object object = objectRepository.findById("15XX-0bf933d1-4d07-4622-af44-373980484fe3").orElseThrow();
            ResponseDto responseDto = mapper.toDto(object);

            return responseDto;
        } catch (Exception e){
            return null;
        }
    }
}
