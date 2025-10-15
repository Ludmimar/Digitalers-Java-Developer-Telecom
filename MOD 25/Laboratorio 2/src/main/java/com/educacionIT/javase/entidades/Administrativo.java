package com.educacionIT.javase.entidades;

import java.util.Date;

public class Administrativo extends Empleado {

	/**
	 * 
	 */
	public Administrativo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param documento
	 * @param fechaNacimiento
	 * @param fechaCargo
	 * @param sueldo
	 */
	public Administrativo(String nombre, String apellido, Documento documento, Date fechaNacimiento, Date fechaCargo,
			Float sueldo) {
		super(nombre, apellido, documento, fechaNacimiento, fechaCargo, sueldo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrativo [toString()=" + super.toString() + "]";
	}

	@Override
	public String mostrarTipoPersona() {
		return getNombre() + " " + getApellido() + " es Administrativo";
	}

}
