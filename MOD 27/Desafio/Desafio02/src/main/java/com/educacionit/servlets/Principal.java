package com.educacionit.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioImplementacion usuarioImplementacion = new UsuarioImplementacion();

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
		Usuario usuario = usuarioImplementacion.buscarPorID(correo);
		String redireccion = "index.jsp";
		// seteamos el atributo mensaje

		// reenviamos la peticion con los objetos request y response

		if (usuario != null && usuario.getClave().equalsIgnoreCase(clave)) {
			redireccion = "Bienvenida.jsp";
			request.setAttribute("usuario", usuario);
		} else {
			request.setAttribute("credencialesValidas", false);
		}

		RequestDispatcher rd = request.getRequestDispatcher(redireccion);
		rd.forward(request, response);

	}

}
