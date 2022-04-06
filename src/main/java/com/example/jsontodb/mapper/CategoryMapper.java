package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Category;
import com.example.jsontodb.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryDto, Category>{
    @Override
    CategoryDto toDto(Category category);
}
