package com.educacionit.sistemaeducativo.entidades;

import java.util.Objects;

/**
 * Clase que representa un Curso en el sistema educativo.
 * 
 * PROPÓSITO:
 * - Representa la oferta académica de la institución
 * - Define características académicas de cada curso
 * - Controla capacidad y carga académica
 * - Demuestra encapsulación con valores por defecto apropiados
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Encapsulación: atributos private con getters/setters
 * - Inicialización por defecto: valores sensatos en constructores
 * - Comparación de objetos: equals() y hashCode() basados en código único
 * - Modelado de dominio: información académica específica
 * 
 * @author Ludmila Martos
 */
public class Curso {
    // Atributos que definen las características del curso
    private Integer id;                    // ID único en la base de datos
    private String codigoCurso;             // Código único del curso (ej: MAT101, PROG201)
    private String nombre;                  // Nombre descriptivo del curso
    private String descripcion;             // Descripción detallada del contenido
    private Integer creditos;               // Créditos académicos que otorga el curso
    private Integer horasSemanales;          // Horas de clase por semana
    private Integer cupoMaximo;             // Máximo número de estudiantes permitidos
    private String estado;                  // Estado del curso (ACTIVO, INACTIVO, etc.)

    // CONSTRUCTORES
    /**
     * Constructor por defecto con valores académicos estándar
     * Demuestra inicialización con valores sensatos para el dominio educativo
     */
    public Curso() {
        this.creditos = 3;              // 3 créditos es un valor estándar
        this.horasSemanales = 4;         // 4 horas semanales es común
        this.cupoMaximo = 30;            // Cupo estándar para aulas
        this.estado = "ACTIVO";          // Estado activo por defecto
    }

    /**
     * Constructor completo que permite especificar características académicas
     * Demuestra flexibilidad en la creación de cursos con diferentes características
     */
    public Curso(String codigoCurso, String nombre, String descripcion, 
                 Integer creditos, Integer horasSemanales) {
        this.codigoCurso = codigoCurso;      // Código único del curso
        this.nombre = nombre;                // Nombre descriptivo
        this.descripcion = descripcion;      // Descripción del contenido
        this.creditos = creditos;            // Créditos académicos
        this.horasSemanales = horasSemanales; // Horas semanales
        this.cupoMaximo = 30;                // Cupo estándar
        this.estado = "ACTIVO";              // Estado activo por defecto
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // MÉTODOS DE COMPARACIÓN Y REPRESENTACIÓN
    /**
     * Compara dos cursos basándose en el código del curso
     * Demuestra implementación correcta de equals() para evitar duplicados
     * Dos cursos son iguales si tienen el mismo código
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                    // Misma referencia
        if (o == null || getClass() != o.getClass()) return false;  // Tipos diferentes
        Curso curso = (Curso) o;
        return Objects.equals(codigoCurso, curso.codigoCurso);  // Mismo código de curso
    }

    /**
     * Genera código hash basado en el código del curso
     * Demuestra implementación correcta de hashCode() para colecciones
     * Debe ser consistente con equals()
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigoCurso);
    }

    /**
     * Representación textual del curso con información académica relevante
     * Demuestra información clave para identificación y gestión académica
     * @return String con información académica del curso
     */
    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigoCurso + '\'' +      // Código único del curso
                ", nombre='" + nombre + '\'' +         // Nombre descriptivo
                ", creditos=" + creditos +            // Créditos académicos
                ", horas=" + horasSemanales +          // Horas semanales
                '}';
    }
}


