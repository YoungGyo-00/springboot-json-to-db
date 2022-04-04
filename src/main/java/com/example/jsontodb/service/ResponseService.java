package com.example.jsontodb.service;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.dto.ResponseDTO;
import com.example.jsontodb.repository.CategoryRepository;
import com.example.jsontodb.repository.MetaRepository;
import com.example.jsontodb.repository.ObjectRepository;
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
    public ResponseDTO response() {

        Meta meta = metaRepository.findByLabelId("fed25dc0-f196-405d-a189-d335af493e37");

        ResponseDTO responseDTO = ResponseDTO.builder()
                .id(meta.getId())
                .labelId(meta.getId())
                .height(meta.getHeight())
                .width(meta.getWidth())
                .build();

        return responseDTO;

    }
}
