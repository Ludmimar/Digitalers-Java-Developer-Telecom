package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDateTime;
import java.time.LocalDate;
import com.educacionit.sistemaeducativo.enumerados.EstadoInscripcion;

/**
 * Clase que representa una Inscripción de un estudiante a un curso.
 * 
 * PROPÓSITO:
 * - Representa la relación entre estudiante y curso en un período específico
 * - Controla el estado académico de la inscripción
 * - Registra información de evaluación y aprobación
 * - Demuestra relaciones complejas entre entidades del dominio
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Relaciones entre entidades: conecta Estudiante con CursoOfrecido
 * - Enumerados: EstadoInscripcion para controlar estados válidos
 * - Manejo de fechas: LocalDateTime y LocalDate para timestamps
 * - Inicialización automática: fecha de inscripción automática
 * - Deprecación: manejo de campos obsoletos manteniendo compatibilidad
 * 
 * @author Ludmila Martos
 */
public class Inscripcion {
    // Atributos que definen la inscripción y su estado académico
    private Integer id;                        // ID único de la inscripción
    private Integer estudianteId;              // Referencia al estudiante inscrito
    private Integer cursoOfrecidoId;           // Referencia al curso ofrecido en un período
    private LocalDateTime fechaInscripcion;   // Timestamp de cuando se realizó la inscripción
    private EstadoInscripcion estado;          // Estado actual de la inscripción (enum)
    private Double notaFinal;                  // Nota final obtenida en el curso
    private LocalDate fechaAprobacion;         // Fecha en que se aprobó el curso
    private Double asistenciaPorcentaje;       // Deprecated - mantener por compatibilidad

    // CONSTRUCTORES
    /**
     * Constructor por defecto que inicializa estado y fecha automáticamente
     * Demuestra inicialización automática de valores críticos
     */
    public Inscripcion() {
        this.estado = EstadoInscripcion.INSCRITO;        // Estado inicial: inscrito
        this.fechaInscripcion = LocalDateTime.now();     // Timestamp automático de inscripción
    }

    /**
     * Constructor que crea una inscripción básica con estudiante y curso
     * Demuestra creación de relaciones entre entidades
     */
    public Inscripcion(Integer estudianteId, Integer cursoOfrecidoId) {
        this.estudianteId = estudianteId;                // ID del estudiante que se inscribe
        this.cursoOfrecidoId = cursoOfrecidoId;          // ID del curso ofrecido
        this.estado = EstadoInscripcion.INSCRITO;        // Estado inicial: inscrito
        this.fechaInscripcion = LocalDateTime.now();     // Timestamp automático de inscripción
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

    public LocalDate getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDate fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    // MÉTODOS DEPRECATED (COMPATIBILIDAD)
    /**
     * Getter para campo deprecated de asistencia
     * Mantenido por compatibilidad con versiones anteriores
     * @deprecated Este campo ya no se usa en la lógica de negocio
     */
    @Deprecated
    public Double getAsistenciaPorcentaje() {
        return asistenciaPorcentaje;
    }

    /**
     * Setter para campo deprecated de asistencia
     * Mantenido por compatibilidad con versiones anteriores
     * @deprecated Este campo ya no se usa en la lógica de negocio
     */
    @Deprecated
    public void setAsistenciaPorcentaje(Double asistenciaPorcentaje) {
        this.asistenciaPorcentaje = asistenciaPorcentaje;
    }

    // REPRESENTACIÓN TEXTUAL
    /**
     * Representación textual de la inscripción con información académica relevante
     * Demuestra información clave para seguimiento académico
     * @return String con información académica de la inscripción
     */
    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +                           // ID único de la inscripción
                ", estudianteId=" + estudianteId +      // ID del estudiante
                ", estado=" + estado +                 // Estado actual de la inscripción
                ", notaFinal=" + notaFinal +            // Nota final obtenida
                ", fechaAprobacion=" + fechaAprobacion + // Fecha de aprobación
                '}';
    }
}


