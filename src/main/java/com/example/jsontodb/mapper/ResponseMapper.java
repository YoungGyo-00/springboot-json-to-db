package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.domain.Project;
import com.example.jsontodb.dto.AnnotationDto;
import com.example.jsontodb.dto.MetaDto;
import com.example.jsontodb.dto.ProjectDto;
import com.example.jsontodb.dto.ResponseDto;
import org.apache.tomcat.jni.Local;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ResponseMapper extends GenericMapper<ResponseDto, Object> {

    @Override
    @Mapping(target = "annotations.id", source = "id")
    @Mapping(target = "annotations.property.value", source = "propertyValue")
    @Mapping(target = "annotations.points", source = "points", qualifiedByName = "points")
    @Mapping(target = "info.version", constant = "1.0.0")
    @Mapping(target = "info.dateCreated", expression = "java(dateCreated())")
    @Mapping(target = "meta.fileName", source = "meta.id", qualifiedByName = "fileName")
    ResponseDto toDto(Object object);

    @Named("points")
    default List<AnnotationDto.PointDto> points(String point_info) throws ParseException {
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

    @Named("fileName")
    default String fileName(String id) {
        return id.split("-")[1];
    }

    @Named("dateCreated")
    default String dateCreated() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}