package com.example.jsontodb.service;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.domain.Project;
import com.example.jsontodb.dto.*;
import com.example.jsontodb.mapper.ResponseMapper;
import com.example.jsontodb.repository.MetaRepository;
import com.example.jsontodb.repository.ObjectRepository;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void write_v0() {

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
    }

    @Transactional
    public void write_v1() {
        try {
            for (Meta meta : metaRepository.findAll()) {

                InfoDto info = InfoDto.builder()
                        .version("1.0.0")
                        .dateCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build();

                MetaDto image = MetaDto.builder()
                        .id(meta.getId())
                        .fileName(meta.getId().split("-")[1])
                        .height(meta.getHeight())
                        .width(meta.getWidth())
                        .build();

                JSONArray annotations = new JSONArray();
                JSONArray categories = new JSONArray();


                for (Object object : objectRepository.findByMetaId(meta.getId())) {
                    try {

                        int i = 0;
                        boolean flag = false;

                        // classId 비교
                        for (java.lang.Object category : categories) {

                            ProjectDto temp = (ProjectDto) category;

                            if (temp.getClassId() == object.getProject().getClassId()){
                                flag = true;
                                break;
                            }
                            i++;
                        }

                        if (!flag) {
                            categories.add(projectToProjectDto(object.getProject()));
                        }

                        annotations.add(objectToAnotationDto(object));

                    } catch (ParseException e) {
                        System.out.println(object.getId());
                        System.out.println(e);
                    }

                }

                // make result(ResponseDto2)
                ResponseDto1 result = ResponseDto1.builder()
                        .info(info)
                        .image(image)
                        .annotations(annotations)
                        .categories(categories)
                        .build();

                // set folder path & file name
                String json = meta.getId();
                String folder = path + json.split("-")[0] + "\\";
                String file_name = json.split("-")[1].substring(0, json.length() - folder.length()
                        + path.length() - 4) + ".json";

                File dir = new File(folder);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                // save result to JSON file
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

                FileWriter fw = new FileWriter(folder + file_name);

                writer.writeValue(fw, result);

                fw.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Set AnnotationDto values 1
    private AnnotationDto objectToAnotationDto(Object object) throws ParseException {

        ArrayList<List<AnnotationDto.PointDto>> points = new ArrayList<>();

        points.add(getPoints(object.getPoints()));

        return AnnotationDto.builder()
                .id(object.getId())
                .property(objectToPropertyDto(object))
                .points(getPoints(object.getPoints()))
                .categoryId(object.getProject().getClassId())
                .build();
    }

    // Set AnnotationDto values 2
    private AnnotationDto.PropertyDto objectToPropertyDto(Object object) {

        return AnnotationDto.PropertyDto.builder()
                .name(object.getProject().getPropertyName())
                .value(object.getPropertyValue())
                .unit(object.getProject().getPropertyUnit())
                .build();
    }

    // Set AnnotationDto values 3
    private List<AnnotationDto.PointDto> getPoints(String point_info) throws ParseException {

        JSONParser parser = new JSONParser();

        JSONArray points = (JSONArray) parser.parse(point_info);

        List<AnnotationDto.PointDto> point_arr = new ArrayList<>();

        for (java.lang.Object o : points) {
            JSONObject point = (JSONObject) o;

            AnnotationDto.PointDto pointDto = AnnotationDto.PointDto.builder()
                    .x(Float.parseFloat(String.valueOf(point.get("x"))))
                    .y(Float.parseFloat(String.valueOf(point.get("y"))))
                    .build();

            point_arr.add(pointDto);
        }

        return point_arr;
    }

    // Set Categories(Project entityToDto)
    private ProjectDto projectToProjectDto(Project project) {

        if (project == null) {
            return null;
        }

        return ProjectDto.builder()
                .superCategory(project.getSuperCategory())
                .classId(project.getClassId())
                .className(project.getClassName())
                .build();
    }

}
