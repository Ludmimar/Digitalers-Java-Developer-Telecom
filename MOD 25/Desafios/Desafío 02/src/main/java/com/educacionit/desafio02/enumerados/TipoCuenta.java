package com.educacionit.desafio02.enumerados;

public enum TipoCuenta {
	CC("Cuenta Corriente", true), CA("Caja de Ahorro", true), FCI("Fondo Comun de Inversion", false),
	PF("Plazo Fijo", false);

	private String descripcion;
	boolean disponible;

	private TipoCuenta(String descripcion, boolean disponible) {
		this.descripcion = descripcion;
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isDisponible() {
		return disponible;
	}

}
