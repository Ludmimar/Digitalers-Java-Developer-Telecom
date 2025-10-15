package com.educacionit.desafio02.enumerados;

public enum TipoDocumento {
	DNI("Documento Nacional de Identidad"), PAS("Pasaporte"), LE("Libreta de Enrolamiento"), CI("Cedula de Identidad");

	private String descripcion;

	private TipoDocumento(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
