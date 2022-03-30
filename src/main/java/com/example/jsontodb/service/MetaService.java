package com.example.jsontodb.service;

import com.example.jsontodb.domain.ImageInfo;
import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.repository.ImageInfoRepository;
import com.example.jsontodb.repository.MetaRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;
    private final ImageInfoRepository imageInfoRepository;

    @Transactional
    public void save() throws IOException, ParseException {

        String path = "C:\\Users\\user\\Desktop\\meta\\" +
                "A30049099001TNG001O001SH.jpg.json";
        Reader reader = new FileReader(path);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray label_path = (JSONArray) jsonObject.get("label_path");
        JSONObject image_info = (JSONObject) jsonObject.get("image_info");

        Meta meta = new Meta();
        ImageInfo imageInfo = new ImageInfo();

        imageInfo.setHeight((Long) image_info.get("height"));
        imageInfo.setWidth((Long) image_info.get("width"));

        meta.setData_key((String) jsonObject.get("data_key"));
        meta.setLabel_id((String) jsonObject.get("label_id"));
        meta.setLabel_path((String) label_path.get(0));
        meta.setImageInfo(imageInfo);

        imageInfo.setMeta(meta);

        metaRepository.save(meta);
        imageInfoRepository.save(imageInfo);

        metaRepository.findAll().forEach(System.out::println);
    }
}
