package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"id", "propertyValue", "points", "meta", "project"})
public class ResponseDto {

    @JsonProperty(value = "object_id")
    private String id;

    @JsonProperty(value = "property_value")
    private int propertyValue;

    private List<PointDto> points;

    private MetaDto meta;

    private ProjectDto project;

}
