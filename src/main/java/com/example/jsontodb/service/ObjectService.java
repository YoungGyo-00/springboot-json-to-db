package com.example.jsontodb.service;

import com.example.jsontodb.domain.Category;
import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.repository.*;
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
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final MetaRepository metaRepository;
    private final CategoryRepository categoryRepository ;

    @Transactional
    public void save(String folder_path, String file) throws IOException, ParseException {
        try {
            String path = folder_path + file;
            Reader reader = new FileReader(path);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray objects = (JSONArray) jsonObject.get("objects");

            for (java.lang.Object o : objects) {

                Object object = new Object();

                JSONObject object_info = (JSONObject) o;
                JSONObject annotation = (JSONObject) object_info.get("annotation");
                JSONObject coord = (JSONObject) annotation.get("coord");
                JSONArray temp1 = (JSONArray) coord.get("points");
                JSONArray temp2 = (JSONArray) temp1.get(0);
                JSONArray points = (JSONArray) temp2.get(0);
                JSONArray properties = (JSONArray) object_info.get("properties");
                JSONObject property = (JSONObject) properties.get(0);

                ArrayList<String> point = new ArrayList<>();

                for (java.lang.Object p : points) {
                    JSONObject point_info = (JSONObject) p;

                    String x = point_info.get("x").toString().substring(0,3);
                    String y = point_info.get("y").toString().substring(0,3);

                    point.add("\n x : " + x + " y : " + y);
                }

                point.add("\n");

                object.setPoints(point.toString());
                object.setId((String) object_info.get("id"));

                Category category = categoryRepository.findByClassName((String) object_info.get("class_name"));
                Meta meta = metaRepository.findByLabelId(file.substring(0, file.length() - 5));

                object.setPropertyValue(Integer.parseInt(String.valueOf(property.get("value"))));
                object.setCategory(category);
                object.setMeta(meta);

                objectRepository.save(object);
            }

        } catch (NullPointerException e) {
            System.out.println(file + " label 파일은 meta 파일이 없음");
        }
    }


}
