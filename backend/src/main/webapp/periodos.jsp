<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.PeriodoAcademico" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

@SuppressWarnings("unchecked")
List<PeriodoAcademico> periodos = (List<PeriodoAcademico>) request.getAttribute("periodos");
PeriodoAcademico periodoActivo = (PeriodoAcademico) request.getAttribute("periodoActivo");
String mensaje = request.getParameter("success");
String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Períodos Académicos - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-calendar-alt"></i> Períodos Académicos</h2>
                <a href="periodos?accion=nuevo" class="btn btn-primary">
                    <i class="fas fa-plus"></i> Nuevo Período
                </a>
            </div>
            
            <!-- Mensajes -->
            <% if ("insert".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Período creado exitosamente
            </div>
            <% } else if ("update".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Período actualizado exitosamente
            </div>
            <% } else if ("delete".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Período eliminado exitosamente
            </div>
            <% } else if ("activado".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Período activado correctamente
            </div>
            <% } else if (error != null) { %>
            <div class="alert alert-danger">
                <i class="fas fa-times-circle"></i> Error al procesar la solicitud
            </div>
            <% } %>
            
            <!-- Período Activo -->
            <% if (periodoActivo != null) { %>
            <div class="alert alert-info" style="background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 100%); border-left-color: var(--primary);">
                <i class="fas fa-calendar-check"></i>
                <div>
                    <strong>Período Activo:</strong> <%= periodoActivo.getNombreCompleto() %><br>
                    <small>Estado: <%= periodoActivo.getEstado() %> | 
                    Inscripciones: <%= periodoActivo.getRangoInscripciones() %> | 
                    Clases: <%= periodoActivo.getRangoClases() %></small>
                </div>
            </div>
            <% } %>
            
            <!-- Tabla de períodos -->
            <div class="table-container">
                <% if (periodos != null && !periodos.isEmpty()) { %>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Período</th>
                            <th>Año</th>
                            <th>Semestre</th>
                            <th>Estado</th>
                            <th>Fecha Inscripciones</th>
                            <th>Fecha Clases</th>
                            <th>Activo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (PeriodoAcademico periodo : periodos) { %>
                        <tr <%= periodo.getActivo() ? "style='background-color: #f0f9ff;'" : "" %>>
                            <td><strong><%= periodo.getNombre() %></strong></td>
                            <td><%= periodo.getAnio() %></td>
                            <td><%= periodo.getSemestre() %>°</td>
                            <td>
                                <span class="badge <%= 
                                    periodo.getEstado().name().equals("CURSANDO") ? "badge-success" : 
                                    periodo.getEstado().name().equals("INSCRIPCION") ? "badge-info" :
                                    periodo.getEstado().name().equals("PLANIFICACION") ? "badge-warning" : "badge-secondary" %>">
                                    <%= periodo.getEstado() %>
                                </span>
                            </td>
                            <td style="font-size: 0.85rem;">
                                <%= periodo.getFechaInicioInscripciones() != null ? periodo.getFechaInicioInscripciones() : "-" %>
                                al
                                <%= periodo.getFechaFinInscripciones() != null ? periodo.getFechaFinInscripciones() : "-" %>
                            </td>
                            <td style="font-size: 0.85rem;">
                                <%= periodo.getFechaInicioClases() != null ? periodo.getFechaInicioClases() : "-" %>
                                al
                                <%= periodo.getFechaFinClases() != null ? periodo.getFechaFinClases() : "-" %>
                            </td>
                            <td class="text-center">
                                <% if (periodo.getActivo()) { %>
                                    <i class="fas fa-check-circle" style="color: var(--success); font-size: 1.5rem;"></i>
                                <% } else { %>
                                    <form method="post" action="periodos" style="display: inline;">
                                        <input type="hidden" name="accion" value="activar">
                                        <input type="hidden" name="id" value="<%= periodo.getId() %>">
                                        <button type="submit" class="btn btn-sm btn-success" title="Activar Período">
                                            <i class="fas fa-toggle-on"></i> Activar
                                        </button>
                                    </form>
                                <% } %>
                            </td>
                            <td class="actions">
                                <a href="periodos?accion=ver&id=<%= periodo.getId() %>&vista=detalle" 
                                   class="btn btn-icon" title="Ver Detalle">
                                    <i class="fas fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <p class="table-info">
                    Total de períodos: <strong><%= periodos.size() %></strong>
                </p>
                <% } else { %>
                <div class="empty-state">
                    <i class="fas fa-calendar-alt"></i>
                    <p>No hay períodos académicos registrados</p>
                    <a href="periodos?accion=nuevo" class="btn btn-primary">
                        <i class="fas fa-plus"></i> Crear Primer Período
                    </a>
                </div>
                <% } %>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>


