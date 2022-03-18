package com.ex1.libreria.repositories;

import com.ex1.libreria.entities.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRep extends JpaRepository<Prestamo, String>{

@Query("SELECT p FROM Prestamo p WHERE p.id = :id")
public List<Prestamo> buscarxId(@Param("id") String id);

@Query("SELECT p FROM Prestamo p WHERE p.libro.isbn = :isbn")
public List<Prestamo> buscarxLibro(@Param("isbn") String isbn);

@Query("SELECT p FROM Prestamo p WHERE p.cliente.id = :id")
public List<Prestamo> buscarxCliente(@Param("id") String id);
}
