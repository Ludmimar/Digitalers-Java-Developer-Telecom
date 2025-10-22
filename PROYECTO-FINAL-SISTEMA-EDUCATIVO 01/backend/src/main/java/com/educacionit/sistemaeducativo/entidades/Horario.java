package com.educacionit.sistemaeducativo.entidades;

import java.sql.Time;

/**
 * Entidad que representa un Horario de un curso ofrecido.
 * Un curso puede tener múltiples horarios (diferentes días).
 * 
 * @author Ludmila Martos
 */
public class Horario {
    
    private Integer id;
    private Integer cursoOfrecidoId;
    private DiaSemana diaSemana;
    private Time horaInicio;
    private Time horaFin;
    
    // Enumerado para días de la semana
    public enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }
    
    // Constructores
    public Horario() {
    }
    
    public Horario(Integer cursoOfrecidoId, DiaSemana diaSemana, Time horaInicio, Time horaFin) {
        this.cursoOfrecidoId = cursoOfrecidoId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCursoOfrecidoId() {
        return cursoOfrecidoId;
    }
    
    public void setCursoOfrecidoId(Integer cursoOfrecidoId) {
        this.cursoOfrecidoId = cursoOfrecidoId;
    }
    
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }
    
    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    public Time getHoraInicio() {
        return horaInicio;
    }
    
    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }
    
    public Time getHoraFin() {
        return horaFin;
    }
    
    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }
    
    /**
     * Verifica si este horario se solapa con otro.
     */
    public boolean sesolapaCon(Horario otro) {
        if (!this.diaSemana.equals(otro.diaSemana)) {
            return false; // Diferentes días, no se solapan
        }
        
        // Verificar solapamiento de horarios en el mismo día
        return (this.horaInicio.before(otro.horaFin) && this.horaFin.after(otro.horaInicio));
    }
    
    @Override
    public String toString() {
        return "Horario{" +
                "dia=" + diaSemana +
                ", inicio=" + horaInicio +
                ", fin=" + horaFin +
                '}';
    }
}


