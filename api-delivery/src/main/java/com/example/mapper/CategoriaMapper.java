package com.example.mapper;


import com.example.entity.Categoria;
import com.example.entity.dto.CategoriaDto;
import com.example.mapperImpl.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends GenericMapper<CategoriaDto, Categoria> {

    @Override
    CategoriaDto toDto(Categoria categoria);

    @Override
    Categoria toEntity(CategoriaDto categoriaDto);
}