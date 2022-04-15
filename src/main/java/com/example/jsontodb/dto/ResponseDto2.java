package com.example.jsontodb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"info", "image", "annotations", "categories"})
public class ResponseDto2 {

    private JSONObject info;

    private JSONArray image;

    private JSONArray annotations;

    private JSONArray categories;

}
