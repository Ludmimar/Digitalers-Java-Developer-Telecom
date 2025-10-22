package com.educacionIT.javase.entidades;

import java.util.Date;

public class Director extends Empleado {
	private String carrera;

	/**
	 * 
	 */
	public Director() {
		super();
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param documento
	 * @param fechaNacimiento
	 * @param fechaCargo
	 * @param sueldo
	 * @param carrera
	 */
	public Director(String nombre, String apellido, Documento documento, Date fechaNacimiento, Date fechaCargo,
			Float sueldo, String carrera) {
		super(nombre, apellido, documento, fechaNacimiento, fechaCargo, sueldo);
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "Director [toString()=" + super.toString() + ", carrera=" + carrera + "]";
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	@Override
	public String mostrarTipoPersona() {
		return getNombre() + " " + getApellido() + " es Director";
	}


}
