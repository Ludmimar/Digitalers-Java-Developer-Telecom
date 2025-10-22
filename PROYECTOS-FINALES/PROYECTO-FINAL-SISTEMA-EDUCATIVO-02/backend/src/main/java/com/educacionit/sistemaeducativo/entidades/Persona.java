package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;
import java.util.Objects;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;

/**
 * Clase abstracta que representa una Persona en el sistema educativo.
 * Clase base para Estudiante, Profesor y Administrativo (patrón Herencia).
 * 
 * PROPÓSITO:
 * - Implementa el patrón de herencia para reutilizar código común
 * - Define atributos compartidos por todos los tipos de personas
 * - Demuestra encapsulación con modificadores de acceso apropiados
 * - Utiliza enumerados para controlar valores válidos
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Herencia: Clase padre abstracta
 * - Encapsulación: Atributos protected para acceso desde subclases
 * - Polimorfismo: Método abstracto getTipoPersona()
 * - Reutilización de código: Evita duplicación en subclases
 * 
 * @author Ludmila Martos
 */
public abstract class Persona {
    // Atributos comunes a todas las personas en el sistema
    protected Integer id;                    // ID único en la base de datos
    protected TipoDocumento tipoDocumento;   // Enum que controla tipos válidos (DNI, PASAPORTE, etc.)
    protected String numeroDocumento;         // Número del documento de identidad
    protected String nombre;                 // Nombre de pila de la persona
    protected String apellido;               // Apellido de la persona
    protected LocalDate fechaNacimiento;     // Fecha de nacimiento para cálculos de edad
    protected String email;                  // Correo electrónico para comunicación
    protected String telefono;               // Número de teléfono de contacto
    protected String direccion;              // Dirección física de residencia
    protected Boolean activo;                // Estado de la persona en el sistema (soft delete)

    // CONSTRUCTORES
    /**
     * Constructor por defecto que inicializa el estado activo como true
     * Demuestra inicialización de valores por defecto
     */
    public Persona() {
        this.activo = true;  // Por defecto, todas las personas están activas
    }

    /**
     * Constructor con parámetros principales para crear una persona
     * Demuestra encapsulación y validación de datos básicos
     */
    public Persona(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, 
                   String apellido, LocalDate fechaNacimiento) {
        this.tipoDocumento = tipoDocumento;      // Tipo de documento (DNI, PASAPORTE, etc.)
        this.numeroDocumento = numeroDocumento;  // Número único del documento
        this.nombre = nombre;                    // Nombre de pila
        this.apellido = apellido;               // Apellido
        this.fechaNacimiento = fechaNacimiento; // Fecha para cálculos de edad
        this.activo = true;                     // Estado activo por defecto
    }

    // MÉTODO ABSTRACTO (POLIMORFISMO)
    /**
     * Método abstracto que cada subclase debe implementar
     * Demuestra polimorfismo: cada tipo de persona se identifica diferente
     * @return String que identifica el tipo de persona (Estudiante, Profesor, etc.)
     */
    public abstract String getTipoPersona();

    // MÉTODOS DE UTILIDAD
    /**
     * Combina nombre y apellido en una sola cadena
     * Demuestra reutilización de código y métodos de conveniencia
     * @return String con el nombre completo de la persona
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /**
     * Calcula la edad de la persona basándose en la fecha de nacimiento
     * Demuestra uso de LocalDate para cálculos temporales
     * @return int con la edad en años
     */
    public int calcularEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // MÉTODOS DE COMPARACIÓN Y REPRESENTACIÓN
    /**
     * Compara dos personas basándose en tipo y número de documento
     * Demuestra implementación correcta de equals() para evitar duplicados
     * Dos personas son iguales si tienen el mismo tipo y número de documento
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                    // Misma referencia
        if (o == null || getClass() != o.getClass()) return false;  // Tipos diferentes
        Persona persona = (Persona) o;
        return Objects.equals(tipoDocumento, persona.tipoDocumento) &&      // Mismo tipo de documento
               Objects.equals(numeroDocumento, persona.numeroDocumento);     // Mismo número de documento
    }

    /**
     * Genera código hash basado en tipo y número de documento
     * Demuestra implementación correcta de hashCode() para colecciones
     * Debe ser consistente con equals()
     */
    @Override
    public int hashCode() {
        return Objects.hash(tipoDocumento, numeroDocumento);
    }

    /**
     * Representación textual de la persona
     * Demuestra polimorfismo usando getTipoPersona()
     * @return String con información básica de la persona
     */
    @Override
    public String toString() {
        return getTipoPersona() + " [" + nombre + " " + apellido + 
               ", " + tipoDocumento + ": " + numeroDocumento + "]";
    }
}


