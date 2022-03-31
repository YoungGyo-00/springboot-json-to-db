package com.example.jsontodb.service;

import com.example.jsontodb.domain.*;
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

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final ObjectRepository objectRepository;
    private final CategoryRepository categoryRepository;
    private final PointRepository pointRepository;
    private final PropertyRepository propertyRepository;
    private final MetaRepository metaRepository;

    @Transactional
    public void save(String folder_path, String file) throws IOException, ParseException {
        try {
            String path = folder_path + file;
            String label_id = file.substring(0, file.length() - 5);

            Reader reader = new FileReader(path);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray objects = (JSONArray) jsonObject.get("objects");
            JSONObject categories = (JSONObject) jsonObject.get("categories");
            JSONArray category_properties = (JSONArray) categories.get("properties");

            Label label = new Label();

            label.setFileName(label_id);
            Meta meta = metaRepository.findByLabelId(label_id);
            label.setImageInfo(meta.getImageInfo());

            for (int i = 0; i < objects.size(); i++) {

                Object object = new Object();

                JSONObject object_info = (JSONObject) objects.get(i);
                JSONArray object_properties = (JSONArray) object_info.get("properties");
                JSONObject annotation = (JSONObject) object_info.get("annotation");
                JSONObject coord = (JSONObject) annotation.get("coord");
                JSONArray temp1 = (JSONArray) coord.get("points");
                JSONArray temp2 = (JSONArray) temp1.get(0);
                JSONArray points = (JSONArray) temp2.get(0);

                for (int j = 0; j < object_properties.size(); j++) {

                    Property property = new Property();

                    JSONObject property_info = (JSONObject) object_properties.get(j);

                    property.setValue((String) property_info.get("value"));
                    property.setPropertyName((String) property_info.get("property_name"));

                    object.addProperty(property);

                    propertyRepository.save(property);
                }

                for (int j = 0; j < points.size(); j++) {

                    Point point = new Point();

                    JSONObject point_info = (JSONObject) points.get(j);

                    point.setX((Double) point_info.get("x"));
                    point.setY((Double) point_info.get("y"));

                    object.addPoint(point);
                    pointRepository.save(point);
                }

                object.setClassName((String) object_info.get("class_name"));
                object.setAnnotationType((String) object_info.get("annotation_type"));

                label.addObject(object);
                objectRepository.save(object);
            }

            for (int i = 0; i < category_properties.size(); i++) {

                Category category = new Category();

                JSONObject properties = (JSONObject) category_properties.get(i);

                category.setPropertyName((String) properties.get("property_id"));
                category.setValue((String) properties.get("value"));

                label.addCategory(category);
                categoryRepository.save(category);
            }

            labelRepository.save(label);
        } catch (NullPointerException e) {
            System.out.println(file + " label 파일은 meta 파일이 없음");
        }
    }
}
