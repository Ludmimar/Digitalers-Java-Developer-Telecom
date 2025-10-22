package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;
import com.educacionit.sistemaeducativo.enumerados.EstadoAcademico;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;

/**
 * Clase que representa un Estudiante en el sistema educativo.
 * Hereda de Persona e incluye información académica específica.
 * 
 * @author Ludmila Martos
 */
public class Estudiante extends Persona {
    private Integer personaId;
    private String matricula;
    private LocalDate fechaIngreso;
    private Double promedioGeneral;
    private Integer creditosCursados;
    private EstadoAcademico estadoAcademico;

    // Constructores
    public Estudiante() {
        super();
        this.promedioGeneral = 0.0;
        this.creditosCursados = 0;
        this.estadoAcademico = EstadoAcademico.ACTIVO;
    }

    public Estudiante(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, 
                      String apellido, LocalDate fechaNacimiento, String matricula, LocalDate fechaIngreso) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, fechaNacimiento);
        this.matricula = matricula;
        this.fechaIngreso = fechaIngreso;
        this.promedioGeneral = 0.0;
        this.creditosCursados = 0;
        this.estadoAcademico = EstadoAcademico.ACTIVO;
    }

    @Override
    public String getTipoPersona() {
        return "Estudiante";
    }

    // Getters y Setters
    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getPromedioGeneral() {
        return promedioGeneral;
    }

    public void setPromedioGeneral(Double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    public Integer getCreditosCursados() {
        return creditosCursados;
    }

    public void setCreditosCursados(Integer creditosCursados) {
        this.creditosCursados = creditosCursados;
    }

    public EstadoAcademico getEstadoAcademico() {
        return estadoAcademico;
    }

    public void setEstadoAcademico(EstadoAcademico estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + getNombreCompleto() + '\'' +
                ", promedio=" + promedioGeneral +
                ", creditos=" + creditosCursados +
                ", estado=" + estadoAcademico +
                '}';
    }
}


