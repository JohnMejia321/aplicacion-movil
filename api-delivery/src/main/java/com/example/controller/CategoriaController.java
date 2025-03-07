package com.example.controller;


import com.example.entity.dto.CategoriaDto;
import com.example.entity.filters.CategoriaFilter;
import com.example.exception.UnprocessableEntityException;
import com.example.service.CategoriaService;
import com.example.utils.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public GenericResponse listarCategoriasActivas() {
        return this.service.listarCategoriasActivas();
    }

    @PostMapping("/filtrar")
    public ResponseEntity<Page<CategoriaDto>> filtrar(Pageable pageRequest,
    @RequestBody(required = false) CategoriaFilter filter) {
        return ResponseEntity.ok(this.service.findAll(pageRequest, filter));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable(required = true) int id) {
        CategoriaDto categoriaDto = this.service.findCategoriaById(id);
        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody CategoriaDto categoriaDto) {
        if (categoriaDto.getId() != null) {
            throw new UnprocessableEntityException();
        }
        Integer id = this.service.save(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);

    }

    @PutMapping(value = "/{categoriaInfo}")
    public ResponseEntity<Integer> update(@PathVariable(value = "categoriaInfo") Integer id,
                                           @RequestBody CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Integer categoriaid = this.service.save(categoriaDto);
        return ResponseEntity.ok(categoriaid);
    }

    @DeleteMapping("/deleteCategoria/{categoriaId}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer categoriaId) {
        if (categoriaId == null) {
            throw new UnprocessableEntityException();
        }
        this.service.deleteCategoriaById(categoriaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/desactivar/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable(required = false) Integer id) {
        this.service.desactivar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activar/{id}")
    public ResponseEntity<Void> activar(@PathVariable(required = false) Integer id) {
        this.service.activar(id);
        return ResponseEntity.ok().build();
    }
}