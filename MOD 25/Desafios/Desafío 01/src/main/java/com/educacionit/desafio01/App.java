package com.educacionit.desafio01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.educacionit.desafio01.entidades.Ciudad;
import com.educacionit.desafio01.entidades.Pais;

public class App {
	public static void main(String[] args) {
		buscarCiudad("");
	}

	private static Connection conectar() throws SQLException {
		Connection conexion = null;
		try {

			String driver = "org.mariadb.jdbc.Driver";
			String url = "jdbc:mariadb://localhost:3306/Desafio01JavaSE";
			String usuario = "root";
			String clave = "";
			Class.forName(driver);

			// CREAMOS LA CONEXION
			conexion = DriverManager.getConnection(url, usuario, clave);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	private static void buscarCiudad(String cadena) {
		List<Ciudad> ciudades = new ArrayList<>();
		Map<Integer, Pais> paises = new HashMap<>();

		try (Connection conexion = conectar()) {

			String sql = "SELECT id, id_pais, descripcion, categoria FROM desafio01javase.ciudades WHERE descripcion LIKE '%"
					+ cadena + "%';" + "";

			Statement declaracionSQL = conexion.createStatement();

			ResultSet resultado = declaracionSQL.executeQuery(sql);

			while (resultado.next()) {
				Ciudad ciudad = new Ciudad(resultado.getInt("id"), resultado.getInt("id_pais"),
						resultado.getString("descripcion"), resultado.getString("categoria"));
				ciudades.add(ciudad);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// buscamos los paises involucrados e instanciamos el mapa
		if (ciudades.size() > 0) {
			Set<Integer> idPaises = new HashSet<>();
			for (Ciudad ciudad : ciudades) {
				idPaises.add(ciudad.getId_pais());
			}
			for (Integer id : idPaises) {
				Pais pais = buscarPais(id);
				pais.setCiudades(new ArrayList<>());

				ListIterator<Ciudad> iterador = ciudades.listIterator();
				while (iterador.hasNext()) {
					Ciudad ciudad = iterador.next();

					if (ciudad.getId_pais() == pais.getId()) {
						pais.getCiudades().add(ciudad);
						iterador.remove();
					}
				}

				paises.put(id, pais);
			}

			mostrarInformacion(paises);
		}

	}

	private static Pais buscarPais(int id) {
		Pais pais = null;

		try (Connection conexion = conectar()) {
			// CREAMOS NUESTRO QUERY SQL
			String sql = "SELECT id, descripcion FROM desafio01javase.paises WHERE id = " + id + ";";

			Statement declaracionSQL = conexion.createStatement();

			ResultSet resultado = declaracionSQL.executeQuery(sql);

			if (resultado.next()) {
				pais = new Pais();
				pais.setId(resultado.getInt("id"));
				pais.setDescripcion(resultado.getString("descripcion"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pais;
	}

	private static void mostrarInformacion(Map<Integer, Pais> paises) {
		for (Entry<Integer, Pais> entrySet : paises.entrySet()) {
			Pais pais = entrySet.getValue();
			System.out.println("Pais: " + pais.getDescripcion());
			System.out.println("Ciudades:");
			for (Ciudad ciudad : pais.getCiudades()) {
				System.out.println("\t" + ciudad.getDescripcion());
			}

		}
	}
}
