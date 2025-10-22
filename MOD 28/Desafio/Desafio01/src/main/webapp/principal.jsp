<%@page import="java.util.Arrays"%>
<%@page import="com.educacionit.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Validacion</title>

<%!private static final List<Usuario> usuarios = Arrays.asList(new Usuario("user1@deducacionit.com", "user1.1234"),
			new Usuario("user2@deducacionit.com", "user2.1235"), new Usuario("user3@deducacionit.com", "user3.1236")

	);

	private boolean usuarioValido(String correo, String clave) {
		for (Usuario usuario : usuarios) {
			if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getClave().equals(clave)) {
				return true;
			}
		}

		return false;
	}%>

</head>
<body>
	<section>
		<%
		String correo = request.getParameter("correo");
		String clave = request.getParameter("clave");
		if (usuarioValido(correo, clave)) {
		%>
		<h1 style="color: yellowgreen;">Bienvenido Credenciales Validas</h1>
		<%
		} else {
		%>
		<h1 style="color: red;">Credenciales No Validas, vuelva a
			intentarlo</h1>
		<%
		}
		%>


	</section>

	<a href="index.jsp">volver</a>


</body>
</html>