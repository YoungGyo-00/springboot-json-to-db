package com.example.jsontodb.service;

import com.example.jsontodb.controller.ApiController;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.repository.CategoryRepository;
import com.example.jsontodb.repository.MetaRepository;
import com.example.jsontodb.repository.ObjectRepository;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.UnexpectedRollbackException;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ObjectServiceTest {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private ApiController apiController;

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    @DisplayName("1. Meta value가 DB에 저장 잘 되는지")
    void metatest() throws IOException, ParseException {



    }
}