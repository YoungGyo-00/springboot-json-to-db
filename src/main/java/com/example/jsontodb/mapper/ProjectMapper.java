package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Project;
import com.example.jsontodb.dto.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends GenericMapper<ProjectDto, Project>{
    @Override
    ProjectDto toDto(Project project);
}
