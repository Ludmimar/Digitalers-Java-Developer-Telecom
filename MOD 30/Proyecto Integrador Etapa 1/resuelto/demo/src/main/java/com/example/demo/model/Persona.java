package com.example.demo.model;

/**
 * Clase Persona - Modelo que representa a una persona en el sistema
 * Esta clase es un POJO (Plain Old Java Object) con getters y setters
 * Contiene información personal completa incluyendo datos de contacto
 */
public class Persona {
	// ID único para identificar a cada persona (tipo Long permite valores grandes)
	private Long id;
	
	// Nombre completo de la persona
    private String nombre;
    
    // Edad de la persona en años
    private int edad;
    
    // Objeto Direccion que contiene la dirección completa (composición)
    private Direccion direccion;
    
    // Número de teléfono de contacto
    private String telefono;
    
    // Correo electrónico de la persona
    private String email;
    
    /**
     * Getter - Obtiene el ID de la persona
     * @return Long con el identificador único
     */
	public Long getId() {
		return id;
	}
	
	/**
	 * Setter - Establece el ID de la persona
	 * @param id - identificador único a asignar
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Getter - Obtiene el nombre de la persona
	 * @return String con el nombre completo
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter - Establece el nombre de la persona
	 * @param nombre - nombre completo a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter - Obtiene la edad de la persona
	 * @return int con la edad en años
	 */
	public int getEdad() {
		return edad;
	}
	
	/**
	 * Setter - Establece la edad de la persona
	 * @param edad - edad en años a asignar
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**
	 * Getter - Obtiene el objeto Direccion completo
	 * @return Direccion con toda la información de dirección
	 */
	public Direccion getDireccion() {
		return direccion;
	}
	
	/**
	 * Setter - Establece el objeto Direccion
	 * @param direccion - objeto Direccion a asignar
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Getter - Obtiene el número de teléfono
	 * @return String con el teléfono de contacto
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Setter - Establece el número de teléfono
	 * @param telefono - número de teléfono a asignar
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Getter - Obtiene el email
	 * @return String con el correo electrónico
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Setter - Establece el email
	 * @param email - correo electrónico a asignar
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
