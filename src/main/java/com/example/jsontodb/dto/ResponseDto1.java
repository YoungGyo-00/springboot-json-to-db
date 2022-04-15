package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONArray;

@Data
@Builder
@JsonPropertyOrder({"info", "image", "annotations", "categories"})
public class ResponseDto1 {

    private InfoDto info;

    private MetaDto image;

    private JSONArray annotations;

    private JSONArray categories;

}
