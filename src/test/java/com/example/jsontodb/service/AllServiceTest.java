package com.example.jsontodb.service;

import com.example.jsontodb.base.BaseIntegrationTest;
import com.example.jsontodb.domain.Project;
import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.repository.ProjectRepository;
import com.example.jsontodb.repository.MetaRepository;
import com.example.jsontodb.repository.ObjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@Transactional
class AllServiceTest extends BaseIntegrationTest {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private ProjectRepository categoryRepository;
    @Autowired
    private MetaRepository metaRepository;

    @Test
    @DisplayName("1. Meta value가 DB에 저장 잘 되는지")
    void metaService() {
        String id = "22XX-A22011000001TNV330O001LH.jpg";
        int height = 810;
        int width = 640;
        String label_id = "fed25dc0-f196-405d-a189-d335af493e37";

        Meta meta = metaRepository.findById(id).orElseThrow();

        assertEquals(meta.getHeight(), height);
        assertEquals(meta.getLabelId(), label_id);
        assertEquals(meta.getWidth(), width);
    }

    @Test
    @DisplayName("2. Object value가 DB에 저장 잘 되는지")
    void objectSerivce() {
        String id = "cf0523ba-d6e7-4f3e-b715-f0e4c9e5437d";
        int property_value = 355;
        String class_id = "628bf24e-ab75-4450-975c-616bd8a916de";
        String file = "fed25dc0-f196-405d-a189-d335af493e37";

        Object object = objectRepository.findById(id).orElseThrow();
        Meta meta = metaRepository.findByLabelId(file);

        assertEquals(object.getProject().getClassId(), class_id);
        assertEquals(object.getMeta().getId(), meta.getId());
        assertEquals(object.getPropertyValue(), property_value);

    }

    @Test
    @DisplayName("3. Category value가 잘 들어갔는지")
    void categoryService() {
        String id = "628bf24e-ab75-4450-975c-616bd8a916de";
        String annotation_type = "polygon";
        String class_name = "Mineral waters";
        String property_name = "Volume";

        Project category = categoryRepository.findById(id).orElseThrow();

        assertEquals(category.getAnnotationType(), annotation_type);
        assertEquals(category.getClassName(), class_name);
        assertEquals(category.getPropertyName(), property_name);
    }
}