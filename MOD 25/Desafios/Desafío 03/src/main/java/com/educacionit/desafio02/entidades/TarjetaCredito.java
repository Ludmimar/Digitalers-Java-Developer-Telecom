package com.educacionit.desafio02.entidades;

import com.educacionit.desafio02.utilidades.Formatos;

public class TarjetaCredito extends Producto {
	private Integer codigoSeguridad;

	public TarjetaCredito() {
		setNumero(getNumeroGenerado());
	}

	public TarjetaCredito(Integer banco, Integer sucursal, Integer codigoSeguridad) {
		super(banco, sucursal);
		this.codigoSeguridad = codigoSeguridad;
		setNumero(getNumeroGenerado());
	}

	@Override
	public String toString() {
		return "TC [Banco=" + Formatos.getFormato(getBanco(), "BANCO") + ", Sucursal="
				+ Formatos.getFormato(getSucursal(), "BANCO") + ", Numero=" + Formatos.getFormato(getNumero(), "TC")
				+ ", Codigo de Seguridad=" + Formatos.getFormato(getCodigoSeguridad(), "CS") + "]";
	}

	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

}
