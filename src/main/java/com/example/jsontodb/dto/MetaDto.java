package com.example.jsontodb.dto;

import com.example.jsontodb.domain.Object;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"id", "fileName", "height", "width"})
public class MetaDto {

    private String id;

    @JsonProperty(value = "file_name")
    private String fileName;

    private int height;

    private int width;

}
