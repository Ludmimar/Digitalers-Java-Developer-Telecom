package com.educacionit.implementaciones.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.entidades.Usuario;
import com.educacionit.interfaces.ConexionMariaDB;
import com.educacionit.interfaces.DAO;

public class UsuarioImplementacion implements DAO<Usuario, String>, ConexionMariaDB {
	private PreparedStatement psInsertar;
	private PreparedStatement psBuscar;
	private PreparedStatement psActualizar;
	private PreparedStatement psEliminar;

	@Override
	public Usuario buscarPorID(String correo) {
		Usuario usuario = null;
		String sql = "select correo, clave from usuarios where correo = ?;";

		try {
			if (null == psBuscar) {
				psBuscar = getConexion().prepareStatement(sql);
			}

			psBuscar.setString(1, correo);

			ResultSet resultado = psBuscar.executeQuery();

			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setClave(resultado.getString("clave"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public boolean insertar(Usuario usuario) {
		String sql = "insert into usuarios (correo, clave) values (?, ?);";
		try {
			if (null == psInsertar) {
				psInsertar = getConexion().prepareStatement(sql);
			}

			psInsertar.setString(1, usuario.getCorreo());
			psInsertar.setString(2, usuario.getClave());

			return psInsertar.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean eliminar(Usuario usuario) {
		String sql = "delete from usuarios where correo = ?; ";
		try {
			if (null == psEliminar) {
				psEliminar = getConexion().prepareStatement(sql);
			}
			psEliminar.setString(1, usuario.getCorreo());

			return psEliminar.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean actualizar(Usuario usuario) {
		String sql = "update usuarios set clave = ? where correo = ?;";
		try {
			if (null == psActualizar) {
				psActualizar = getConexion().prepareStatement(sql);
			}

			psActualizar.setString(1, usuario.getClave());
			psActualizar.setString(2, usuario.getCorreo());

			return psActualizar.executeUpdate() == 1;

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();

		String sql = "select correo, clave from usuarios;";

		try {
			if (null == psBuscar) {
				psBuscar = getConexion().prepareStatement(sql);
			}

			ResultSet resultado = psBuscar.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setClave(resultado.getString("clave"));
				usuarios.add(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

}
