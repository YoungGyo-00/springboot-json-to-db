package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Object;
import com.example.jsontodb.dto.ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ResponseMapper extends GenericMapper<ResponseDto, Object> {
    @Override
    @Mappings({
            @Mapping(source = "meta", target = "metaDto"),
            @Mapping(source = "category", target = "categoryDto")})
    ResponseDto toDto(Object object);
}