package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;
import com.educacionit.sistemaeducativo.enumerados.EstadoAcademico;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;

/**
 * Clase que representa un Estudiante en el sistema educativo.
 * Hereda de Persona e incluye información académica específica.
 * 
 * PROPÓSITO:
 * - Demuestra herencia: extiende Persona con atributos específicos de estudiante
 * - Implementa especialización: agrega funcionalidad académica
 * - Utiliza enumerados para controlar estados académicos válidos
 * - Demuestra encapsulación con atributos privados específicos
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Herencia: extends Persona
 * - Encapsulación: atributos private específicos
 * - Polimorfismo: override de getTipoPersona()
 * - Reutilización: usa métodos heredados de Persona
 * - Enumerados: EstadoAcademico para controlar valores válidos
 * 
 * @author Ludmila Martos
 */
public class Estudiante extends Persona {
    // Atributos específicos del estudiante (además de los heredados de Persona)
    private Integer personaId;              // Referencia al ID de la tabla personas
    private String matricula;                // Código único del estudiante en la institución
    private LocalDate fechaIngreso;          // Fecha en que ingresó a la institución
    private Double promedioGeneral;          // Promedio académico general del estudiante
    private Integer creditosCursados;        // Total de créditos aprobados por el estudiante
    private EstadoAcademico estadoAcademico; // Estado actual del estudiante (ACTIVO, GRADUADO, etc.)

    // CONSTRUCTORES
    /**
     * Constructor por defecto que inicializa valores académicos por defecto
     * Demuestra inicialización de valores específicos del estudiante
     */
    public Estudiante() {
        super();                                    // Llama al constructor de Persona
        this.promedioGeneral = 0.0;                // Promedio inicial en cero
        this.creditosCursados = 0;                  // Sin créditos cursados inicialmente
        this.estadoAcademico = EstadoAcademico.ACTIVO; // Estado activo por defecto
    }

    /**
     * Constructor completo que inicializa tanto datos de persona como de estudiante
     * Demuestra cómo combinar datos heredados con datos específicos
     */
    public Estudiante(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, 
                      String apellido, LocalDate fechaNacimiento, String matricula, LocalDate fechaIngreso) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, fechaNacimiento); // Llama constructor padre
        this.matricula = matricula;                 // Código único del estudiante
        this.fechaIngreso = fechaIngreso;           // Fecha de ingreso a la institución
        this.promedioGeneral = 0.0;                // Promedio inicial en cero
        this.creditosCursados = 0;                  // Sin créditos cursados inicialmente
        this.estadoAcademico = EstadoAcademico.ACTIVO; // Estado activo por defecto
    }

    // IMPLEMENTACIÓN DE MÉTODO ABSTRACTO (POLIMORFISMO)
    /**
     * Implementa el método abstracto de la clase padre
     * Demuestra polimorfismo: cada tipo de persona se identifica diferente
     * @return String que identifica este objeto como "Estudiante"
     */
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

    // REPRESENTACIÓN TEXTUAL
    /**
     * Representación textual específica del estudiante
     * Demuestra override de toString() con información académica relevante
     * @return String con información académica del estudiante
     */
    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula='" + matricula + '\'' +           // Código único del estudiante
                ", nombre='" + getNombreCompleto() + '\'' +  // Usa método heredado de Persona
                ", promedio=" + promedioGeneral +             // Promedio académico
                ", creditos=" + creditosCursados +           // Créditos cursados
                ", estado=" + estadoAcademico +              // Estado académico actual
                '}';
    }
}


