package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;

/**
 * Entidad que representa un Período Académico en el sistema educativo.
 * 
 * @author Ludmila Martos
 */
public class PeriodoAcademico {
    
    private Integer id;
    private String nombre;
    private Integer anio;
    private String semestre; // "1" o "2"
    private Boolean activo;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaFinInscripciones;
    private LocalDate fechaInicioClases;
    private LocalDate fechaFinClases;
    private String descripcion;
    private EstadoPeriodo estado;
    
    // Enumerado interno
    public enum EstadoPeriodo {
        PLANIFICACION, INSCRIPCION, CURSANDO, FINALIZADO
    }
    
    // Constructores
    public PeriodoAcademico() {
        this.activo = false;
        this.estado = EstadoPeriodo.PLANIFICACION;
    }
    
    public PeriodoAcademico(String nombre, Integer anio, String semestre) {
        this();
        this.nombre = nombre;
        this.anio = anio;
        this.semestre = semestre;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getAnio() {
        return anio;
    }
    
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    
    public String getSemestre() {
        return semestre;
    }
    
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }
    
    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }
    
    public LocalDate getFechaFinInscripciones() {
        return fechaFinInscripciones;
    }
    
    public void setFechaFinInscripciones(LocalDate fechaFinInscripciones) {
        this.fechaFinInscripciones = fechaFinInscripciones;
    }
    
    public LocalDate getFechaInicioClases() {
        return fechaInicioClases;
    }
    
    public void setFechaInicioClases(LocalDate fechaInicioClases) {
        this.fechaInicioClases = fechaInicioClases;
    }
    
    public LocalDate getFechaFinClases() {
        return fechaFinClases;
    }
    
    public void setFechaFinClases(LocalDate fechaFinClases) {
        this.fechaFinClases = fechaFinClases;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public EstadoPeriodo getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPeriodo estado) {
        this.estado = estado;
    }
    
    // Métodos de utilidad
    
    /**
     * Retorna el nombre completo del período (nombre + año).
     */
    public String getNombreCompleto() {
        return nombre + " - " + anio;
    }
    
    /**
     * Verifica si el período está activo.
     */
    public boolean estaActivo() {
        return activo != null && activo;
    }
    
    /**
     * Verifica si el período está aceptando inscripciones.
     */
    public boolean aceptaInscripciones() {
        if (estado != EstadoPeriodo.INSCRIPCION) {
            return false;
        }
        
        LocalDate hoy = LocalDate.now();
        return (fechaInicioInscripciones == null || !hoy.isBefore(fechaInicioInscripciones)) &&
               (fechaFinInscripciones == null || !hoy.isAfter(fechaFinInscripciones));
    }
    
    /**
     * Verifica si el período ya finalizó.
     */
    public boolean haFinalizado() {
        return estado == EstadoPeriodo.FINALIZADO;
    }
    
    /**
     * Obtiene el rango de fechas de inscripción como string.
     */
    public String getRangoInscripciones() {
        if (fechaInicioInscripciones != null && fechaFinInscripciones != null) {
            return fechaInicioInscripciones + " al " + fechaFinInscripciones;
        }
        return "No definido";
    }
    
    /**
     * Obtiene el rango de fechas de clases como string.
     */
    public String getRangoClases() {
        if (fechaInicioClases != null && fechaFinClases != null) {
            return fechaInicioClases + " al " + fechaFinClases;
        }
        return "No definido";
    }
    
    @Override
    public String toString() {
        return "PeriodoAcademico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", semestre='" + semestre + '\'' +
                ", activo=" + activo +
                ", estado=" + estado +
                '}';
    }
}


