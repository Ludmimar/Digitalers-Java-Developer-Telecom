package com.educacionit.sistemaeducativo.entidades;

import java.time.LocalDateTime;

/**
 * Entidad que representa un Aula en el sistema educativo.
 * 
 * @author Ludmila Martos
 */
public class Aula {
    
    private Integer id;
    private String codigo;
    private String nombre;
    private Integer capacidad;
    private String edificio;
    private Integer piso;
    private TipoAula tipo;
    private String equipamiento;
    private EstadoAula estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Enumerados internos
    public enum TipoAula {
        AULA, LABORATORIO, AUDITORIO, TALLER
    }
    
    public enum EstadoAula {
        DISPONIBLE, EN_USO, MANTENIMIENTO, INACTIVA
    }
    
    // Constructores
    public Aula() {
        this.estado = EstadoAula.DISPONIBLE;
        this.tipo = TipoAula.AULA;
    }
    
    public Aula(String codigo, String nombre, Integer capacidad) {
        this();
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    public String getEdificio() {
        return edificio;
    }
    
    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
    
    public Integer getPiso() {
        return piso;
    }
    
    public void setPiso(Integer piso) {
        this.piso = piso;
    }
    
    public TipoAula getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoAula tipo) {
        this.tipo = tipo;
    }
    
    public String getEquipamiento() {
        return equipamiento;
    }
    
    public void setEquipamiento(String equipamiento) {
        this.equipamiento = equipamiento;
    }
    
    public EstadoAula getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoAula estado) {
        this.estado = estado;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Métodos de utilidad
    
    /**
     * Retorna el nombre completo del aula (código + nombre).
     */
    public String getNombreCompleto() {
        return codigo + " - " + nombre;
    }
    
    /**
     * Retorna la ubicación completa del aula.
     */
    public String getUbicacionCompleta() {
        if (edificio != null && piso != null) {
            return edificio + ", Piso " + piso;
        } else if (edificio != null) {
            return edificio;
        }
        return "Ubicación no especificada";
    }
    
    /**
     * Verifica si el aula está disponible para asignación.
     */
    public boolean estaDisponible() {
        return estado == EstadoAula.DISPONIBLE;
    }
    
    /**
     * Verifica si tiene capacidad para un número de estudiantes.
     */
    public boolean tieneCapacidad(Integer numeroEstudiantes) {
        return numeroEstudiantes != null && capacidad != null && numeroEstudiantes <= capacidad;
    }
    
    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", tipo=" + tipo +
                ", estado=" + estado +
                '}';
    }
}


