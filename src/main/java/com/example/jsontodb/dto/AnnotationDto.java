package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"id", "property", "points"})
public class AnnotationDto {

    private String id;

    private PropertyDto property;

    @JsonProperty(value = "segmentation")
    private List<PointDto> points;

    @JsonProperty(value = "category_id")
    private String categoryId;

    @Data
    @Builder
    public static class PropertyDto {

        private String name;

        private Integer value;

        private String unit;
    }

    @Data
    @Builder
    public static class PointDto {

        private float x;

        private float y;
    }
}
