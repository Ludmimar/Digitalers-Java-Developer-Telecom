package com.educacionit.desafio02.excepciones;

import java.sql.SQLException;
import java.time.LocalDateTime;

import com.educacionit.desafio02.entidades.Log;
import com.educacionit.desafio02.entidades.Producto;
import com.educacionit.desafio02.enumerados.ErrorBanco;
import com.educacionit.desafio02.implementaiones.LogImplementacion;

public class TipoProductoExcepcion extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorBanco errorBanco;

	public TipoProductoExcepcion(ErrorBanco errorBanco, Producto producto) {
		super();
		this.errorBanco = errorBanco;
		crearLogError(producto);
	}

	@Override
	public String getMessage() {
		return errorBanco.getDescripcion();
	}

	private void crearLogError(Producto producto) {

		Log log = new Log(null, LocalDateTime.now(), producto.getClass().toString(), producto.toString(),
				errorBanco.getDescripcion());

		LogImplementacion logImplementacion = new LogImplementacion();
		try {
			logImplementacion.insertar(log);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println(log);
		}

	}

}
