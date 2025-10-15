package com.educacionit.desafio02.implementaiones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.educacionit.desafio02.entidades.ConexionMariaDB;
import com.educacionit.desafio02.entidades.Log;
import com.educacionit.desafio02.interfaces.Archivo;
import com.educacionit.desafio02.interfaces.DAO;
import com.educacionit.desafio02.utilidades.Fechas;

public class LogImplementacion implements DAO<Integer, Log> {
	private PreparedStatement psInsertar;
	private PreparedStatement psBuscar;
	private PreparedStatement psActualizar;
	private PreparedStatement psEliminar;
	private PreparedStatement psListar;
	private ConexionMariaDB conexionMariaDB;

	public LogImplementacion() {
		conexionMariaDB = new ConexionMariaDB();
	}

	@Override
	public Log buscarPorID(Integer id) {
		Log log = null;
		String sql = "SELECT fecha, clase, objeto, error from desafio02javase.logs where id = ?;";

		try {
			if (null == psBuscar) {
				psBuscar = conexionMariaDB.getConexion().prepareStatement(sql);
			}
			psBuscar.setInt(1, id);

			ResultSet resultado = psBuscar.executeQuery();

			if (resultado.next()) {
				log = new Log();
				log.setId(id);
				log.setFecha(Fechas.getLocalDateTime(resultado.getString("fecha")));
				log.setClase(resultado.getString("clase"));
				log.setObjeto(resultado.getString("objeto"));
				log.setError(resultado.getString("error"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;
	}

	@Override
	public boolean insertar(Log log) throws SQLException {
		boolean inserto = false;
		String sql = "INSERT INTO desafio02javase.logs (fecha, clase, objeto, error) VALUES(?, ?, ?, ?);";

		if (null == psInsertar) {
			// el segundo parametro indica qupodemos recuperar el
			// ID autoincrementable generado por MariaDB
			psInsertar = conexionMariaDB.getConexion().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		}
		psInsertar.setString(1, Fechas.getStringLocalDateTime(log.getFecha()));
		psInsertar.setString(2, log.getClase());
		psInsertar.setString(3, log.getObjeto());
		psInsertar.setString(4, log.getError());

		inserto = psInsertar.executeUpdate() == 1;

		ResultSet auto_incremental = psInsertar.getGeneratedKeys();

		// recuperamos el ID autoincrementable
		if (auto_incremental.next()) {
			log.setId(auto_incremental.getInt(1));
		}

		Archivo.escribir("log.error", Arrays.asList(log.toLog()));
		return inserto;
	}

	@Override
	public boolean eliminar(Log log) throws SQLException {
		String sql = "delete from desafio02javase.logs where id = ? ";

		if (null == psEliminar) {
			psEliminar = conexionMariaDB.getConexion().prepareStatement(sql);
		}
		psEliminar.setLong(1, log.getId());

		return psEliminar.executeUpdate() == 1;
	}

	@Override
	public boolean actualizar(Log log) throws SQLException {
		String sql = "UPDATE desafio02javase.logs SET fecha=?, clase=?, objeto=?, error=? WHERE id = ?";

		if (null == psActualizar) {
			psActualizar = conexionMariaDB.getConexion().prepareStatement(sql);
		}

		psActualizar.setString(1, Fechas.getStringLocalDateTime(log.getFecha()));
		psActualizar.setString(2, log.getClase());
		psActualizar.setString(3, log.getObjeto());
		psActualizar.setString(4, log.getError());
		psActualizar.setLong(5, log.getId());

		return psActualizar.executeUpdate() == 1;
	}

	@Override
	public List<Log> listar() {
		List<Log> logs = new ArrayList<>();

		String sql = "SELECT id, fecha, clase, objeto, error from desafio02javase.logs";

		try {
			if (null == psListar) {
				psListar = conexionMariaDB.getConexion().prepareStatement(sql);
			}

			ResultSet resultado = psListar.executeQuery();

			while (resultado.next()) {
				Log log = new Log();
				log.setId(resultado.getInt("id"));
				log.setFecha(Fechas.getLocalDateTime(resultado.getString("fecha")));
				log.setClase(resultado.getString("clase"));
				log.setObjeto(resultado.getString("objeto"));
				log.setError(resultado.getString("error"));
				logs.add(log);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return logs;
	}

	public ConexionMariaDB getConexionMariaDB() {
		return conexionMariaDB;
	}

	public void setConexionMariaDB(ConexionMariaDB conexionMariaDB) {
		this.conexionMariaDB = conexionMariaDB;
	}

}
