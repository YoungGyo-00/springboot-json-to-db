package com.example.jsontodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {

    private int height;

    private int width;
}
