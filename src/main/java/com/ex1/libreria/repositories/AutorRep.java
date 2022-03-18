package com.ex1.libreria.repositories;

import com.ex1.libreria.entities.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRep extends JpaRepository<Autor, String>{

@Query("SELECT a FROM Autor a WHERE a.id = :id")
public List<Autor> buscarxId(@Param("id") String id);

@Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
public List<Autor> buscarxNombre(@Param("nombre") String nombre);
}
