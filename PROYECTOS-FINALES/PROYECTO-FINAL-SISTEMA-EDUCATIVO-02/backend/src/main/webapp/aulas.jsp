<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

@SuppressWarnings("unchecked")
List<Aula> aulas = (List<Aula>) request.getAttribute("aulas");
String tipoFiltro = (String) request.getAttribute("tipoFiltro");
String estadoFiltro = (String) request.getAttribute("estadoFiltro");
String mensaje = request.getParameter("success");
String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aulas - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-door-open"></i> Gestión de Aulas</h2>
                <a href="aulas?accion=nuevo" class="btn btn-primary">
                    <i class="fas fa-plus"></i> Nueva Aula
                </a>
            </div>
            
            <!-- Mensajes de éxito/error -->
            <% if ("insert".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Aula creada exitosamente
            </div>
            <% } else if ("update".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Aula actualizada exitosamente
            </div>
            <% } else if ("delete".equals(mensaje)) { %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> Aula eliminada exitosamente
            </div>
            <% } else if (error != null) { %>
            <div class="alert alert-danger">
                <i class="fas fa-times-circle"></i> Error al procesar la solicitud
            </div>
            <% } %>
            
            <!-- Filtros -->
            <div class="filter-section">
                <form method="get" action="aulas" style="display: flex; gap: 1rem; align-items: center;">
                    <input type="hidden" name="accion" value="listar">
                    
                    <select name="tipo" class="filter-select" onchange="this.form.submit()">
                        <option value="">Todos los tipos</option>
                        <option value="AULA" <%= "AULA".equals(tipoFiltro) ? "selected" : "" %>>Aula</option>
                        <option value="LABORATORIO" <%= "LABORATORIO".equals(tipoFiltro) ? "selected" : "" %>>Laboratorio</option>
                        <option value="AUDITORIO" <%= "AUDITORIO".equals(tipoFiltro) ? "selected" : "" %>>Auditorio</option>
                        <option value="TALLER" <%= "TALLER".equals(tipoFiltro) ? "selected" : "" %>>Taller</option>
                    </select>
                    
                    <select name="estado" class="filter-select" onchange="this.form.submit()">
                        <option value="">Todos los estados</option>
                        <option value="DISPONIBLE" <%= "DISPONIBLE".equals(estadoFiltro) ? "selected" : "" %>>Disponible</option>
                        <option value="EN_USO" <%= "EN_USO".equals(estadoFiltro) ? "selected" : "" %>>En Uso</option>
                        <option value="MANTENIMIENTO" <%= "MANTENIMIENTO".equals(estadoFiltro) ? "selected" : "" %>>Mantenimiento</option>
                        <option value="INACTIVA" <%= "INACTIVA".equals(estadoFiltro) ? "selected" : "" %>>Inactiva</option>
                    </select>
                    
                    <% if (tipoFiltro != null || estadoFiltro != null) { %>
                    <a href="aulas?accion=listar" class="btn btn-secondary btn-sm">
                        <i class="fas fa-times"></i> Limpiar Filtros
                    </a>
                    <% } %>
                </form>
            </div>
            
            <!-- Tabla de aulas -->
            <div class="table-container">
                <% if (aulas != null && !aulas.isEmpty()) { %>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Capacidad</th>
                            <th>Tipo</th>
                            <th>Edificio</th>
                            <th>Piso</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Aula aula : aulas) { %>
                        <tr>
                            <td><strong><%= aula.getCodigo() %></strong></td>
                            <td><%= aula.getNombre() %></td>
                            <td><%= aula.getCapacidad() %> personas</td>
                            <td>
                                <span class="badge <%= 
                                    aula.getTipo().name().equals("LABORATORIO") ? "badge-info" : 
                                    aula.getTipo().name().equals("AUDITORIO") ? "badge-warning" :
                                    aula.getTipo().name().equals("TALLER") ? "badge-secondary" : "badge-primary" %>">
                                    <%= aula.getTipo() %>
                                </span>
                            </td>
                            <td><%= aula.getEdificio() != null ? aula.getEdificio() : "-" %></td>
                            <td><%= aula.getPiso() != null ? aula.getPiso() : "-" %></td>
                            <td>
                                <span class="badge <%= 
                                    aula.getEstado().name().equals("DISPONIBLE") ? "badge-success" : 
                                    aula.getEstado().name().equals("EN_USO") ? "badge-info" :
                                    aula.getEstado().name().equals("MANTENIMIENTO") ? "badge-warning" : "badge-secondary" %>">
                                    <%= aula.getEstado() %>
                                </span>
                            </td>
                            <td class="actions">
                                <a href="aulas?accion=ver&id=<%= aula.getId() %>&vista=detalle" 
                                   class="btn btn-icon" title="Ver Detalle">
                                    <i class="fas fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <p class="table-info">
                    Total de aulas: <strong><%= aulas.size() %></strong>
                </p>
                <% } else { %>
                <div class="empty-state">
                    <i class="fas fa-door-open"></i>
                    <p>No hay aulas registradas</p>
                    <a href="aulas?accion=nuevo" class="btn btn-primary">
                        <i class="fas fa-plus"></i> Crear Primera Aula
                    </a>
                </div>
                <% } %>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>


