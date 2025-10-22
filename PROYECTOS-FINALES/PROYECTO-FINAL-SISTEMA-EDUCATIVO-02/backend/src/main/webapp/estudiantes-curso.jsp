<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl.InscripcionDetalle" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

@SuppressWarnings("unchecked")
List<InscripcionDetalle> inscripciones = (List<InscripcionDetalle>) request.getAttribute("inscripciones");
Curso curso = (Curso) request.getAttribute("curso");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estudiantes del Curso - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2>
                    <i class="fas fa-users"></i> Estudiantes Inscritos
                    <% if (curso != null) { %>
                        <small style="font-size: 0.6em; color: var(--text-secondary);"> 
                            - <%= curso.getCodigoCurso() %>: <%= curso.getNombre() %>
                        </small>
                    <% } %>
                </h2>
                <a href="cursos?accion=ver&id=<%= curso != null ? curso.getId() : "" %>&vista=detalle" 
                   class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Volver al Curso
                </a>
            </div>

            <!-- Tabla de estudiantes -->
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Matrícula</th>
                            <th>Nombre Completo</th>
                            <th>Email</th>
                            <th>Estado</th>
                            <th>Nota Final</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (inscripciones != null && !inscripciones.isEmpty()) { 
                            for (InscripcionDetalle insc : inscripciones) { %>
                                <tr>
                                    <td><strong><%= insc.matricula %></strong></td>
                                    <td><%= insc.estudianteNombre %></td>
                                    <td><a href="mailto:<%= insc.estudianteEmail %>"><%= insc.estudianteEmail %></a></td>
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
                                        <a href="estudiantes?accion=ver&id=<%= insc.estudianteId %>&vista=detalle" 
                                           class="btn-icon" title="Ver estudiante">
                                            <i class="fas fa-user"></i>
                                        </a>
                                        <a href="inscripciones?accion=ver&id=<%= insc.id %>" 
                                           class="btn-icon" title="Editar inscripción">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                    </td>
                                </tr>
                            <% }
                        } else { %>
                            <tr>
                                <td colspan="7" style="text-align: center; padding: 2rem;">
                                    <i class="fas fa-info-circle"></i> No hay estudiantes inscritos en este curso
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <div class="stats-info">
                <p><i class="fas fa-users"></i> Total de estudiantes inscritos: <strong><%= inscripciones != null ? inscripciones.size() : 0 %></strong></p>
                <% if (curso != null) { %>
                    <p><i class="fas fa-chart-pie"></i> Cupo máximo: <strong><%= curso.getCupoMaximo() %></strong> | 
                       Disponibles: <strong class="<%= (curso.getCupoMaximo() - (inscripciones != null ? inscripciones.size() : 0)) > 0 ? "text-success" : "text-danger" %>">
                           <%= curso.getCupoMaximo() - (inscripciones != null ? inscripciones.size() : 0) %>
                       </strong>
                    </p>
                <% } %>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>



