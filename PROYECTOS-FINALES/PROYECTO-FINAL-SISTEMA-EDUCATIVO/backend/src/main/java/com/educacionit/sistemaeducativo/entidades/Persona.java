package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDate;
import java.util.Objects;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;

/**
 * Clase abstracta que representa una Persona en el sistema educativo.
 * Clase base para Estudiante, Profesor y Administrativo (patrón Herencia).
 * 
 * @author Ludmila Martos
 */
public abstract class Persona {
    protected Integer id;
    protected TipoDocumento tipoDocumento;
    protected String numeroDocumento;
    protected String nombre;
    protected String apellido;
    protected LocalDate fechaNacimiento;
    protected String email;
    protected String telefono;
    protected String direccion;
    protected Boolean activo;

    // Constructores
    public Persona() {
        this.activo = true;
    }

    public Persona(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, 
                   String apellido, LocalDate fechaNacimiento) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = true;
    }

    // Método abstracto para que cada subclase defina su tipo
    public abstract String getTipoPersona();

    // Métodos de utilidad
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

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

    // equals y hashCode basados en documento
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(tipoDocumento, persona.tipoDocumento) &&
               Objects.equals(numeroDocumento, persona.numeroDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoDocumento, numeroDocumento);
    }

    @Override
    public String toString() {
        return getTipoPersona() + " [" + nombre + " " + apellido + 
               ", " + tipoDocumento + ": " + numeroDocumento + "]";
    }
}


