package com.educacionit.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioImplementacion usuarioImplementacion = new UsuarioImplementacion();
	private static Map<String, Usuario> usuariosActivos = new HashMap<String, Usuario>();

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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Boolean cerrarSesion = Boolean.valueOf(request.getParameter("cerrarSesion"));

		// si entra por el metodo get a la URL invalidamos la sesion si existe
		if (request.getSession().getId() != null && cerrarSesion != null && cerrarSesion) {

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			usuariosActivos.remove(usuario.getCorreo());
			request.getSession().invalidate();

			request.setAttribute("mensaje", "Se ha cerrado la sesion correctamente");

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("Bienvenida.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String correo = request.getParameter("correo");
		String clave = request.getParameter("clave");
		Usuario usuario = usuarioImplementacion.buscarPorID(correo);
		HttpSession sesion = null;
		String redireccion = "index.jsp";

		Boolean sesionIniciada = usuariosActivos.containsKey(correo);

		// seteamos el atributo mensaje

		// reenviamos la peticion con los objetos request y response

		if (usuario != null && usuario.getClave().equalsIgnoreCase(clave) && !sesionIniciada) {
			usuariosActivos.put(correo, usuario);
			sesion = request.getSession();
			sesion.setAttribute("usuario", usuario);
			System.out.println(sesion);
			System.out.println(sesion.getId());
			System.out.println(usuariosActivos);
			redireccion = "Bienvenida.jsp";
		} else if (sesionIniciada) {
			request.setAttribute("mensaje",
					"No puedes iniciar sesion en dos equipos al mismo tiempo, por favor cierra la otra sesion");
		} else {
			request.setAttribute("mensaje", "Credenciales No Validas");
		}

		RequestDispatcher rd = request.getRequestDispatcher(redireccion);
		rd.forward(request, response);

	}

}
