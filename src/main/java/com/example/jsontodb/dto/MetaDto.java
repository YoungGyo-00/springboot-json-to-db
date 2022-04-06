package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaDto {

    @JsonProperty(value = "meta_id")
    private String id;

    private ImageDto image_info;

    @JsonProperty(value = "label_id")
    private String labelId;

    @Data
    @Builder
    public static class ImageDto{

        private int height;

        private int width;
    }
}
