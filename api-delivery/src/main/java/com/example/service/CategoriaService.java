package com.example.service;

import com.example.entity.Categoria;
import com.example.entity.dto.CategoriaDto;
import com.example.entity.filters.CategoriaFilter;
import com.example.repository.CategoriaRepository;
import com.example.spec.CategoriaSpec;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Order;
import com.example.utils.GenericResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.utils.Global.*;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaSpec spec;

    public CategoriaService(CategoriaRepository repository, CategoriaSpec spec) {
        this.repository = repository;
        this.spec = spec;
    }

    public GenericResponse listarCategoriasActivas() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.repository.listarCategoriasActivas());
    }

    public Page<CategoriaDto> findAll(Pageable page, CategoriaFilter filter) {
        if (page.getSort() == null) {
            List<Order> listaOrden = new ArrayList<>();
            listaOrden.add(new Order(Sort.Direction.ASC, Categoria.C_NOMBRE));
            Sort sort = Sort.by(listaOrden);
            page = PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
        }
        Page<Categoria> lista = repository.findAll(spec.filtrar(filter), page);
        return lista.map(this::convertirADto);
    }

    private CategoriaDto convertirADto(Categoria categoria) {
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.isVigencia() ? "Activo" : "Inactivo"
        );
    }

    public Integer save(CategoriaDto categoriaDto) {
        Categoria categoria = convertirAEntidad(categoriaDto);
        Categoria categoriaGuardada = repository.save(categoria);
        return categoriaGuardada.getId();
    }

    private Categoria convertirAEntidad(CategoriaDto dto) {
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNombre(dto.getNombre());
        categoria.setVigencia("Activo".equals(dto.getVigenciaString()));
        return categoria;
    }

    public CategoriaDto findCategoriaById(int id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        return convertirADto(categoria);
    }

    public void deleteCategoriaById(int id) {
        repository.deleteById(id);
    }

    public void activar(Integer id) {
        repository.activar(id);
    }

    public void desactivar(Integer id) {
        repository.desactivar(id);
    }
}
