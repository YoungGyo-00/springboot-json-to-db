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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;

    @Transactional
    public void save(String path) {
        try{
            Reader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            int height = 0;
            int width = 0;
            String time = (String) jsonObject.get("last_updated_date");

            try {
                JSONObject image_info = (JSONObject) jsonObject.get("image_info");

                height = Integer.parseInt(String.valueOf(image_info.get("height")));
                width = Integer.parseInt(String.valueOf(image_info.get("width")));
            } catch (NumberFormatException e){
                
            }

            Meta meta = new Meta();

            meta.setId(jsonObject.get("dataset") + "-" + jsonObject.get("data_key"));
            meta.setLabelId((String) jsonObject.get("label_id"));
            meta.setHeight(height);
            meta.setWidth(width);
            meta.setLabelingTime(LocalDateTime.parse(time.substring(0, time.length() - 1)));

            metaRepository.save(meta);

        } catch (DataIntegrityViolationException e) {
            System.out.println(path + "파일 DB에 저장됨");
        } catch (FileNotFoundException e) {
            System.out.println(path + " 파일을 못 찾는 예외");
        } catch (IOException e) {
            System.out.println(path + " 파일은 IOException");
        } catch (ParseException e) {
            System.out.println(path + " 파일은 ParseException");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
