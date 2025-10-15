package com.educacionit.desafio02;

import com.educacionit.desafio02.entidades.TarjetaCredito;
import com.educacionit.desafio02.enumerados.ErrorBanco;
import com.educacionit.desafio02.excepciones.TipoProductoExcepcion;

public class AppError {
	public static void main(String[] args) {
		try {

			TarjetaCredito tarjetaCredito = new TarjetaCredito(120, 1, 256);
			throw new TipoProductoExcepcion(ErrorBanco.ERROR_DESCONOCIDO, tarjetaCredito);
		} catch (TipoProductoExcepcion e) {
			e.printStackTrace();
		}
	}
}
