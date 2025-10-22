package com.educacionit.sistemaeducativo.entidades;

/**
 * Entidad que representa una Correlatividad entre cursos.
 * Un curso puede requerir que otro curso sea cursado/aprobado previamente.
 * 
 * @author Ludmila Martos
 */
public class Correlatividad {
    
    private Integer id;
    private Integer cursoId;              // Curso que tiene la correlativa
    private Integer correlativaId;        // Curso que es prerequisito
    private TipoCorrelatividad tipo;
    private String cursoNombre;            // Para mostrar en vistas
    private String correlativaNombre;      // Para mostrar en vistas
    
    // Enumerado
    public enum TipoCorrelatividad {
        REGULAR,    // Solo necesita haberlo cursado
        APROBADA    // Necesita haberlo aprobado
    }
    
    // Constructores
    public Correlatividad() {
        this.tipo = TipoCorrelatividad.APROBADA;
    }
    
    public Correlatividad(Integer cursoId, Integer correlativaId, TipoCorrelatividad tipo) {
        this.cursoId = cursoId;
        this.correlativaId = correlativaId;
        this.tipo = tipo;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCursoId() {
        return cursoId;
    }
    
    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }
    
    public Integer getCorrelativaId() {
        return correlativaId;
    }
    
    public void setCorrelativaId(Integer correlativaId) {
        this.correlativaId = correlativaId;
    }
    
    public TipoCorrelatividad getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoCorrelatividad tipo) {
        this.tipo = tipo;
    }
    
    public String getCursoNombre() {
        return cursoNombre;
    }
    
    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }
    
    public String getCorrelativaNombre() {
        return correlativaNombre;
    }
    
    public void setCorrelativaNombre(String correlativaNombre) {
        this.correlativaNombre = correlativaNombre;
    }
    
    @Override
    public String toString() {
        return "Correlatividad{" +
                "cursoId=" + cursoId +
                ", correlativaId=" + correlativaId +
                ", tipo=" + tipo +
                '}';
    }
}


