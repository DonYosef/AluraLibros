package com.aluraLibros.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "libro_id")
    private List<Autor> arrayAutor;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;
    @Column(name = "total_descargas")
    private int totalDescargas;

    public Libro() {
    }

    public Libro(String titulo, List<Autor> arrayAutor, List<String> idiomas, int totalDescargas) {
        this.titulo = titulo;
        this.arrayAutor = arrayAutor;
        this.idiomas = idiomas;
        this.totalDescargas = totalDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getArrayAutor() {
        return arrayAutor;
    }

    public void setArrayAutor(List<Autor> arrayAutor) {
        this.arrayAutor = arrayAutor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(int totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                "arrayAutor=" + arrayAutor +
                "idiomas=" + idiomas +
                "totalDescargas=" + totalDescargas;
    }
}
