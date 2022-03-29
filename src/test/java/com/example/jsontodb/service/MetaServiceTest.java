package com.example.jsontodb.service;

import com.example.jsontodb.repository.MetaRepository;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MetaServiceTest {
    @Autowired
    private MetaService metaService;
    @Autowired
    private MetaRepository metaRepository;

    @Test
    public void saveTest() throws IOException, ParseException {

    }
}