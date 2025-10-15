package com.educacionIT.javase.interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Interfaz que define constantes utilizadas en el sistema educativo.
 * 
 * Esta interfaz centraliza todas las constantes del sistema, incluyendo
 * tipos de documentos válidos y cursos disponibles. Proporciona un lugar
 * centralizado para mantener estos valores, facilitando el mantenimiento
 * y la consistencia en toda la aplicación.
 * 
 * Características principales:
 * - Centraliza constantes del sistema
 * - Proporciona métodos estáticos para obtener datos
 * - Facilita el mantenimiento y actualización
 * - Mejora la legibilidad del código
 * 
 * Contenido:
 * - Tipos de documento válidos
 * - Mapa de cursos disponibles con IDs
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public interface Constantes {
	
	// Array con los tipos de documento válidos en el sistema
	String[] tipoDocumento = { "DNI", "PAS", "LE", "CI" };
	
	
	/**
	 * Obtiene un mapa con los cursos disponibles en el sistema educativo.
	 * 
	 * Este método estático crea y retorna un mapa que contiene los cursos
	 * disponibles en el sistema, donde la clave es un ID numérico y el
	 * valor es el nombre del curso. Los cursos incluyen tecnologías
	 * de programación y desarrollo web.
	 * 
	 * Cursos disponibles:
	 * - JAVA: Programación en Java
	 * - PYTHON: Programación en Python
	 * - SQL: Base de datos y consultas SQL
	 * - JAVASCRIPT: Programación en JavaScript
	 * - REACT: Desarrollo con React
	 * 
	 * @return Map<Integer, String> mapa con ID y nombre de cursos
	 */
	static Map<Integer, String> getCursos() {
		// Crea un nuevo mapa para almacenar los cursos
		Map<Integer, String> cursos = new HashMap<Integer, String>();
		Integer id = 1;  // Contador para los IDs de cursos
		
		// Agrega cada curso al mapa con su ID correspondiente
		cursos.put(id++, "JAVA");        // ID: 1
		cursos.put(id++, "PYTHON");      // ID: 2
		cursos.put(id++, "SQL");         // ID: 3
		cursos.put(id++, "JAVASCRIPT");  // ID: 4
		cursos.put(id++, "REACT");       // ID: 5
		
		// Retorna el mapa completo con todos los cursos
		return cursos;
	}
}
