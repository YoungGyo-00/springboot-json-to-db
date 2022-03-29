package com.example.jsontodb.service;

import com.example.jsontodb.domain.ImageInfo;
import com.example.jsontodb.domain.LabelPath;
import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.repository.ImageInfoRepository;
import com.example.jsontodb.repository.LabelPathRepository;
import com.example.jsontodb.repository.MetaRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;
    private final LabelPathRepository labelPathRepository;
    private final ImageInfoRepository imageInfoRepository;

    @Transactional
    public void save(HashMap<Object, Object> params) throws IOException, ParseException {

        String path = "C:\\Users\\user\\Desktop\\A30049099001TNG001O001SH.jpg.json";
        Reader reader = new FileReader(path);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        Meta meta = new Meta();

        meta.setData_key((String) jsonObject.get("data_key"));
        meta.setLabel_id((String) jsonObject.get("label_id"));

        JSONArray label_path = (JSONArray) jsonObject.get("label_path");

        for (int i = 0; i < label_path.size(); i++) {
            LabelPath labelPath = new LabelPath();

            labelPath.setLabel_path((String) label_path.get(i));
            System.out.println(label_path.get(i));

            meta.addLabelPath(labelPath);
            labelPathRepository.save(labelPath);
        }

        ImageInfo imageInfo = new ImageInfo();

        JSONObject image_info = (JSONObject) jsonObject.get("image_info");

        imageInfo.setHeight((Long) image_info.get("height"));
        imageInfo.setWidth((Long) image_info.get("width"));

        meta.setImageInfo(imageInfo);
        imageInfo.setMeta(meta);

        System.out.println(meta);

        metaRepository.save(meta);
        imageInfoRepository.save(imageInfo);

        metaRepository.findAll().forEach(System.out::println);
    }
}
