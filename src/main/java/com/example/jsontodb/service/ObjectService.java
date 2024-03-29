package com.example.jsontodb.service;

import com.example.jsontodb.domain.Project;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
@RequiredArgsConstructor
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final MetaRepository metaRepository;
    private final ProjectRepository categoryRepository ;

    @Transactional
    public void save(String path) {
        try {
            Reader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray objects = (JSONArray) jsonObject.get("objects");

            for (java.lang.Object o : objects) {

                Object object = new Object();

                JSONObject object_info = (JSONObject) o;
                JSONObject annotation = (JSONObject) object_info.get("annotation");
                JSONObject coord = (JSONObject) annotation.get("coord");

                // 포인트가 없는 파일은 하나밖에 없어서 상의해봐야함
                JSONArray temp1 = (JSONArray) coord.get("points");
                JSONArray temp2 = (JSONArray) temp1.get(0);
                JSONArray points = (JSONArray) temp2.get(0);

                // 값이 없는 것도 있음, null 저장
                JSONArray properties = (JSONArray) object_info.get("properties");

                Integer property_value = null;

                if (properties.size() != 0) {
                    JSONObject property = (JSONObject) properties.get(0);
                    try {
                        property_value = Integer.parseInt(String.valueOf(property.get("value")));
                    } catch (NumberFormatException e) {
                    }
                }

                JSONArray point = new JSONArray();

                for (java.lang.Object p : points) {
                    JSONObject point_info = (JSONObject) p;
                    JSONObject point_temp = new JSONObject();

                    double x = Math.round(Double.parseDouble(String.valueOf(point_info.get("x")))*100.0)/100.0;
                    double y = Math.round(Double.parseDouble(String.valueOf(point_info.get("y")))*100.0)/100.0;

                    point_temp.put("x", x);
                    point_temp.put("y", y);

                    point.add(point_temp);
                }

                // object file name
                String[] file_arr = path.split("\\\\");
                String file = file_arr[file_arr.length - 1];

                // project name
                String[] project_names = path.split("\\\\");
                String project_name = project_names[project_names.length - 3].split(" ")[0];

                Project project = categoryRepository.findByClassId(project_name + "-" + object_info.get("class_id"));

                Meta meta = metaRepository.findByLabelId(file.substring(0, file.length() - 5));

                String id = project_name + "-" + object_info.get("id");

                // Entity 저장
                object.setId(id);
                object.setPoints(point.toJSONString());
                object.setPropertyValue(property_value);
                object.setMeta(meta);
                object.setProject(project);

                // DB 저장
                objectRepository.save(object);
            }

        } catch (NullPointerException e) {
            System.out.println(path + " 파일은 meta 파일이 예외");
        } catch (IndexOutOfBoundsException e){
            System.out.println(path + " 파일은 인덱스 바운드 예외");
        } catch (FileNotFoundException e) {
            System.out.println(path + " 파일을 못 찾는 예외");
        } catch (IOException e) {
            System.out.println(path + " 파일은 IOException");
        } catch (ParseException e) {
            System.out.println(path + " 파일은 ParseException");
        }
    }
}
