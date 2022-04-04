package com.example.jsontodb.service;

import com.example.jsontodb.domain.Category;
import com.example.jsontodb.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(String folder_path) throws IOException, ParseException {
        String path = folder_path + "project.json";
        Reader reader = new FileReader(path);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONObject object_detection = (JSONObject) jsonObject.get("object_detection");
        JSONArray object_classes = (JSONArray) object_detection.get("object_classes");

        for (Object o : object_classes){
            Category category = new Category();

            JSONObject object_class = (JSONObject) o;
            JSONArray properties = (JSONArray) object_class.get("properties");

            for(Object p : properties) {
                JSONObject property = (JSONObject) p;

                category.setPropertyName((String) property.get("name"));
            }

            category.setId((String) object_class.get("id"));
            category.setClassName((String) object_class.get("name"));
            category.setAnnotationType((String) object_class.get("annotation_type"));

            categoryRepository.save(category);
        }

    }
}
