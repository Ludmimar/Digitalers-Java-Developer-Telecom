package com.educacionIT.javase.entidades;

import java.util.Date;

import com.educacionIT.javase.interfaces.Persistencia;
import com.educacionIT.javase.interfaces.UtilidadesFecha;

/**
 * Clase abstracta que representa una Persona en el sistema educativo.
 * 
 * Esta clase define los atributos y comportamientos comunes a todas las personas
 * del sistema, incluyendo información personal básica como nombre, apellido,
 * documento de identidad y fecha de nacimiento. Implementa la interfaz Persistencia
 * para definir operaciones básicas de almacenamiento.
 * 
 * Características principales:
 * - Clase abstracta que no puede ser instanciada directamente
 * - Define un método abstracto que debe ser implementado por las subclases
 * - Implementa equals() y hashCode() basados en el documento para unicidad
 * - Utiliza composición con la clase Documento
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public abstract class Persona implements Persistencia {
	
	// Atributos de la clase Persona
	private String nombre;           // Nombre de la persona
	private String apellido;         // Apellido de la persona
	private Documento documento;     // Documento de identidad (composición)
	private Date fechaNacimiento;    // Fecha de nacimiento

	/**
	 * Constructor por defecto de Persona.
	 * Inicializa todos los atributos con valores por defecto.
	 */
	public Persona() {
		super();
	}

	/**
	 * Constructor parametrizado de Persona.
	 * 
	 * @param nombre Nombre de la persona
	 * @param apellido Apellido de la persona
	 * @param documento Documento de identidad
	 * @param fechaNacimiento Fecha de nacimiento
	 */
	public Persona(String nombre, String apellido, Documento documento, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Método abstracto que debe ser implementado por las subclases.
	 * 
	 * Este método permite que cada tipo específico de persona (Alumno, Empleado, etc.)
	 * proporcione su propia implementación para mostrar el tipo de persona que es.
	 * 
	 * @return String que describe el tipo de persona
	 */
	public abstract String mostrarTipoPersona();

	/**
	 * Representación en cadena del objeto Persona.
	 * 
	 * @return String con la información de la persona formateada
	 */
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
				+ ", fechaNacimiento=" + UtilidadesFecha.getFechaAString(fechaNacimiento) + "]";
	}

	// Métodos getter y setter para el atributo nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Métodos getter y setter para el atributo apellido
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	// Métodos getter y setter para el atributo documento
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	// Métodos getter y setter para el atributo fechaNacimiento
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Calcula el código hash del objeto basándose en el documento.
	 * 
	 * Dos personas con el mismo documento tendrán el mismo hash code,
	 * lo que es útil para colecciones como HashSet o HashMap.
	 * 
	 * @return int código hash del objeto
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

	/**
	 * Compara dos objetos Persona para determinar si son iguales.
	 * 
	 * Dos personas se consideran iguales si tienen el mismo documento de identidad.
	 * Esto es importante para evitar duplicados en el sistema.
	 * 
	 * @param obj Objeto a comparar
	 * @return true si las personas son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		// Verifica si es el mismo objeto en memoria
		if (this == obj)
			return true;
		
		// Verifica si el objeto es null
		if (obj == null)
			return false;
		
		// Verifica si son de la misma clase
		if (getClass() != obj.getClass())
			return false;
		
		// Convierte el objeto a Persona para comparar
		Persona other = (Persona) obj;
		
		// Compara los documentos de identidad
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		
		return true;
	}

}
