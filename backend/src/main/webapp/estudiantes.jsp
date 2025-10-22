<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Estudiante" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
String mensaje = (String) request.getAttribute("mensaje");
String error = (String) request.getAttribute("error");
String matriculaBusqueda = request.getParameter("matricula");
String estadoFiltro = request.getParameter("estado");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estudiantes - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2>👨‍🎓 Gestión de Estudiantes</h2>
                <a href="nuevo-estudiante.jsp" class="btn btn-primary">
                    ➕ Nuevo Estudiante
                </a>
            </div>
            
            <% if (mensaje != null) { %>
                <div class="alert alert-success">✅ <%= mensaje %></div>
            <% } %>
            
            <% if (error != null) { %>
                <div class="alert alert-error">❌ <%= error %></div>
            <% } %>
            
            <!-- Filtros y Búsqueda -->
            <div class="filter-section">
                <form action="estudiantes" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="buscar">
                    <div class="filter-group">
                        <input type="text" name="matricula" placeholder="🔍 Buscar por matrícula o nombre..." 
                               value="<%= matriculaBusqueda != null ? matriculaBusqueda : "" %>"
                               class="search-input">
                        <button type="submit" class="btn btn-secondary">
                            <i class="fas fa-search"></i> Buscar
                        </button>
                    </div>
                </form>
                
                <form action="estudiantes" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="buscar">
                    <select name="estado" onchange="this.form.submit()" class="filter-select">
                        <option value="">Todos los estados</option>
                        <option value="ACTIVO" <%= "ACTIVO".equals(estadoFiltro) ? "selected" : "" %>>✅ Activo</option>
                        <option value="INACTIVO" <%= "INACTIVO".equals(estadoFiltro) ? "selected" : "" %>>❌ Inactivo</option>
                        <option value="GRADUADO" <%= "GRADUADO".equals(estadoFiltro) ? "selected" : "" %>>🎓 Graduado</option>
                        <option value="SUSPENDIDO" <%= "SUSPENDIDO".equals(estadoFiltro) ? "selected" : "" %>>⏸️ Suspendido</option>
                    </select>
                </form>
                
                <% if (matriculaBusqueda != null || estadoFiltro != null) { %>
                    <a href="estudiantes?accion=listar" class="btn btn-secondary">
                        <i class="fas fa-times"></i> Limpiar Filtros
                    </a>
                <% } %>
            </div>
            
            <!-- Tabla de Estudiantes -->
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Matrícula</th>
                            <th>Nombre Completo</th>
                            <th>Documento</th>
                            <th>Email</th>
                            <th>Promedio</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        if (estudiantes != null && !estudiantes.isEmpty()) {
                            for (Estudiante est : estudiantes) {
                        %>
                        <tr>
                            <td><strong><%= est.getMatricula() %></strong></td>
                            <td><%= est.getNombreCompleto() %></td>
                            <td><%= est.getTipoDocumento() %> <%= est.getNumeroDocumento() %></td>
                            <td><%= est.getEmail() %></td>
                            <td>
                                <span class="badge <%= est.getPromedioGeneral() >= 7 ? "badge-success" : "badge-warning" %>">
                                    <%= String.format("%.2f", est.getPromedioGeneral()) %>
                                </span>
                            </td>
                            <td>
                                <span class="badge badge-info"><%= est.getEstadoAcademico() %></span>
                            </td>
                            <td class="actions">
                                <a href="estudiantes?accion=ver&id=<%= est.getId() %>&vista=detalle" 
                                   class="btn-icon" title="Ver detalle">
                                    <i class="fas fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                        <% 
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="7" style="text-align: center; padding: 2rem;">
                                <i class="fas fa-info-circle"></i> No hay estudiantes registrados
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    function confirmarEliminacion(id, nombre) {
        if (confirm('¿Está seguro de eliminar al estudiante ' + nombre + '?')) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'estudiantes';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = id;
            
            form.appendChild(inputAccion);
            form.appendChild(inputId);
            document.body.appendChild(form);
            form.submit();
        }
    }
    </script>
</body>
</html>

