package com.educacionIT.javase.comparadores;

import java.util.Comparator;

import com.educacionIT.javase.entidades.Persona;

/**
 * Comparador que ordena objetos Persona por fecha de nacimiento en orden descendente.
 * 
 * Esta clase implementa la interfaz Comparator<Persona> para permitir el ordenamiento
 * de listas de personas basado en su fecha de nacimiento. El ordenamiento es descendente,
 * es decir, las personas más jóvenes aparecerán primero en la lista.
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public class OrdenEdadDesc implements Comparator<Persona> {

	/**
	 * Compara dos objetos Persona basándose en su fecha de nacimiento.
	 * 
	 * @param persona1 Primera persona a comparar
	 * @param persona2 Segunda persona a comparar
	 * @return Un valor negativo si persona1 es más joven que persona2,
	 *         cero si tienen la misma edad, o un valor positivo si persona1 es mayor que persona2.
	 *         Esto resulta en un ordenamiento descendente (más jóvenes primero).
	 */
	@Override
	public int compare(Persona persona1, Persona persona2) {
		// Utiliza el método compareTo() de Date para comparar las fechas de nacimiento
		// El resultado se invierte para obtener orden descendente (más jóvenes primero)
		return persona1.getFechaNacimiento().compareTo(persona2.getFechaNacimiento());
	}

}
