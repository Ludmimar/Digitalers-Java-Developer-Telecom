package com.educacionit.sistemaeducativo.enumerados;

/**
 * Enumerado que define los tipos de documentos de identidad válidos en el sistema.
 * 
 * @author Ludmila Martos
 */
public enum TipoDocumento {
    DNI("Documento Nacional de Identidad"),
    PASAPORTE("Pasaporte"),
    CI("Cédula de Identidad"),
    LE("Libreta de Enrolamiento"),
    LC("Libreta Cívica");
    
    private final String descripcion;
    
    TipoDocumento(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}


