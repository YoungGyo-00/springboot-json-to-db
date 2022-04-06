package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

    @JsonProperty(value = "object_id")
    private String id;

    @JsonProperty(value = "property_value")
    private int propertyValue;

    private String points;

    private MetaDto metaDto;

    private CategoryDto categoryDto;


}
