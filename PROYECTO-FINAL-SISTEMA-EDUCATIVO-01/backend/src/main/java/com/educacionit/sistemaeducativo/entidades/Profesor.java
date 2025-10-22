package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;

/**
 * Clase que representa un Profesor en el sistema educativo.
 * Hereda de Persona e incluye información laboral y académica.
 * 
 * @author Ludmila Martos
 */
public class Profesor extends Persona {
    private Integer personaId;
    private String codigoProfesor;
    private LocalDate fechaContratacion;
    private Double sueldo;
    private String especialidad;
    private String gradoAcademico;
    private String estadoLaboral;

    public Profesor() {
        super();
        this.estadoLaboral = "ACTIVO";
    }

    public Profesor(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, 
                    String apellido, LocalDate fechaNacimiento, String codigoProfesor,
                    LocalDate fechaContratacion, Double sueldo, String especialidad) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, fechaNacimiento);
        this.codigoProfesor = codigoProfesor;
        this.fechaContratacion = fechaContratacion;
        this.sueldo = sueldo;
        this.especialidad = especialidad;
        this.estadoLaboral = "ACTIVO";
    }

    @Override
    public String getTipoPersona() {
        return "Profesor";
    }

    // Getters y Setters
    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(String codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getEstadoLaboral() {
        return estadoLaboral;
    }

    public void setEstadoLaboral(String estadoLaboral) {
        this.estadoLaboral = estadoLaboral;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "codigo='" + codigoProfesor + '\'' +
                ", nombre='" + getNombreCompleto() + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", grado='" + gradoAcademico + '\'' +
                '}';
    }
}


