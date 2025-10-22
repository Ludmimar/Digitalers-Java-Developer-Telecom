<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Profesor" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

List<Profesor> profesores = (List<Profesor>) request.getAttribute("profesores");
String mensaje = (String) request.getAttribute("mensaje");
String error = (String) request.getAttribute("error");
String codigoBusqueda = request.getParameter("codigo");
String estadoFiltro = request.getParameter("estado");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profesores - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-chalkboard-teacher"></i> Gestión de Profesores</h2>
                <a href="profesores?accion=nuevo" class="btn btn-primary">
                    <i class="fas fa-user-plus"></i> Nuevo Profesor
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
                <form action="profesores" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="buscar">
                    <div class="filter-group">
                        <input type="text" name="codigo" placeholder="🔍 Buscar por código o nombre..." 
                               value="<%= codigoBusqueda != null ? codigoBusqueda : "" %>"
                               class="search-input">
                        <button type="submit" class="btn btn-secondary">
                            <i class="fas fa-search"></i> Buscar
                        </button>
                    </div>
                </form>
                
                <form action="profesores" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="buscar">
                    <select name="estado" onchange="this.form.submit()" class="filter-select">
                        <option value="">Todos los estados</option>
                        <option value="ACTIVO" <%= "ACTIVO".equals(estadoFiltro) ? "selected" : "" %>>✅ Activo</option>
                        <option value="INACTIVO" <%= "INACTIVO".equals(estadoFiltro) ? "selected" : "" %>>❌ Inactivo</option>
                        <option value="LICENCIA" <%= "LICENCIA".equals(estadoFiltro) ? "selected" : "" %>>🏥 Licencia</option>
                        <option value="JUBILADO" <%= "JUBILADO".equals(estadoFiltro) ? "selected" : "" %>>👴 Jubilado</option>
                    </select>
                </form>
                
                <% if (codigoBusqueda != null || estadoFiltro != null) { %>
                    <a href="profesores?accion=listar" class="btn btn-secondary">
                        <i class="fas fa-times"></i> Limpiar Filtros
                    </a>
                <% } %>
            </div>
            
            <!-- Tabla de Profesores -->
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre Completo</th>
                            <th>Email</th>
                            <th>Especialidad</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        if (profesores != null && !profesores.isEmpty()) {
                            for (Profesor prof : profesores) {
                        %>
                        <tr>
                            <td><strong><%= prof.getCodigoProfesor() %></strong></td>
                            <td><%= prof.getNombreCompleto() %></td>
                            <td><%= prof.getEmail() %></td>
                            <td><%= prof.getEspecialidad() %></td>
                            <td>
                                <span class="badge <%= prof.getEstadoLaboral().equals("ACTIVO") ? "badge-success" : "badge-warning" %>">
                                    <%= prof.getEstadoLaboral() %>
                                </span>
                            </td>
                            <td class="actions">
                                <a href="profesores?accion=ver&id=<%= prof.getId() %>&vista=detalle" 
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
                            <td colspan="6" style="text-align: center; padding: 2rem;">
                                <i class="fas fa-info-circle"></i> No hay profesores registrados
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
        if (confirm('¿Está seguro de eliminar al profesor ' + nombre + '?')) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'profesores';
            
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


