package com.ex1.libreria.repositories;

import com.ex1.libreria.entities.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRep extends JpaRepository<Libro, String>{

@Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
public List<Libro> buscarxISBN(@Param("isbn") String isbn);

@Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
public List<Libro> buscarxTitulo(@Param("titulo") String titulo);

@Query("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo%")
public List<Libro> buscarxSaga(@Param("titulo") String titulo);

@Query("SELECT l FROM Libro l WHERE l.autor = :autor")
public List<Libro> buscarxAutor(@Param("autor") String autor);

@Query("SELECT l FROM Libro l WHERE l.editorial = :editorial")
public List<Libro> buscarxEditorial(@Param("editorial") String editorial);

@Query("SELECT l FROM Libro l")
public List<Libro> mostrar(@Param("isbn") String isbn);
}
