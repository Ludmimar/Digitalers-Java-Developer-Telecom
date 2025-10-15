package com.educacionIT.javase.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Interfaz que proporciona utilidades para el manejo de fechas en el sistema.
 * 
 * Esta interfaz define métodos estáticos para la conversión y formateo de fechas
 * entre diferentes formatos utilizados en el sistema. Proporciona patrones
 * estándar para la interfaz de usuario y para la base de datos, facilitando
 * la consistencia en el manejo de fechas en toda la aplicación.
 * 
 * Características principales:
 * - Métodos estáticos para conversión de fechas
 * - Patrones de formato predefinidos
 * - Formateo para interfaz de usuario y base de datos
 * - Manejo de excepciones de parsing
 * 
 * Patrones de formato:
 * - dd/MM/yyyy: Para interfaz de usuario (ej: 15/03/1985)
 * - yyyy-MM-dd: Para base de datos SQL (ej: 1985-03-15)
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public interface UtilidadesFecha {
	
	// Patrón de formato para fechas en la interfaz de usuario
	String PATRON_FECHA = "dd/MM/yyyy";
	
	// Patrón de formato para fechas en base de datos SQL
	String PATRON_FECHA_SQL = "yyyy-MM-dd";
	
	// Formateador para fechas de interfaz de usuario
	SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat(PATRON_FECHA);
	
	// Formateador para fechas de base de datos SQL
	SimpleDateFormat FORMATO_FECHA_SQL = new SimpleDateFormat(PATRON_FECHA_SQL);

	/**
	 * Convierte un objeto Date a String usando el formato de interfaz de usuario.
	 * 
	 * Este método toma un objeto Date y lo convierte a una cadena de texto
	 * usando el formato dd/MM/yyyy, que es el formato estándar para mostrar
	 * fechas al usuario en la interfaz.
	 * 
	 * @param fecha Objeto Date a convertir
	 * @return String con la fecha formateada (dd/MM/yyyy)
	 */
	static String getFechaAString(Date fecha) {
		return FORMATO_FECHA.format(fecha);
	}

	/**
	 * Convierte un String a objeto Date usando el formato de interfaz de usuario.
	 * 
	 * Este método toma una cadena de texto con formato dd/MM/yyyy y la convierte
	 * a un objeto Date. Es útil para procesar fechas ingresadas por el usuario
	 * en la interfaz.
	 * 
	 * @param fecha String con la fecha en formato dd/MM/yyyy
	 * @return Date objeto Date correspondiente
	 * @throws ParseException si el formato de la fecha no es válido
	 */
	static Date getStringAFecha(String fecha) throws ParseException {
		return FORMATO_FECHA.parse(fecha);
	}

	/**
	 * Convierte un objeto Date a String usando el formato de base de datos SQL.
	 * 
	 * Este método toma un objeto Date y lo convierte a una cadena de texto
	 * usando el formato yyyy-MM-dd, que es el formato estándar para fechas
	 * en consultas SQL y almacenamiento en base de datos.
	 * 
	 * @param fecha Objeto Date a convertir
	 * @return String con la fecha formateada (yyyy-MM-dd)
	 */
	static String getFechaAStringsSQL(Date fecha) {
		return FORMATO_FECHA_SQL.format(fecha);
	}

}
