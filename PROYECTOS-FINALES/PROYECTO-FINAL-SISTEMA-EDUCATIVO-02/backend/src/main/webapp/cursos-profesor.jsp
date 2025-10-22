<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Profesor" %>
<%@ page import="com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl.CursoOfrecidoDetalle" %>
<%
    // Verificar sesión
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    Profesor profesor = (Profesor) request.getAttribute("profesor");
    List<CursoOfrecidoDetalle> cursosAsignados = (List<CursoOfrecidoDetalle>) request.getAttribute("cursosAsignados");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cursos del Profesor - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.1">
</head>
<body>
    <jsp:include page="WEB-INF/includes/header.jsp" />
    
    <div class="container">
        <!-- Título y breadcrumb -->
        <div class="page-header">
            <div>
                <h1><i class="fas fa-chalkboard-teacher"></i> Cursos Asignados</h1>
                <p class="breadcrumb">
                    <a href="dashboard"><i class="fas fa-home"></i> Inicio</a> › 
                    <a href="profesores?accion=listar"><i class="fas fa-users"></i> Profesores</a> › 
                    <a href="profesores?accion=ver&id=<%= profesor.getId() %>&vista=detalle">
                        <%= profesor.getNombre() + " " + profesor.getApellido() %>
                    </a> › 
                    <span>Cursos</span>
                </p>
            </div>
            <a href="profesores?accion=ver&id=<%= profesor.getId() %>&vista=detalle" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Volver al Perfil
            </a>
        </div>

        <!-- Info del profesor -->
        <div class="info-card">
            <div class="info-header">
                <i class="fas fa-user-tie"></i>
                <div>
                    <h2><%= profesor.getNombre() + " " + profesor.getApellido() %></h2>
                    <p><strong>Código:</strong> <%= profesor.getCodigoProfesor() %> | 
                       <strong>Especialidad:</strong> <%= profesor.getEspecialidad() %></p>
                </div>
            </div>
        </div>

        <% if (cursosAsignados == null || cursosAsignados.isEmpty()) { %>
            <div class="alert alert-info">
                <i class="fas fa-info-circle"></i>
                Este profesor no tiene cursos asignados en el período actual.
            </div>
        <% } else { %>
            <!-- Tabla de cursos -->
            <div class="card">
                <div class="card-header">
                    <h3><i class="fas fa-list"></i> Cursos Asignados (<%= cursosAsignados.size() %>)</h3>
                </div>
                <div class="table-container">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nombre del Curso</th>
                                <th>Créditos</th>
                                <th>Período</th>
                                <th>Aula</th>
                                <th>Horario</th>
                                <th>Cupos</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (CursoOfrecidoDetalle curso : cursosAsignados) { %>
                                <tr>
                                    <td>
                                        <span class="badge badge-primary">
                                            <%= curso.codigoCurso %>
                                        </span>
                                    </td>
                                    <td><strong><%= curso.cursoNombre %></strong></td>
                                    <td><%= curso.creditos %></td>
                                    <td>
                                        <% if (curso.periodoNombre != null) { %>
                                            <%= curso.periodoNombre %>
                                            <br>
                                            <small class="text-muted">
                                                <%= curso.anio %> - <%= curso.semestre %>
                                            </small>
                                        <% } else { %>
                                            <span class="text-muted">N/A</span>
                                        <% } %>
                                    </td>
                                    <td>
                                        <i class="fas fa-door-open"></i> 
                                        <%= curso.aula %>
                                    </td>
                                    <td>
                                        <i class="fas fa-clock"></i>
                                        <%= curso.horario %>
                                    </td>
                                    <td>
                                        <span class="badge badge-info">
                                            <%= curso.cuposDisponibles %>
                                        </span>
                                    </td>
                                    <td class="actions">
                                        <a href="cursos?accion=ver&id=<%= curso.cursoId %>&vista=detalle" 
                                           class="btn-icon" 
                                           title="Ver detalle del curso">
                                            <i class="fas fa-eye"></i>
                                        </a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Estadísticas -->
            <div class="stats-grid" style="margin-top: 20px;">
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
                        <i class="fas fa-book"></i>
                    </div>
                    <div class="stat-info">
                        <h3><%= cursosAsignados.size() %></h3>
                        <p>Cursos Totales</p>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);">
                        <i class="fas fa-award"></i>
                    </div>
                    <div class="stat-info">
                        <%
                            int totalCreditos = 0;
                            for (CursoOfrecidoDetalle c : cursosAsignados) {
                                totalCreditos += c.creditos;
                            }
                        %>
                        <h3><%= totalCreditos %></h3>
                        <p>Créditos Totales</p>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);">
                        <i class="fas fa-users"></i>
                    </div>
                    <div class="stat-info">
                        <%
                            int totalCupos = 0;
                            for (CursoOfrecidoDetalle c : cursosAsignados) {
                                totalCupos += c.cuposDisponibles;
                            }
                        %>
                        <h3><%= totalCupos %></h3>
                        <p>Cupos Totales</p>
                    </div>
                </div>
            </div>
        <% } %>
    </div>

    <jsp:include page="WEB-INF/includes/footer.jsp" />
</body>
</html>




