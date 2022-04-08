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

    private final ProjectRepository projectRepository;

    @Transactional
    public void save(String path) {
        try{
            Reader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONObject object_detection = (JSONObject) jsonObject.get("object_detection");
            JSONArray object_classes = (JSONArray) object_detection.get("object_classes");

            for (Object o : object_classes){
                Project project = new Project();

                JSONObject object_class = (JSONObject) o;
                JSONArray properties = (JSONArray) object_class.get("properties");
                JSONObject property = (JSONObject) properties.get(0);

                String property_name = (String) property.get("name");
                String property_unit = "";
                switch (property_name) {
                    case "Volume" : property_unit = "cm";
                        break;
                    case "Length" : property_unit = "wh";
                        break;
                    case "Watt-hour" : property_unit = "wh";
                        break;
                    case "Gram" : property_unit = "Gram";
                        break;
                    case "Gravity" : property_unit = "Gr";
                        break;
                    case "Other beverage Property" : property_unit = "Oh";
                        break;
                    case "Instant curry Property" : property_unit = "I";
                        break;
                    case "Other glue, adhesive Property" : property_unit = "O";
                        break;
                    case "Null" : property_unit = "N";
                        break;
                    default: property_unit = "?";
                }

                // project DB 저장
                project.setClassId((String) object_class.get("id"));
                project.setClassName((String) object_class.get("name"));
                project.setSuperCategory(null);
                project.setAnnotationType((String) object_class.get("annotation_type"));
                project.setPropertyName(property_name);
                project.setPropertyUnit(property_unit);

                projectRepository.save(project);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
