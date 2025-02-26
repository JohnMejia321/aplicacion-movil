package com.example.mapperImpl;

import com.example.entity.DocumentoAlmacenado;
import com.example.entity.dto.DocumentoAlmacenadoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentoAlmacenadoMapper extends GenericMapper<DocumentoAlmacenadoDto, DocumentoAlmacenado> {

    @Override
    DocumentoAlmacenadoDto toDto(DocumentoAlmacenado documentoAlmacenado);

    @Override
    DocumentoAlmacenado toEntity(DocumentoAlmacenadoDto documentoAlmacenadoDto);
}