package com.example.repository;

import com.example.entity.Platillo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlatilloRepository extends CrudRepository<Platillo, Integer> {

    @Query("SELECT P FROM Platillo P WHERE P.recomendado = TRUE") // ✅ Consulta corregida
    Iterable<Platillo> listarPlatillosRecomendados();

    @Query("SELECT P FROM Platillo P WHERE P.categoria.id = :idC")
    Iterable<Platillo> listarPlatillosPorCategoria(int idC);

    @Modifying
    @Query("UPDATE Platillo P SET P.stock = P.stock - :cant WHERE P.id = :id")
    void descontarStock(int cant, int id);

    @Modifying
    @Query("UPDATE Platillo P SET P.stock = P.stock + :cant WHERE P.id = :id")
    void aumentarStock(int cant, int id);
}
