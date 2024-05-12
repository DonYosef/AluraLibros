package com.aluraLibros.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros (
        @JsonAlias("id")
        int id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<DatosAutor> arrayAutor,
        @JsonAlias("download_count")
        int totalDescargas
) {


}
