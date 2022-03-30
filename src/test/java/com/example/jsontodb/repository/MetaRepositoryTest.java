package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Meta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MetaRepositoryTest {
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private LabelPathRepository labelPathRepository;

    @Transactional
    @Test
    void dbTest(){
        Meta meta = new Meta();

        meta.setData_key("dwad");

        LabelPath labelPath = new LabelPath();

        labelPath.setLabel_path("dwaddwa");

        meta.addLabelPath(labelPath);

        metaRepository.save(meta);
        labelPathRepository.save(labelPath);

        System.out.println(labelPathRepository.findAll());
        System.out.println(metaRepository.findAll());
    }
}