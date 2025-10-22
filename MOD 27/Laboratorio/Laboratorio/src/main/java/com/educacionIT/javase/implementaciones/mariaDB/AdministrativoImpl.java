package com.educacionIT.javase.implementaciones.mariaDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educacionIT.javase.entidades.Administrativo;
import com.educacionIT.javase.entidades.Documento;
import com.educacionIT.javase.enumerados.TiposDocumento;
import com.educacionIT.javase.interfaces.ConexionMariaDB;
import com.educacionIT.javase.interfaces.DAO;
import com.educacionIT.javase.interfaces.UtilidadesFecha;

public class AdministrativoImpl implements DAO<Administrativo, Documento>, ConexionMariaDB {
	private PreparedStatement psBuscarPorID = null;
	private PreparedStatement psInsertar = null;
	private PreparedStatement psModificar = null;
	private PreparedStatement psEliminar = null;
	private PreparedStatement psListar = null;

	@Override
	public Administrativo buscarPorID(Documento documento) {
		Administrativo administrativo = null;
		String sql = "SELECT TipoDocumento, NumeroDocumento, Nombre, Apellido, FechaNacimiento, FechaCargo, sueldo FROM empleados WHERE NumeroDocumento = ? AND TipoDocumento = ? AND Tipo = ?;";

		try {
			if (null == psBuscarPorID) {
				psBuscarPorID = getConexion().prepareStatement(sql);
			}

			psBuscarPorID.setInt(1, documento.getNumero());
			psBuscarPorID.setString(2, documento.getTipo().toString());
			psInsertar.setInt(3, 4);

			ResultSet resultado = psBuscarPorID.executeQuery();

			if (resultado.next()) {

				TiposDocumento TipoDocumento = TiposDocumento.valueOf(resultado.getString("TipoDocumento"));
				Integer NumeroDocumento = resultado.getInt("NumeroDocumento");
				String Nombre = resultado.getString("TipoDocumento");
				String Apellido = resultado.getString("TipoDocumento");
				Date FechaNacimiento = UtilidadesFecha.getStringAFecha(resultado.getString("FechaNacimiento"));
				Date FechaCargo = UtilidadesFecha.getStringAFecha(resultado.getString("FechaCargo"));
				Float sueldo = resultado.getFloat("sueldo");

				administrativo = new Administrativo(Nombre, Apellido, new Documento(TipoDocumento, NumeroDocumento),
						FechaNacimiento, FechaCargo, sueldo);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return administrativo;
	}

	@Override
	public boolean insertar(Administrativo administrativo) {

		String sql = "INSERT INTO empleados (TipoDocumento, NumeroDocumento, Nombre, Apellido, FechaNacimiento, FechaCargo, sueldo, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			if (null == psInsertar) {
				psInsertar = getConexion().prepareStatement(sql);
			}

			psInsertar.setString(1, administrativo.getDocumento().getTipo().toString());
			psInsertar.setInt(2, administrativo.getDocumento().getNumero());
			psInsertar.setString(3, administrativo.getNombre());
			psInsertar.setString(4, administrativo.getApellido());
			psInsertar.setDate(5,
					java.sql.Date.valueOf(UtilidadesFecha.getFechaAStringsSQL(administrativo.getFechaNacimiento())));
			psInsertar.setDate(6,
					java.sql.Date.valueOf(UtilidadesFecha.getFechaAStringsSQL(administrativo.getFechaCargo())));
			psInsertar.setFloat(7, administrativo.getSueldo());
			psInsertar.setInt(8, 4);

			System.out.println(psInsertar);
			return (psInsertar.executeUpdate() == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(Administrativo administrativo) {
		String sql = "DELETE FROM empleados WHERE NumeroDocumento = ? AND TipoDocumento = ? AND Tipo = ?;";
		try {
			if (null == psEliminar) {
				psEliminar = getConexion().prepareStatement(sql);
			}

			psInsertar.setInt(1, administrativo.getDocumento().getNumero());
			psInsertar.setString(2, administrativo.getDocumento().getTipo().toString());
			psInsertar.setInt(3, 4);
			return (psEliminar.executeUpdate() == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean actualizar(Administrativo administrativo) {
		String sql = "UPDATE empleados SET Nombre = ?, Apellido = ?, FechaNacimiento = ?, FechaCargo = ?, sueldo = ? WHERE NumeroDocumento = ? AND TipoDocumento = ? AND Tipo = ?;";
		try {
			if (null == psModificar) {
				psModificar = getConexion().prepareStatement(sql);
			}

			psInsertar.setString(1, administrativo.getNombre());
			psInsertar.setString(2, administrativo.getApellido());
			psInsertar.setDate(3,
					java.sql.Date.valueOf(UtilidadesFecha.getFechaAStringsSQL(administrativo.getFechaNacimiento())));
			psInsertar.setDate(4,
					java.sql.Date.valueOf(UtilidadesFecha.getFechaAStringsSQL(administrativo.getFechaCargo())));
			psInsertar.setFloat(5, administrativo.getSueldo());

			psInsertar.setString(6, administrativo.getDocumento().getTipo().toString());
			psInsertar.setInt(7, administrativo.getDocumento().getNumero());
			psInsertar.setInt(8, 4);

			return (psModificar.executeUpdate() == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Administrativo> listar() {
		List<Administrativo> lista = new ArrayList<>();

		String sql = "SELECT TipoDocumento, NumeroDocumento, Nombre, Apellido, FechaNacimiento, FechaCargo, sueldo FROM empleados WHERE Tipo = 4;";

		try {
			if (null == psListar) {
				psListar = getConexion().prepareStatement(sql);
			}

			ResultSet resultado = psListar.executeQuery();

			while (resultado.next()) {

				TiposDocumento TipoDocumento = TiposDocumento.valueOf(resultado.getString("TipoDocumento"));
				Integer NumeroDocumento = resultado.getInt("NumeroDocumento");
				String Nombre = resultado.getString("TipoDocumento");
				String Apellido = resultado.getString("TipoDocumento");
				Date FechaNacimiento = UtilidadesFecha.getStringAFecha(resultado.getString("FechaNacimiento"));
				Date FechaCargo = UtilidadesFecha.getStringAFecha(resultado.getString("FechaCargo"));
				Float sueldo = resultado.getFloat("sueldo");

				Administrativo administrativo = new Administrativo(Nombre, Apellido,
						new Documento(TipoDocumento, NumeroDocumento), FechaNacimiento, FechaCargo, sueldo);

				lista.add(administrativo);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
