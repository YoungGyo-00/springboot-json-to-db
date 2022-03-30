package com.example.jsontodb.service;

import com.example.jsontodb.domain.Project;
import com.example.jsontodb.repository.ProjectRepository;
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

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void save() throws IOException, ParseException {
        String path = "C:\\Users\\user\\Desktop\\project.json";

        Reader reader = new FileReader(path);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONObject object_detection = (JSONObject) jsonObject.get("object_detection");
        JSONArray object_classes = (JSONArray) object_detection.get("object_classes");

        for (int i = 0; i < object_classes.size(); i++) {
            Project project = new Project();

            JSONObject object_classes_info = (JSONObject) object_classes.get(i);

            project.setClass_name((String) object_classes_info.get("name"));
            project.setAnnotation_type((String) object_classes_info.get("annotation_type"));

            JSONArray properties = (JSONArray) object_classes_info.get("properties");

            for (int j = 0; j < properties.size(); j++) {
                JSONObject property = (JSONObject) properties.get(j);

                project.setProperty((String) property.get("name"));
            }

            projectRepository.save(project);
        }
    }
}
