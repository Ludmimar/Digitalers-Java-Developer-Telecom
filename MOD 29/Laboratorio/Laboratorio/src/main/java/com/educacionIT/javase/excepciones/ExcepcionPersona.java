package com.educacionIT.javase.excepciones;

import java.util.Arrays;

import com.educacionIT.javase.interfaces.Constantes;

public class ExcepcionPersona extends Exception {
	private static final long serialVersionUID = 1L;
	private Integer codigo;

	public ExcepcionPersona(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public ExcepcionPersona(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		switch (codigo) {
		case 1:
			return "Los Documentos validos son: " + Arrays.toString(Constantes.tipoDocumento);
		default:
			return super.getMessage();
		}
	}
}
