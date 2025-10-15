package com.educacionIT.javase.enumerados;

/**
 * Enumerado que define los tipos de documentos de identidad válidos en el sistema.
 * 
 * Este enumerado proporciona una lista predefinida de tipos de documentos
 * que pueden ser utilizados en el sistema educativo. Cada tipo tiene asociada
 * una descripción completa que facilita la comprensión y presentación al usuario.
 * 
 * Características principales:
 * - Define constantes para tipos de documento
 * - Cada valor tiene una descripción asociada
 * - Proporciona validación de tipos de documento
 * - Facilita el mantenimiento y extensión del sistema
 * 
 * Tipos disponibles:
 * - DNI: Documento Nacional de Identidad
 * - PAS: Pasaporte
 * - LE: Libreta de Enrolamiento
 * - CI: Cédula de Identidad
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public enum TiposDocumento {
	
	// Definición de los tipos de documento con sus descripciones
	DNI("Documento Nacional de Identidad"),    // DNI argentino
	PAS("Pasaporte"),                           // Pasaporte internacional
	LE("Libreta de Enrolamiento"),              // Libreta de Enrolamiento militar
	CI("Cedula de Identidad");                  // Cédula de Identidad

	// Atributo que almacena la descripción de cada tipo de documento
	private String descripcion;

	/**
	 * Constructor del enumerado TiposDocumento.
	 * 
	 * @param descripcion Descripción completa del tipo de documento
	 */
	private TiposDocumento(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene la descripción completa del tipo de documento.
	 * 
	 * Este método permite acceder a la descripción legible del tipo
	 * de documento, lo que es útil para mostrar información al usuario
	 * o para generar reportes y documentación.
	 * 
	 * @return String con la descripción completa del tipo de documento
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
