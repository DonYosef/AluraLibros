package com.aluraLibros.models;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private int fecha_nacimiento;
    @Column(name = "fecha_fallecimiento")
    private int fecha_fallecimiento;

    public Autor() {
    }

    public Autor(String nombre, int fecha_nacimiento, int fecha_fallecimiento) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_fallecimiento = fecha_fallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(int fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getFecha_fallecimiento() {
        return fecha_fallecimiento;
    }

    public void setFecha_fallecimiento(int fecha_fallecimiento) {
        this.fecha_fallecimiento = fecha_fallecimiento;
    }

    @Override
    public String toString() {
        return  ", nombre autor='" + nombre + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", fecha_fallecimiento=" + fecha_fallecimiento +
                '}';
    }
}
