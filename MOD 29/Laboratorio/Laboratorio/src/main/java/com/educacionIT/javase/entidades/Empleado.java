package com.educacionIT.javase.entidades;

import java.util.Date;

import com.educacionIT.javase.interfaces.UtilidadesFecha;

public abstract class Empleado extends Persona {
	private Date fechaCargo;
	private Float sueldo;

	public Empleado() {
		super();
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param documento
	 * @param fechaNacimiento
	 * @param fechaCargo
	 * @param sueldo
	 */
	public Empleado(String nombre, String apellido, Documento documento, Date fechaNacimiento, Date fechaCargo,
			Float sueldo) {
		super(nombre, apellido, documento, fechaNacimiento);
		this.fechaCargo = fechaCargo;
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "Empleado [toString()=" + super.toString() + ", fechaCargo="
				+ UtilidadesFecha.getFechaAString(fechaCargo) + ", sueldo=" + sueldo + "]";
	}

	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	public Float getSueldo() {
		return sueldo;
	}

	public void setSueldo(Float sueldo) {
		this.sueldo = sueldo;
	}

}
