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

    @Transactional
    @Test
    void dbTest(){

    }
}