package com.example.jsontodb.service;

import com.example.jsontodb.domain.Object;
import com.example.jsontodb.dto.*;
import com.example.jsontodb.mapper.ResponseMapper;
import com.example.jsontodb.repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResponseService {
    
    private final ObjectRepository objectRepository;
    private final ResponseMapper mapper;

    @Transactional
    public ResponseDto response() {

        Object object = objectRepository.findById("17318b2b-b743-4f05-8612-a8b78aaef99c").orElseThrow();

        ResponseDto responseDto = mapper.toDto(object);

        return responseDto;
    }
}
