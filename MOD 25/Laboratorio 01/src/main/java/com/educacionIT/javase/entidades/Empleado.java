package com.educacionIT.javase.entidades;

import java.util.Date;

import com.educacionIT.javase.interfaces.UtilidadesFecha;

/**
 * Clase abstracta que representa un Empleado en el sistema educativo.
 * 
 * Esta clase extiende de Persona y agrega atributos específicos relacionados
 * con el empleo, como la fecha de inicio del cargo y el sueldo. Es una clase
 * abstracta que no puede ser instanciada directamente, sino que debe ser
 * extendida por clases concretas como Administrativo, Profesor, etc.
 * 
 * Características principales:
 * - Extiende de Persona (herencia)
 * - Agrega atributos específicos de empleados
 * - Clase abstracta que define la estructura común para todos los empleados
 * - Utiliza composición y herencia para organizar la información
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public abstract class Empleado extends Persona {
	
	// Atributos específicos de empleados
	private Date fechaCargo;    // Fecha de inicio del cargo
	private Float sueldo;       // Sueldo del empleado

	/**
	 * Constructor por defecto de Empleado.
	 * Llama al constructor de la clase padre (Persona) e inicializa
	 * los atributos específicos de empleado con valores por defecto.
	 */
	public Empleado() {
		super();
	}

	/**
	 * Constructor parametrizado de Empleado.
	 * 
	 * @param nombre Nombre del empleado
	 * @param apellido Apellido del empleado
	 * @param documento Documento de identidad del empleado
	 * @param fechaNacimiento Fecha de nacimiento del empleado
	 * @param fechaCargo Fecha de inicio del cargo
	 * @param sueldo Sueldo del empleado
	 */
	public Empleado(String nombre, String apellido, Documento documento, Date fechaNacimiento, Date fechaCargo,
			Float sueldo) {
		// Llama al constructor de la clase padre con los parámetros comunes
		super(nombre, apellido, documento, fechaNacimiento);
		// Inicializa los atributos específicos de empleado
		this.fechaCargo = fechaCargo;
		this.sueldo = sueldo;
	}

	/**
	 * Representación en cadena del objeto Empleado.
	 * 
	 * Incluye la información de la clase padre (Persona) más los atributos
	 * específicos de empleado (fecha de cargo y sueldo).
	 * 
	 * @return String con la información completa del empleado formateada
	 */
	@Override
	public String toString() {
		return "Empleado [toString()=" + super.toString() + ", fechaCargo="
				+ UtilidadesFecha.getFechaAString(fechaCargo) + ", sueldo=" + sueldo + "]";
	}

	// Métodos getter y setter para el atributo fechaCargo
	public Date getFechaCargo() {
		return fechaCargo;
	}

	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}

	// Métodos getter y setter para el atributo sueldo
	public Float getSueldo() {
		return sueldo;
	}

	public void setSueldo(Float sueldo) {
		this.sueldo = sueldo;
	}

}
