package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    @JsonProperty(value = "category_id")
    private String id;

    @JsonProperty(value = "category_name")
    private String className;

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "annotation_type")
    private String annotationType;

    @JsonProperty(value = "super_category")
    private String superCategory;
}
