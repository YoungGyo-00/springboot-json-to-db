package com.example.jsontodb.service;

import com.example.jsontodb.domain.Category;
import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.dto.*;
import com.example.jsontodb.repository.CategoryRepository;
import com.example.jsontodb.repository.MetaRepository;
import com.example.jsontodb.repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final CategoryRepository categoryRepository;
    private final ObjectRepository objectRepository;
    private final MetaRepository metaRepository;

    @Transactional
    public ResponseDto response() throws JsonProcessingException {

        Object object = objectRepository.findById("17318b2b-b743-4f05-8612-a8b78aaef99c").orElseThrow();
        Meta meta = metaRepository.findById(object.getMeta().getId()).orElseThrow();
        Category category = categoryRepository.findById(object.getCategory().getId()).orElseThrow();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object.getPoints());

        ResponseDto responseDTO = ResponseDto.builder()
                .id(object.getId())
                .propertyValue(object.getPropertyValue())
                .points(object.getPoints())
                .meta(MetaDto.builder()
                        .id(meta.getId())
                        .image_info(ImageDto.builder()
                                .height(meta.getHeight())
                                .width(meta.getWidth())
                                .build())
                        .labelId(meta.getLabelId())
                        .build())
                .category(CategoryDto.builder()
                        .id(category.getId())
                        .className(category.getClassName())
                        .propertyName(category.getPropertyName())
                        .annotationType(category.getAnnotationType())
                        .superCategory(category.getSuperCategory())
                        .build())
                .build();

        return responseDTO;

    }
}
