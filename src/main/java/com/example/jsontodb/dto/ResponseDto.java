package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDto {

    @JsonProperty(value = "object_id")
    private String id;

    @JsonProperty(value = "property_value")
    private int propertyValue;

    private List<PointDto> points;

    private MetaDto meta;

    private ProjectDto project;

}
