package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDto {

    @JsonProperty(value = "supercategory")
    private String superCategory;

    @JsonProperty(value = "id")
    private String classId;

    @JsonProperty(value = "name")
    private String className;

}
