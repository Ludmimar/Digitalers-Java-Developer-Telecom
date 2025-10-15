package com.educacionit.desafio02.enumerados;

public enum ErrorBanco {
	ERROR_PRODUCTO_NO_DISPONIBLE(1, "Producto No disponible"), ERROR_PRODUCTO_INEXISTENTE(2, "Producto Inexistente"),
	ERROR_DESCONOCIDO(0, "Error No Especificado contacte al administrador");

	private Integer codigo;
	private String descripcion;

	private ErrorBanco(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

}
