package com.example.demo.model;

/**
 * Clase Direccion - Modelo que representa una dirección física
 * Esta clase es utilizada como parte de la entidad Persona (composición)
 * Es un POJO (Plain Old Java Object) con getters y setters
 */
public class Direccion {
	// Atributo que almacena el nombre de la calle
    private String calle;
    
    // Atributo que almacena el nombre de la ciudad
    private String ciudad;
    
    // Atributo que almacena el código postal (número entero)
    private int codigoPostal;
    
    /**
     * Getter - Obtiene el valor de la calle
     * @return String con el nombre de la calle
     */
	public String getCalle() {
		return calle;
	}
	
	/**
	 * Setter - Establece el valor de la calle
	 * @param calle - nombre de la calle a establecer
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	/**
	 * Getter - Obtiene el valor de la ciudad
	 * @return String con el nombre de la ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	
	/**
	 * Setter - Establece el valor de la ciudad
	 * @param ciudad - nombre de la ciudad a establecer
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/**
	 * Getter - Obtiene el código postal
	 * @return int con el código postal
	 */
	public int getCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * Setter - Establece el código postal
	 * @param codigoPostal - código postal a establecer
	 */
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

}
