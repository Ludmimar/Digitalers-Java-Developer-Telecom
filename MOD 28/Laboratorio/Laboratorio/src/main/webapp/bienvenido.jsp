<%@page import="com.educacionIT.javase.interfaces.UtilidadesFecha"%>
<%@page import="com.educacionIT.javase.entidades.Administrativo"%>
<%@page import="java.util.List"%>
<%@page import="com.educacionIT.javase.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- /**
 * @author Octavio Robleto
 * @see https://orobleto.github.io/octaviorobleto.github.io/
 * 
 */ -->
<!DOCTYPE html>
<html lang="es">

<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<!--     <link rel='stylesheet' type='text/css' media='screen' href='css/formularios.css'> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src='main.js'></script>
<title>Clase 01 - Sesiones JSP</title>

</head>

<body>

	<div class="jumbotron text-center">
		<h1>Java EE</h1>
		<p>Lista de Empleados Administrativos</p>
	</div>

	<%
		Usuario usuario = (Usuario) session.getAttribute("usuario");

	if (usuario != null) {
	%>
	<!-- Left-aligned media object -->
	<div class="container">
		<div class="media">
			<div class="media-left">
				<img src="images/editor.png" class="media-object"
					style="width: 150px">
			</div>
			<div class="media-body">
					<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Documento</th>
      <th scope="col">Nombre</th>
      <th scope="col">Apellido</th>
      <th scope="col">Fecha de Nacimciento</th>
      <th scope="col">Fecha de Cargo</th>
      <th scope="col">Sueldo</th>
    </tr>
  </thead>
  <tbody>
    
  	<%
	
	List<Administrativo> administrativos = (List<Administrativo>)session.getAttribute("administrativos");
	
	for (Administrativo administrativo : administrativos) {
		out.print("<tr>");
		out.print("<td>"+administrativo.getDocumento().getTipo() + "-" + administrativo.getDocumento().getNumero() + "</td>");
		out.print("<td>"+administrativo.getNombre()+"</td>");
		out.print("<td>"+administrativo.getApellido()+"</td>");
		out.print("<td>"+UtilidadesFecha.getFechaAString(administrativo.getFechaNacimiento())+"</td>");
		out.print("<td>"+UtilidadesFecha.getFechaAString(administrativo.getFechaCargo())+"</td>");
		out.print("<td>"+administrativo.getSueldo()+"</td>");
		out.print("</tr>");
	}
	%>
	
  </tbody>
</table>
			
					
				
			
				</div>
		</div>
	</div>

	<div class="container">
		<a href="sesiones?iniciar=false" class="btn btn-link" role="button">Cerrar
			Sesion <kbd><%=session.getId()%></kbd></a>
	</div>

	


	<%
		} else {
		response.sendRedirect("index.jsp");
	}
	%>



</body>

</html>