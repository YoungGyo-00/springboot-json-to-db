package com.example.jsontodb.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ResponseDTO {
    private String id;

    private String labelId;

    private int height;

    private int width;
}
