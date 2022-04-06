package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.dto.MetaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetaMapper extends GenericMapper<MetaDto, Meta>{
    @Override
    MetaDto toDto(Meta meta);
}
