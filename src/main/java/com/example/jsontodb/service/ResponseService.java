package com.example.jsontodb.service;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.dto.*;
import com.example.jsontodb.mapper.ResponseMapper;
import com.example.jsontodb.repository.MetaRepository;
import com.example.jsontodb.repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {
    
    private final ObjectRepository objectRepository;
    private final MetaRepository metaRepository;
    private final ResponseMapper mapper;

    @Value("${env.write}")
    private String path;

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

    @Transactional
    public void write() {

        try {
            for (Meta meta : metaRepository.findAll()) {

                JSONArray arr = new JSONArray();

                for (Object object : objectRepository.findByMetaId(meta.getId())) {
                    try {

                        ResponseDto responseDto = mapper.toDto(object);

                        responseDto.getAnnotations().getProperty().setUnit(object.getProject().getPropertyUnit());
                        responseDto.getAnnotations().getProperty().setName(object.getProject().getPropertyName());
                        responseDto.getAnnotations().setCategoryId(object.getProject().getClassId());

                        arr.add(responseDto);

                    } catch (Exception e) {
                        System.out.println(object.getId());
                        System.out.println(e);
                    }
                }

                ObjectMapper objectMapper = new ObjectMapper();
                ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());


                String json = meta.getId();
                String folder = path + json.split("-")[0] + "\\";
                String file_name = json.split("-")[1].substring(0, json.length() - folder.length() + path.length() - 4) + ".json";

                File dir = new File(folder);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                FileWriter fw = new FileWriter(folder + file_name);

                writer.writeValue(fw, arr);

                fw.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        return;

        /*for (Object object : objectRepository.findAll()) {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                ResponseDto responseDto = mapper.toDto(object);

                responseDto.getAnnotations().getProperty().setUnit(object.getProject().getPropertyUnit());
                responseDto.getAnnotations().getProperty().setName(object.getProject().getPropertyName());
                responseDto.getAnnotations().setCategoryId(object.getProject().getClassId());

                String json = responseDto.getMeta().getId();
                String folder = path + json.split("-")[0] + "\\";
                String file_name = json.split("-")[1].substring(0, json.length() - folder.length() + path.length() - 4) + ".json";

                File dir = new File(folder);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                FileWriter fw = new FileWriter(folder + file_name);
                gson.toJson(responseDto, fw);

                fw.flush();
                fw.close();

            } catch (Exception e) {
                System.out.println(object.getId());
                System.out.println(e);
            }
        }

        return;*/
    }
}
