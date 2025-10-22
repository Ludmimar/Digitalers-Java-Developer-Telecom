<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
String busqueda = (String) request.getAttribute("busqueda");
String estadoFiltro = (String) request.getAttribute("estadoFiltro");
String success = request.getParameter("success");
String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Cursos - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2>📚 Gestión de Cursos</h2>
                <a href="cursos?accion=nuevo" class="btn btn-primary">
                    ➕ Nuevo Curso
                </a>
            </div>

            <% if (success != null) { %>
                <div class="alert alert-success">
                    <i class="fas fa-check-circle"></i>
                    <% if (success.equals("insert")) { %>
                        Curso registrado exitosamente
                    <% } else if (success.equals("update")) { %>
                        Curso actualizado exitosamente
                    <% } else if (success.equals("delete")) { %>
                        Curso eliminado exitosamente
                    <% } else if (success.equals("asignado")) { %>
                        Curso asignado al período exitosamente
                    <% } %>
                </div>
            <% } %>

            <% if (error != null) { %>
                <div class="alert alert-danger">
                    ❌ Error al procesar la operación
                </div>
            <% } %>

            <!-- Filtros de búsqueda -->
            <div class="filter-section">
                <form action="cursos" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="listar">
                    <div class="filter-group">
                        <input type="text" name="busqueda" placeholder="Buscar por nombre..." 
                               value="<%= busqueda != null ? busqueda : "" %>" class="search-input">
                        <button type="submit" class="btn btn-secondary">🔍 Buscar</button>
                    </div>
                </form>
                
                <form action="cursos" method="get" class="filter-form">
                    <input type="hidden" name="accion" value="listar">
                    <select name="estado" onchange="this.form.submit()" class="filter-select">
                        <option value="">Todos los estados</option>
                        <option value="ACTIVO" <%= "ACTIVO".equals(estadoFiltro) ? "selected" : "" %>>Activos</option>
                        <option value="INACTIVO" <%= "INACTIVO".equals(estadoFiltro) ? "selected" : "" %>>Inactivos</option>
                        <option value="FINALIZADO" <%= "FINALIZADO".equals(estadoFiltro) ? "selected" : "" %>>Finalizados</option>
                    </select>
                </form>
                
                <% if (busqueda != null || estadoFiltro != null) { %>
                    <a href="cursos?accion=listar" class="btn btn-secondary">❌ Limpiar Filtros</a>
                <% } %>
            </div>

            <!-- Tabla de cursos -->
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Créditos</th>
                            <th>Horas/Sem</th>
                            <th>Cupo</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (cursos != null && !cursos.isEmpty()) { 
                            for (Curso curso : cursos) { %>
                                <tr>
                                    <td><strong><%= curso.getCodigoCurso() %></strong></td>
                                    <td><%= curso.getNombre() %></td>
                                    <td><%= curso.getDescripcion() != null && curso.getDescripcion().length() > 50 
                                        ? curso.getDescripcion().substring(0, 50) + "..." 
                                        : curso.getDescripcion() %></td>
                                    <td><%= curso.getCreditos() %></td>
                                    <td><%= curso.getHorasSemanales() %></td>
                                    <td><%= curso.getCupoMaximo() %></td>
                                    <td>
                                        <span class="badge <%= curso.getEstado().equals("ACTIVO") ? "badge-success" : 
                                                               curso.getEstado().equals("INACTIVO") ? "badge-warning" : "badge-secondary" %>">
                                            <%= curso.getEstado() %>
                                        </span>
                                    </td>
                                    <td class="actions">
                                        <a href="cursos?accion=ver&id=<%= curso.getId() %>&vista=detalle" 
                                           class="btn-icon" title="Ver detalle">
                                            <i class="fas fa-eye"></i>
                                        </a>
                                    </td>
                                </tr>
                            <% }
                        } else { %>
                            <tr>
                                <td colspan="8" style="text-align: center; padding: 2rem;">
                                    ℹ️ No se encontraron cursos
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <div class="stats-info">
                <p>📊 Total de cursos: <strong><%= cursos != null ? cursos.size() : 0 %></strong></p>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    function confirmarEliminacion(id, nombre) {
        if (confirm('¿Está seguro de eliminar el curso "' + nombre + '"?\n\nEsta acción no se puede deshacer.')) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'cursos';
            
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

