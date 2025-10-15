package com.educacionit.desafio02.excepciones;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.educacionit.desafio02.entidades.ConexionMariaDB;
import com.educacionit.desafio02.entidades.Producto;
import com.educacionit.desafio02.enumerados.ErrorBanco;
import com.educacionit.desafio02.utilidades.Fechas;

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
		ConexionMariaDB conexionMariaDB = new ConexionMariaDB();
		String query = "INSERT INTO logs (fecha, clase, objeto, error) VALUES('"
				+ Fechas.getStringLocalDateTime(LocalDateTime.now()) + "','" + producto.getClass().toString() + "', '"
				+ producto + "', '" + errorBanco.getDescripcion() + "');";
		try {
			Statement declaracionSQL = conexionMariaDB.getConexion().createStatement();
			declaracionSQL.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
