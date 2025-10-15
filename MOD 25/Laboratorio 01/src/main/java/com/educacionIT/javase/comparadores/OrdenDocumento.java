package com.educacionIT.javase.comparadores;

import java.util.Comparator;

import com.educacionIT.javase.entidades.Persona;

/**
 * Comparador que ordena objetos Persona por tipo de documento y número de documento.
 * 
 * Esta clase implementa la interfaz Comparator<Persona> para permitir el ordenamiento
 * de listas de personas basado en su documento de identidad. El ordenamiento se realiza
 * primero por el tipo de documento (alfabéticamente) y luego por el número de documento
 * (numéricamente) cuando los tipos son iguales.
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public class OrdenDocumento implements Comparator<Persona> {

	/**
	 * Compara dos objetos Persona basándose en su documento de identidad.
	 * 
	 * El algoritmo de comparación funciona de la siguiente manera:
	 * 1. Primero compara el tipo de documento (alfabéticamente)
	 * 2. Si los tipos son iguales, compara los números de documento (numéricamente)
	 * 
	 * @param persona1 Primera persona a comparar
	 * @param persona2 Segunda persona a comparar
	 * @return Un valor negativo si persona1 debe ir antes que persona2,
	 *         cero si son iguales, o un valor positivo si persona1 debe ir después que persona2
	 */
	@Override
	public int compare(Persona persona1, Persona persona2) {
		// Compara primero el tipo de documento convirtiendo el enum a String
		// Esto permite ordenamiento alfabético de los tipos de documento
		int tipo = persona1.getDocumento().getTipo().toString().compareTo(persona2.getDocumento().getTipo().toString());

		// Si los tipos de documento son iguales (tipo == 0), entonces compara los números
		// Si los tipos son diferentes, retorna el resultado de la comparación de tipos
		if (tipo == 0) {
			// Compara los números de documento numéricamente
			// Resta los números para obtener el orden correcto
			return persona1.getDocumento().getNumero() - persona2.getDocumento().getNumero();
		}

		// Retorna el resultado de la comparación de tipos de documento
		return tipo;
	}

}
