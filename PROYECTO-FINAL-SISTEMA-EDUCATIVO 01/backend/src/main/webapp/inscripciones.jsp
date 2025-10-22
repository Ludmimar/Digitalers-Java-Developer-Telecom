<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl.InscripcionDetalle" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Estudiante" %>
<%@ page import="java.net.URLEncoder" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

@SuppressWarnings("unchecked")
List<InscripcionDetalle> inscripciones = (List<InscripcionDetalle>) request.getAttribute("inscripciones");
@SuppressWarnings("unchecked")
List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
Estudiante estudianteFiltro = (Estudiante) request.getAttribute("estudianteFiltro");
String success = request.getParameter("success");
String error = request.getParameter("error");
String cursoIdFiltro = request.getParameter("cursoId");
String estadoFiltro = request.getParameter("estado");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

// Construir URL actual para returnUrl
String currentUrl = "inscripciones?accion=listar";
if (request.getParameter("estudianteId") != null) {
    currentUrl = "inscripciones?accion=listarPorEstudiante&estudianteId=" + request.getParameter("estudianteId");
} else {
    if (cursoIdFiltro != null && !cursoIdFiltro.isEmpty()) {
        currentUrl += "&cursoId=" + cursoIdFiltro;
    }
    if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
        currentUrl += "&estado=" + estadoFiltro;
    }
}
String returnUrl = URLEncoder.encode(currentUrl, "UTF-8");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Inscripciones - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-clipboard-list"></i> Gestión de Inscripciones
                <% if (estudianteFiltro != null) { %>
                    <small style="font-size: 0.6em; color: var(--text-secondary);"> 
                        - Estudiante: <%= estudianteFiltro.getNombreCompleto() %> (<%= estudianteFiltro.getMatricula() %>)
                    </small>
                <% } %>
                </h2>
                <div>
                    <% if (estudianteFiltro != null) { %>
                        <a href="inscripciones?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-arrow-left"></i> Ver Todas
                        </a>
                    <% } %>
                    <a href="inscripciones?accion=nueva" class="btn btn-primary">
                        <i class="fas fa-plus"></i> Nueva Inscripción
                    </a>
                </div>
            </div>

            <% if (success != null) { %>
                <div class="alert alert-success">
                    <% if (success.equals("insert")) { %>
                        <i class="fas fa-check-circle"></i> Inscripción registrada exitosamente
                    <% } else if (success.equals("update")) { %>
                        <i class="fas fa-check-circle"></i> Inscripción actualizada exitosamente
                    <% } else if (success.equals("delete")) { %>
                        <i class="fas fa-check-circle"></i> Inscripción eliminada exitosamente
                    <% } %>
                </div>
            <% } %>

            <% if (error != null) { %>
                <div class="alert alert-danger">
                    <% if (error.equals("duplicado")) { %>
                        <i class="fas fa-exclamation-circle"></i> El estudiante ya está inscrito en este curso
                    <% } else { %>
                        <i class="fas fa-exclamation-circle"></i> Error al procesar la operación
                    <% } %>
                </div>
            <% } %>

            <!-- Filtros de búsqueda -->
            <div class="filter-section">
                <form action="inscripciones" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="listar">
                    <select name="cursoId" onchange="this.form.submit()" class="filter-select">
                        <option value=""><i class="fas fa-book"></i> Filtrar por curso...</option>
                        <% if (cursos != null) {
                            for (Curso curso : cursos) { %>
                                <option value="<%= curso.getId() %>" <%= curso.getId().toString().equals(cursoIdFiltro) ? "selected" : "" %>>
                                    <%= curso.getCodigoCurso() %> - <%= curso.getNombre() %>
                                </option>
                            <% }
                        } %>
                    </select>
                </form>
                
                <form action="inscripciones" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="listar">
                    <select name="estado" onchange="this.form.submit()" class="filter-select">
                        <option value="">Filtrar por estado...</option>
                        <option value="INSCRITO" <%= "INSCRITO".equals(estadoFiltro) ? "selected" : "" %>>Inscrito</option>
                        <option value="CURSANDO" <%= "CURSANDO".equals(estadoFiltro) ? "selected" : "" %>>Cursando</option>
                        <option value="APROBADO" <%= "APROBADO".equals(estadoFiltro) ? "selected" : "" %>>Aprobado</option>
                        <option value="REPROBADO" <%= "REPROBADO".equals(estadoFiltro) ? "selected" : "" %>>Reprobado</option>
                        <option value="RETIRADO" <%= "RETIRADO".equals(estadoFiltro) ? "selected" : "" %>>Retirado</option>
                    </select>
                </form>
                
                <% if (cursoIdFiltro != null || estadoFiltro != null) { %>
                    <a href="inscripciones?accion=listar" class="btn btn-secondary">
                        <i class="fas fa-times"></i> Limpiar Filtros
                    </a>
                <% } %>
            </div>

            <!-- Tabla de inscripciones -->
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Estudiante</th>
                            <th>Matrícula</th>
                            <th>Curso</th>
                            <th>Profesor</th>
                            <th>Aula</th>
                            <th>Estado</th>
                            <th>Nota</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (inscripciones != null && !inscripciones.isEmpty()) { 
                            for (InscripcionDetalle insc : inscripciones) { %>
                                <tr>
                                    <td><%= insc.estudianteNombre %></td>
                                    <td><strong><%= insc.matricula %></strong></td>
                                    <td><%= insc.cursoNombre %></td>
                                    <td><%= insc.profesorNombre %></td>
                                    <td><%= insc.aula != null ? insc.aula : "N/A" %></td>
                                    <td>
                                        <span class="badge <%= 
                                            insc.estado.toString().equals("APROBADO") ? "badge-success" : 
                                            insc.estado.toString().equals("REPROBADO") ? "badge-danger" : 
                                            insc.estado.toString().equals("CURSANDO") ? "badge-info" : "badge-secondary" %>">
                                            <%= insc.estado %>
                                        </span>
                                    </td>
                                    <td>
                                        <% if (insc.notaFinal != null && insc.notaFinal > 0) { %>
                                            <span class="badge <%= insc.notaFinal >= 7 ? "badge-success" : "badge-warning" %>">
                                                <%= String.format("%.2f", insc.notaFinal) %>
                                            </span>
                                        <% } else { %>
                                            <span class="badge badge-secondary">Pendiente</span>
                                        <% } %>
                                    </td>
                                    <td class="actions">
                                        <a href="inscripciones?accion=ver&id=<%= insc.id %>&returnUrl=<%= returnUrl %>" 
                                           class="btn btn-primary btn-sm" title="Editar inscripción">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                    </td>
                                </tr>
                            <% }
                        } else { %>
                            <tr>
                                <td colspan="9" style="text-align: center; padding: 2rem;">
                                    <i class="fas fa-info-circle"></i> No hay inscripciones registradas
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <div class="stats-info">
                <p><i class="fas fa-chart-bar"></i> Total de inscripciones: <strong><%= inscripciones != null ? inscripciones.size() : 0 %></strong></p>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>

