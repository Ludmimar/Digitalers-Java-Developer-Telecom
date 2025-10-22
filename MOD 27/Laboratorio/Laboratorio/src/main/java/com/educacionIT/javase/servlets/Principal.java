package com.educacionIT.javase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.educacionIT.javase.entidades.Administrativo;
import com.educacionIT.javase.entidades.Documento;
import com.educacionIT.javase.enumerados.TiposDocumento;
import com.educacionIT.javase.interfaces.UtilidadesFecha;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Principal() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Administrativo administrativo = null;
		try {
			// declaramos las variables
			String nombre;
			String apellido;
			TiposDocumento tipo;
			Integer numero;
			Date fechaNacimiento;
			Date fechaCargo;
			Float sueldo;

			nombre = request.getParameter("nombre");
			apellido = request.getParameter("apellido");
			tipo = TiposDocumento.valueOf(request.getParameter("tipoDocumento"));
			numero = Integer.parseInt(request.getParameter("numeroDocumento"));

			fechaNacimiento = UtilidadesFecha.getStringAFechaSQL(request.getParameter("fechaNacimiento"));
			fechaCargo = UtilidadesFecha.getStringAFechaSQL(request.getParameter("fechaCargo"));
			sueldo = Float.parseFloat(request.getParameter("sueldo"));
			administrativo = new Administrativo(nombre, apellido, new Documento(tipo, numero), fechaNacimiento,
					fechaCargo, sueldo);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Establece el tipo de contenido
		response.setContentType("text/html;charset=UTF-8");

		// Obtiene el stream de salida
		PrintWriter out = response.getWriter();

		// Envia informacion
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>Document</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Nombre: " + administrativo.getNombre() + " </h3><br>");
		out.println("<h3>Apellido: " + administrativo.getApellido() + " </h3><br>");
		out.println("<h3>Documento: " + administrativo.getNombre() + " </h3><br>");
		out.println("<h3>Fecha Nacimiento: " + UtilidadesFecha.getFechaAString(administrativo.getFechaNacimiento())
				+ " </h3><br>");
		out.println(
				"<h3>Fecha Cargo: " + UtilidadesFecha.getFechaAString(administrativo.getFechaCargo()) + " </h3><br>");
		out.println("<h3>Sueldo: " + administrativo.getSueldo() + " </h3><br>");
		out.println("<br>");
		out.println("<a href=\"index.html\">Volver</a>");
		out.println("</body>");
		out.println("</html>");

	}

}
