package com.example.jsontodb.mapper;

import com.example.jsontodb.domain.Meta;
import com.example.jsontodb.dto.MetaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MetaMapper extends GenericMapper<MetaDto, Meta>{
    @Override
    default MetaDto toDto(Meta meta) {
        if ( meta == null ) {
            return null;
        }

        MetaDto.MetaDtoBuilder metaDto = MetaDto.builder();

        metaDto.id( meta.getId() );
        metaDto.labelId( meta.getLabelId() );
        metaDto.image_info( imageInfoToImageDto( meta.getHeight(), meta.getWidth()));

        return metaDto.build();
    };

    default MetaDto.ImageDto imageInfoToImageDto(int height, int width) {

        MetaDto.ImageDto imageDto = MetaDto.ImageDto.builder()
                .height(height)
                .width(width)
                .build();

        return imageDto;
    }
}
