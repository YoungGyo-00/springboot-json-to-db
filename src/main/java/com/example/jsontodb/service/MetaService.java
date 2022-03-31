package com.example.jsontodb.service;

import com.example.jsontodb.domain.ImageInfo;
import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.repository.ImageInfoRepository;
import com.example.jsontodb.repository.MetaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;
    private final ImageInfoRepository imageInfoRepository;

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
            ImageInfo imageInfo = new ImageInfo();

            imageInfo.setHeight((Long) image_info.get("height"));
            imageInfo.setWidth((Long) image_info.get("width"));

            meta.setDataKey((String) jsonObject.get("data_key"));
            meta.setLabelId((String) jsonObject.get("label_id"));
            meta.setLabelPath((String) label_path.get(0));
            meta.setImageInfo(imageInfo);

            metaRepository.save(meta);
            imageInfoRepository.save(imageInfo);
            return ;
        } catch (DataIntegrityViolationException e) {
            System.out.println(file + "의 파일은 이미 DB에 저장됨");
        }
    }
}
