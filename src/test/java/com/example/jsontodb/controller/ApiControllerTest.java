package com.example.jsontodb.controller;

import com.example.jsontodb.service.CategoryService;
import com.example.jsontodb.service.MetaService;
import com.example.jsontodb.service.ObjectService;
import com.example.jsontodb.service.ResponseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private MetaService metaService;
    @MockBean
    private ObjectService objectService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private ResponseService responseService;

    @Test
    void test() throws Exception {
        mvc.perform(get("/api/meta"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
