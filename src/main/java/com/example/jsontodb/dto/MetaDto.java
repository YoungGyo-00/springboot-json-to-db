package com.example.jsontodb.dto;

import com.example.jsontodb.domain.Object;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MetaDto {

    @JsonProperty(value = "meta_id")
    private String id;

    private int height;

    private int width;
//    private ImageDto image_info;

    @JsonProperty(value = "label_id")
    private String labelId;

    /*@Data
    @Builder
    public static class ImageDto{

        private int height;

        private int width;
    }*/
}
