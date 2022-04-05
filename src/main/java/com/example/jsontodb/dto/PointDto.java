package com.example.jsontodb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointDto {

    private int x;

    private int y;
}
