package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;

/**
 * Clase que representa un Profesor en el sistema educativo.
 * Hereda de Persona e incluye información laboral y académica.
 * 
 * PROPÓSITO:
 * - Demuestra herencia: extiende Persona con atributos específicos de profesor
 * - Implementa especialización: agrega funcionalidad laboral y académica
 * - Demuestra encapsulación con atributos privados específicos
 * - Representa información de recursos humanos del profesorado
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Herencia: extends Persona
 * - Encapsulación: atributos private específicos
 * - Polimorfismo: override de getTipoPersona()
 * - Reutilización: usa métodos heredados de Persona
 * - Modelado de dominio: información laboral específica
 * 
 * @author Ludmila Martos
 */
public class Profesor extends Persona {
    // Atributos específicos del profesor (además de los heredados de Persona)
    private Integer personaId;              // Referencia al ID de la tabla personas
    private String codigoProfesor;          // Código único del profesor en la institución
    private LocalDate fechaContratacion;    // Fecha en que fue contratado
    private Double sueldo;                  // Salario del profesor
    private String especialidad;            // Área de especialización académica
    private String gradoAcademico;          // Nivel académico más alto alcanzado
    private String estadoLaboral;           // Estado laboral actual (ACTIVO, LICENCIA, etc.)

    // CONSTRUCTORES
    /**
     * Constructor por defecto que inicializa estado laboral como ACTIVO
     * Demuestra inicialización de valores específicos del profesor
     */
    public Profesor() {
        super();                                    // Llama al constructor de Persona
        this.estadoLaboral = "ACTIVO";             // Estado laboral activo por defecto
    }

    /**
     * Constructor completo que inicializa tanto datos de persona como de profesor
     * Demuestra cómo combinar datos heredados con datos específicos laborales
     */
    public Profesor(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, 
                    String apellido, LocalDate fechaNacimiento, String codigoProfesor,
                    LocalDate fechaContratacion, Double sueldo, String especialidad) {
        super(tipoDocumento, numeroDocumento, nombre, apellido, fechaNacimiento); // Llama constructor padre
        this.codigoProfesor = codigoProfesor;       // Código único del profesor
        this.fechaContratacion = fechaContratacion; // Fecha de contratación
        this.sueldo = sueldo;                       // Salario del profesor
        this.especialidad = especialidad;           // Área de especialización
        this.estadoLaboral = "ACTIVO";             // Estado laboral activo por defecto
    }

    // IMPLEMENTACIÓN DE MÉTODO ABSTRACTO (POLIMORFISMO)
    /**
     * Implementa el método abstracto de la clase padre
     * Demuestra polimorfismo: cada tipo de persona se identifica diferente
     * @return String que identifica este objeto como "Profesor"
     */
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

    // REPRESENTACIÓN TEXTUAL
    /**
     * Representación textual específica del profesor
     * Demuestra override de toString() con información laboral relevante
     * @return String con información laboral del profesor
     */
    @Override
    public String toString() {
        return "Profesor{" +
                "codigo='" + codigoProfesor + '\'' +        // Código único del profesor
                ", nombre='" + getNombreCompleto() + '\'' + // Usa método heredado de Persona
                ", especialidad='" + especialidad + '\'' +  // Área de especialización
                ", grado='" + gradoAcademico + '\'' +       // Nivel académico
                '}';
    }
}


