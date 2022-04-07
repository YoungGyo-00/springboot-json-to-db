package com.example.jsontodb.base;

import com.example.jsontodb.service.ProjectService;
import com.example.jsontodb.service.MetaService;
import com.example.jsontodb.service.ObjectService;
import com.example.jsontodb.service.ResponseService;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Disabled
@AutoConfigureMockMvc
@Transactional
public class BaseIntegrationTest {
    @Autowired
    protected static MockMvc mvc;
    @MockBean
    private MetaService metaService;
    @MockBean
    private ObjectService objectService;
    @MockBean
    private ProjectService categoryService;
    @MockBean
    private ResponseService responseService;
}
