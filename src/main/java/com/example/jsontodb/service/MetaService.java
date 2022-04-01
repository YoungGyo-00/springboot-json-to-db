package com.example.jsontodb.service;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.repository.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;

    @Transactional
    public void save(String folder_path, String file) throws IOException, ParseException {
        try{
            String path = folder_path + file;
            Reader reader = new FileReader(path);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray label_path = (JSONArray) jsonObject.get("label_path");
            JSONObject image_info = (JSONObject) jsonObject.get("image_info");

            Meta meta = new Meta();

            meta.setHeight(Integer.parseInt(String.valueOf(image_info.get("height"))));
            meta.setWidth(Integer.parseInt(String.valueOf(image_info.get("width"))));

            meta.setId(jsonObject.get("dataset") + "-" + jsonObject.get("data_key"));
            meta.setLabelId((String) jsonObject.get("label_id"));
            meta.setLabelPath((String) label_path.get(0));

            metaRepository.save(meta);
            return ;
        } catch (DataIntegrityViolationException e) {
            System.out.println(file + "의 파일은 이미 DB에 저장됨");
        }
    }
}
