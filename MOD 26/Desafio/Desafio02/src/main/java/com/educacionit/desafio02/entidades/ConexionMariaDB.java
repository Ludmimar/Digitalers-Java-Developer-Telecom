package com.educacionit.desafio02.entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMariaDB {
	private Connection conexion;

	public ConexionMariaDB() {
		setConexion();
	}

	public Connection getConexion() {
		return conexion;
	}

	private void setConexion() {
		conexion = null;
		try {

			String driver = "org.mariadb.jdbc.Driver";
			String url = "jdbc:mariadb://localhost:3306/Desafio02JavaSE";
			String usuario = "root";
			String clave = "";
			Class.forName(driver);

			// CREAMOS LA CONEXION
			conexion = DriverManager.getConnection(url, usuario, clave);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
