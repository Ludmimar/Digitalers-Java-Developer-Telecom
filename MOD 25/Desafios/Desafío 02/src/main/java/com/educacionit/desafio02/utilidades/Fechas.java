package com.educacionit.desafio02.utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Octavio robleto
 *
 */
public interface Fechas {

	String PATRON_FECHA_YYYYMMDD = "yyyy-MM-dd";
	String PATRON_FECHA_HORA_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Obtener Fecha LocalDate de String
	 * 
	 * @param fecha
	 * @return
	 */
	static LocalDate getLocalDate(String fecha) {
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern(PATRON_FECHA_YYYYMMDD));
	}

	/**
	 * Obtener Fecha LocalDateTime de String
	 * 
	 * @param fecha
	 * @return
	 */
	static LocalDateTime getLocalDateTime(String fecha) {
		return LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern(PATRON_FECHA_HORA_YYYYMMDD_HHMMSS));
	}

	/**
	 * Obtener Fecha String de LocalDate
	 * 
	 * @param fecha
	 * @return
	 */
	public static String getStringLocalDate(LocalDate fecha) {
		return fecha.format(DateTimeFormatter.ofPattern(PATRON_FECHA_YYYYMMDD));
	}

	/**
	 * Obtener Fecha String de LocalDateTime
	 * 
	 * @param fecha
	 * @return
	 */
	public static String getStringLocalDateTime(LocalDateTime fecha) {
		return fecha.format(DateTimeFormatter.ofPattern(PATRON_FECHA_HORA_YYYYMMDD_HHMMSS));
	}
}
