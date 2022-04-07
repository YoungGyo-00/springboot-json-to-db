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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository categoryRepository;

    @Transactional
    public void save(String folder_path) throws IOException, ParseException {
        String path = folder_path + "project.json";
        Reader reader = new FileReader(path);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONObject object_detection = (JSONObject) jsonObject.get("object_detection");
        JSONArray object_classes = (JSONArray) object_detection.get("object_classes");

        for (Object o : object_classes){
            Project project = new Project();

            JSONObject object_class = (JSONObject) o;
            JSONArray properties = (JSONArray) object_class.get("properties");

            for(Object p : properties) {
                JSONObject property = (JSONObject) p;

                project.setPropertyName((String) property.get("name"));
            }

            project.setClassId((String) object_class.get("id"));
            project.setClassName((String) object_class.get("name"));
            project.setAnnotationType((String) object_class.get("annotation_type"));

            categoryRepository.save(project);
        }

    }
}
