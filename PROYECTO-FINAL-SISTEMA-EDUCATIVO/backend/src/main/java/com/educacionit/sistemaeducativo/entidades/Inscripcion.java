package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDateTime;
import com.educacionit.sistemaeducativo.enumerados.EstadoInscripcion;

/**
 * Clase que representa una Inscripción de un estudiante a un curso.
 * 
 * @author Ludmila Martos
 */
public class Inscripcion {
    private Integer id;
    private Integer estudianteId;
    private Integer cursoOfrecidoId;
    private LocalDateTime fechaInscripcion;
    private EstadoInscripcion estado;
    private Double notaFinal;
    private Double asistenciaPorcentaje;

    public Inscripcion() {
        this.estado = EstadoInscripcion.INSCRITO;
        this.fechaInscripcion = LocalDateTime.now();
    }

    public Inscripcion(Integer estudianteId, Integer cursoOfrecidoId) {
        this.estudianteId = estudianteId;
        this.cursoOfrecidoId = cursoOfrecidoId;
        this.estado = EstadoInscripcion.INSCRITO;
        this.fechaInscripcion = LocalDateTime.now();
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Integer getCursoOfrecidoId() {
        return cursoOfrecidoId;
    }

    public void setCursoOfrecidoId(Integer cursoOfrecidoId) {
        this.cursoOfrecidoId = cursoOfrecidoId;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Double getAsistenciaPorcentaje() {
        return asistenciaPorcentaje;
    }

    public void setAsistenciaPorcentaje(Double asistenciaPorcentaje) {
        this.asistenciaPorcentaje = asistenciaPorcentaje;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", estudianteId=" + estudianteId +
                ", estado=" + estado +
                ", notaFinal=" + notaFinal +
                ", asistencia=" + asistenciaPorcentaje + "%" +
                '}';
    }
}


