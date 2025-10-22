package com.educacionit.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.educacionit.entidades.Usuario;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<Usuario> usuarios = Arrays.asList(new Usuario("user1@deducacionit.com", "user1.1234"),
			new Usuario("user2@deducacionit.com", "user2.1235"), new Usuario("user3@deducacionit.com", "user3.1236")

	);

	private static final String plantillaUsuarioValido = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html lang=\"es\"><head><meta charset=\"UTF-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /><title>Validacion</title></head><body><section><h1 style=\"color: yellowgreen;\">Bienvenido Credenciales Validas</h1></section> <a href=\"index.jsp\">volver</a><div><footer><p>Creado por Educacion IT</p></footer></div></body></html>";
	private static final String plantillaUsuarioNoValido = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html lang=\"es\"><head><meta charset=\"UTF-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /><title>Validacion</title></head><body><section><h1 style=\"color: red;\">Credenciales No Validas, vuelva a intentarlo</h1></section> <a href=\"index.jsp\">volver</a><div><footer><p>Creado por Educacion IT</p></footer></div></body></html>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Principal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String correo = request.getParameter("correo");
		String clave = request.getParameter("clave");
		String redireccion = plantillaUsuarioNoValido;
		if (usuarioValido(correo, clave)) {
			redireccion = plantillaUsuarioValido;
		}
		// Obtiene el stream de salida
		PrintWriter out = response.getWriter();

		// Envia informacion
		out.println(redireccion);
		out.flush();
		out.close();
	}

	private boolean usuarioValido(String correo, String clave) {
		for (Usuario usuario : usuarios) {
			if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getClave().equals(clave)) {
				return true;
			}
		}

		return false;
	}

}
