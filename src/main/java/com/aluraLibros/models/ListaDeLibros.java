package com.aluraLibros.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListaDeLibros (
        @JsonAlias("results")
        List<DatosLibros> arrayLibros
) {



}
