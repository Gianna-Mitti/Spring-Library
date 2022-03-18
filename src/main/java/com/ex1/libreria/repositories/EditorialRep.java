package com.ex1.libreria.repositories;

import com.ex1.libreria.entities.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRep extends JpaRepository<Editorial, String> {

@Query("SELECT e FROM Editorial e WHERE e.id = :id")
public List<Editorial> buscarxId(@Param("id") String id);

@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
public List<Editorial> buscarxNombre(@Param("nombre") String nombre);
}
