package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.domain.Object;
import com.example.jsontodb.domain.Project;
import com.example.jsontodb.dto.MetaDto;
import com.example.jsontodb.dto.PointDto;
import com.example.jsontodb.dto.ProjectDto;
import com.example.jsontodb.dto.ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper extends GenericMapper<ResponseDto, Object> {
    @Override
    /*@Mappings({
            @Mapping(source = "meta", target = "meta"),
            @Mapping(source = "project", target = "project")})*/
    default ResponseDto toDto(Object object){
        if ( object == null ) {
            return null;
        }

        ResponseDto.ResponseDtoBuilder responseDto = ResponseDto.builder();

        responseDto.meta( metaToMetaDto( object.getMeta() ) );
        responseDto.project( projectToProjectDto( object.getProject() ) );
        responseDto.id( object.getId() );
        responseDto.propertyValue( object.getPropertyValue() );

        return responseDto.build();
    };

    default MetaDto metaToMetaDto(Meta meta) {
        if ( meta == null ) {
            return null;
        }

        MetaDto.MetaDtoBuilder metaDto = MetaDto.builder();

        metaDto.id( meta.getId() );
        metaDto.labelId( meta.getLabelId() );
        metaDto.image_info( imageInfoToImageDto( meta.getHeight(), meta.getWidth()));

        return metaDto.build();
    }

    default ProjectDto projectToProjectDto(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDto.ProjectDtoBuilder projectDto = ProjectDto.builder();

        projectDto.classId( project.getClassId() );
        projectDto.className( project.getClassName() );
        projectDto.superCategory( project.getSuperCategory() );
        projectDto.annotationType( project.getAnnotationType() );
        projectDto.propertyName( project.getPropertyName() );
        projectDto.propertyUnit( project.getPropertyUnit() );

        return projectDto.build();
    }

    default MetaDto.ImageDto imageInfoToImageDto(int height, int width) {

        MetaDto.ImageDto imageDto = MetaDto.ImageDto.builder()
                .height(height)
                .width(width)
                .build();

        return imageDto;
    }
}