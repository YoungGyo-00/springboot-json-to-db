package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDto {

    private InfoDto info;

    @JsonProperty(value = "image")
    private MetaDto meta;

    private AnnotationDto annotations;

    @JsonProperty(value = "categories")
    private ProjectDto project;

}
