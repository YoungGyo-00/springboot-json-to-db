package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"info", "meta", "annotations", "project"})
public class ResponseDto {

    private InfoDto info;

    @JsonProperty(value = "image")
    private MetaDto meta;

    private AnnotationDto annotations;

    @JsonProperty(value = "categories")
    private ProjectDto project;

}
