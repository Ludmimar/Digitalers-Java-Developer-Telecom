package com.educacionit.sistemaeducativo.enumerados;

/**
 * Enumerado que define los estados posibles de una inscripción a un curso.
 * 
 * @author Ludmila Martos
 */
public enum EstadoInscripcion {
    INSCRITO("Inscripción realizada"),
    CURSANDO("Cursando actualmente"),
    APROBADO("Curso aprobado"),
    REPROBADO("Curso reprobado"),
    RETIRADO("Estudiante retirado del curso");
    
    private final String descripcion;
    
    EstadoInscripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}


