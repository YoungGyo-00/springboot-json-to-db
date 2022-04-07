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
    public void save(String path) throws IOException, ParseException {
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
                JSONArray temp1 = (JSONArray) coord.get("points");
                JSONArray temp2 = (JSONArray) temp1.get(0);
                JSONArray points = (JSONArray) temp2.get(0);

                // 값이 없는 것도 있음
                JSONArray properties = (JSONArray) object_info.get("properties");

                int property_value = -1;

                if (properties.size() != 0) {
                    JSONObject property = (JSONObject) properties.get(0);
                    try {
                        property_value = Integer.parseInt(String.valueOf(property.get("value")));
                    } catch (NumberFormatException e) {
                        property_value = -2;
                    }
                }

                JSONArray point = new JSONArray();
                for (java.lang.Object p : points) {
                    JSONObject point_info = (JSONObject) p;
                    JSONObject point_temp = new JSONObject();

                    int x = (int) Math.round(Double.parseDouble(String.valueOf(point_info.get("x"))));
                    int y = (int) Math.round(Double.parseDouble(String.valueOf(point_info.get("y"))));

                    point_temp.put("x", x);
                    point_temp.put("y", y);

                    point.add(point_temp);
                }

                // object file name
                String[] file_arr = path.split("\\\\");
                String file = file_arr[file_arr.length - 1];

                Project project = categoryRepository.findByClassName((String) object_info.get("class_name"));
                Meta meta = metaRepository.findByLabelId(file.substring(0, file.length() - 5));

                object.setPoints(point.toString());
                object.setId((String) object_info.get("id"));
                object.setPropertyValue(property_value);
                object.setProject(project);
                object.setMeta(meta);

                objectRepository.save(object).getId();
            }

        } catch (NullPointerException e) {
            System.out.println(path + " 파일은 meta 파일이 예외");
        } catch (IndexOutOfBoundsException e){
            System.out.println(path + " 파일은 인덱스 바운드 예외");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
