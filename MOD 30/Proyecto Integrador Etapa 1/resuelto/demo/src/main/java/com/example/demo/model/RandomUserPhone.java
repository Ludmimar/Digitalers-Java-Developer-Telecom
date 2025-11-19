package com.example.demo.model;

/**
 * Clase RandomUserPhone - DTO para almacenar teléfonos de usuarios aleatorios
 * Esta clase contiene solo los campos phone y cell obtenidos de la API RandomUser
 */
public class RandomUserPhone {
	// Teléfono fijo del usuario
	private String phone;
	
	// Teléfono celular del usuario
	private String cell;
	
	/**
	 * Constructor vacío
	 */
	public RandomUserPhone() {
	}
	
	/**
	 * Constructor con parámetros
	 * @param phone - teléfono fijo
	 * @param cell - teléfono celular
	 */
	public RandomUserPhone(String phone, String cell) {
		this.phone = phone;
		this.cell = cell;
	}
	
	/**
	 * Getter - Obtiene el teléfono fijo
	 * @return String con el número de teléfono
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Setter - Establece el teléfono fijo
	 * @param phone - número de teléfono a asignar
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Getter - Obtiene el teléfono celular
	 * @return String con el número celular
	 */
	public String getCell() {
		return cell;
	}
	
	/**
	 * Setter - Establece el teléfono celular
	 * @param cell - número celular a asignar
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}
}


