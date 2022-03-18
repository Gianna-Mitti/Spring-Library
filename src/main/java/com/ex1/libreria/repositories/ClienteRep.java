package com.ex1.libreria.repositories;

import com.ex1.libreria.entities.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRep extends JpaRepository<Cliente, String> {

@Query("SELECT c FROM Cliente c WHERE c.id = :id")
public List<Cliente> buscarxId(@Param("id") String id);

@Query("SELECT c FROM Cliente c WHERE c.dni = :dni")
public List<Cliente> buscarxDNI(@Param("dni") Long dni);
}
