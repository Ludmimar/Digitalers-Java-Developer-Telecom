package com.educacionit.sistemaeducativo.enumerados;

/**
 * Enumerado que define los posibles estados académicos de un estudiante.
 * 
 * @author Ludmila Martos
 */
public enum EstadoAcademico {
    ACTIVO("Estudiante activo cursando"),
    INACTIVO("Estudiante inactivo temporalmente"),
    GRADUADO("Estudiante graduado"),
    SUSPENDIDO("Estudiante suspendido");
    
    private final String descripcion;
    
    EstadoAcademico(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}


