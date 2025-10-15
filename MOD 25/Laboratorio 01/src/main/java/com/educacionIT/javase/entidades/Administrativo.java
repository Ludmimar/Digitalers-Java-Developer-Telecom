package com.educacionIT.javase.entidades;

import java.util.Date;

/**
 * Clase concreta que representa un empleado Administrativo en el sistema educativo.
 * 
 * Esta clase extiende de Empleado y proporciona una implementación específica
 * para el personal administrativo. Implementa los métodos abstractos de las
 * clases padre y define comportamientos específicos para este tipo de empleado.
 * 
 * Características principales:
 * - Extiende de Empleado (herencia)
 * - Implementa el método abstracto mostrarTipoPersona()
 * - Implementa los métodos de la interfaz Persistencia
 * - Proporciona funcionalidad específica para personal administrativo
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public class Administrativo extends Empleado {

	/**
	 * Constructor por defecto de Administrativo.
	 * Llama al constructor de la clase padre (Empleado) para inicializar
	 * todos los atributos con valores por defecto.
	 */
	public Administrativo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor parametrizado de Administrativo.
	 * 
	 * @param nombre Nombre del empleado administrativo
	 * @param apellido Apellido del empleado administrativo
	 * @param documento Documento de identidad del empleado
	 * @param fechaNacimiento Fecha de nacimiento del empleado
	 * @param fechaCargo Fecha de inicio del cargo administrativo
	 * @param sueldo Sueldo del empleado administrativo
	 */
	public Administrativo(String nombre, String apellido, Documento documento, Date fechaNacimiento, Date fechaCargo,
			Float sueldo) {
		// Llama al constructor de la clase padre con todos los parámetros
		super(nombre, apellido, documento, fechaNacimiento, fechaCargo, sueldo);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Representación en cadena del objeto Administrativo.
	 * 
	 * Incluye la información completa del empleado administrativo,
	 * incluyendo todos los datos heredados de las clases padre.
	 * 
	 * @return String con la información completa del administrativo formateada
	 */
	@Override
	public String toString() {
		return "Administrativo [toString()=" + super.toString() + "]";
	}

	/**
	 * Implementación del método abstracto mostrarTipoPersona().
	 * 
	 * Este método proporciona una descripción específica del tipo de persona
	 * que es este empleado administrativo, incluyendo su nombre completo.
	 * 
	 * @return String que describe que esta persona es un Administrativo
	 */
	@Override
	public String mostrarTipoPersona() {
		return getNombre() + " " + getApellido() + " es Administrativo";
	}

	/**
	 * Implementación del método guardar() de la interfaz Persistencia.
	 * 
	 * Este método simula la operación de guardar un empleado administrativo
	 * en el sistema de persistencia (base de datos, archivo, etc.).
	 * En una implementación real, aquí se realizaría la inserción en la BD.
	 */
	@Override
	public void guardar() {
		System.out.println("Se ha guardado correctamente el Personal Administrativo");
	}

	/**
	 * Implementación del método eliminar() de la interfaz Persistencia.
	 * 
	 * Este método simula la operación de eliminar un empleado administrativo
	 * del sistema de persistencia. En una implementación real, aquí se
	 * realizaría la eliminación de la BD.
	 */
	@Override
	public void eliminar() {
		System.out.println("Se ha eliminado correctamente el Personal Administrativo");
	}

	/**
	 * Implementación del método modificar() de la interfaz Persistencia.
	 * 
	 * Este método simula la operación de modificar un empleado administrativo
	 * en el sistema de persistencia. En una implementación real, aquí se
	 * realizaría la actualización en la BD.
	 */
	@Override
	public void modificar() {
		System.out.println("Se ha modificado correctamente el Personal Administrativo");
	}

}
