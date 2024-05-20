package com.aluraLibros.repository;

import com.aluraLibros.models.Autor;
import com.aluraLibros.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryLibros extends JpaRepository<Libro, Long> {
    List<Libro> findAll();

    @Query("SELECT a FROM Autor a")
    List<Autor> findAllAutores();

    @Query("SELECT a FROM Autor a WHERE fecha_fallecimiento < :fecha")
    List<Autor> consultarAutoresVivos(int fecha);

    @Query("SELECT l FROM Libro l JOIN l.idiomas idioma WHERE idioma = :idiomaSeleccionado")
    List<Libro> listaDelIdioma(String idiomaSeleccionado);
}
