package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDto {

    @JsonProperty(value = "class_id")
    private String classId;

    @JsonProperty(value = "class_name")
    private String className;

    @JsonProperty(value = "super_category")
    private String superCategory;

    @JsonProperty(value = "annotation_type")
    private String annotationType;

    @JsonProperty(value = "property_name")
    private String propertyName;

    @JsonProperty(value = "property_unit")
    private String propertyUnit;

}
