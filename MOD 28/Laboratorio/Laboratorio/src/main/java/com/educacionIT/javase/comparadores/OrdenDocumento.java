package com.educacionIT.javase.comparadores;

import java.util.Comparator;

import com.educacionIT.javase.entidades.Persona;

public class OrdenDocumento implements Comparator<Persona> {

	@Override
	public int compare(Persona persona1, Persona persona2) {
		// como tenemos dos elementos debemos hacer un algoritmo un poco mas complejo
		int tipo = persona1.getDocumento().getTipo().toString().compareTo(persona2.getDocumento().getTipo().toString());

		// si los documentos son iguales compara los numeros de lo contrario ordenara
		// primero por la cadena de caracter del tipo
		if (tipo == 0) {
			return persona1.getDocumento().getNumero() - persona2.getDocumento().getNumero();
		}

		return tipo;
	}

}
