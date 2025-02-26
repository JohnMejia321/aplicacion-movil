package com.example.repository;

import com.example.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c WHERE c.vigencia = true")
    Iterable<Categoria> listarCategoriasActivas();

    @Transactional
    @Modifying
    @Query("UPDATE Categoria c SET c.vigencia = false WHERE c.id = :id")
    void desactivar(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Categoria c SET c.vigencia = true WHERE c.id = :id")
    void activar(@Param("id") Integer id);
}
