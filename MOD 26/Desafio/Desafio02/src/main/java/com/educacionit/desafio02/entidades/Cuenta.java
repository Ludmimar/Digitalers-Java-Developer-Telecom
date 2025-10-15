package com.educacionit.desafio02.entidades;

import com.educacionit.desafio02.enumerados.ErrorBanco;
import com.educacionit.desafio02.enumerados.TipoCuenta;
import com.educacionit.desafio02.excepciones.TipoProductoExcepcion;
import com.educacionit.desafio02.utilidades.Formatos;

public class Cuenta extends Producto {
	private TipoCuenta tipo;

	public Cuenta() {
		setNumero(getNumeroGenerado());
	}

	public Cuenta(Integer banco, Integer sucursal, TipoCuenta tipo) throws TipoProductoExcepcion {
		super(banco, sucursal);
		setTipo(tipo);
		setNumero(getNumeroGenerado());

	}

	@Override
	public String toString() {
		return tipo.name() + " [Banco=" + Formatos.getFormato(getBanco(), "BANCO") + ", Sucursal="
				+ Formatos.getFormato(getSucursal(), "BANCO") + ", Numero="
				+ Formatos.getFormato(getNumero(), tipo.name()) + ", " + tipo.getDescripcion() + "]";
	}

	public TipoCuenta getTipo() {
		return tipo;
	}

	public void setTipo(TipoCuenta tipo) throws TipoProductoExcepcion {
		if (!tipo.isDisponible()) {
			throw new TipoProductoExcepcion(ErrorBanco.ERROR_PRODUCTO_NO_DISPONIBLE,this);
		}
		this.tipo = tipo;
	}

}
