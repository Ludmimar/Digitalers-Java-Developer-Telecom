package com.educacionit.sistemaeducativo.entidades;

import java.util.Objects;

/**
 * Clase que representa un Curso en el sistema educativo.
 * 
 * @author Ludmila Martos
 */
public class Curso {
    private Integer id;
    private String codigoCurso;
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer horasSemanales;
    private Integer cupoMaximo;
    private String estado;

    public Curso() {
        this.creditos = 3;
        this.horasSemanales = 4;
        this.cupoMaximo = 30;
        this.estado = "ACTIVO";
    }

    public Curso(String codigoCurso, String nombre, String descripcion, 
                 Integer creditos, Integer horasSemanales) {
        this.codigoCurso = codigoCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.cupoMaximo = 30;
        this.estado = "ACTIVO";
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(codigoCurso, curso.codigoCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCurso);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigoCurso + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", horas=" + horasSemanales +
                '}';
    }
}


