package com.example.spec;

import com.example.entity.Categoria;
import com.example.entity.filters.CategoriaFilter;
import org.springframework.data.jpa.domain.Specification;

public interface CategoriaSpec {

    Specification<Categoria> filtrar (CategoriaFilter filter);
}