<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Correlatividad" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

Curso curso = (Curso) request.getAttribute("curso");
if (curso == null) {
    response.sendRedirect("cursos?accion=listar");
    return;
}

@SuppressWarnings("unchecked")
List<Correlatividad> correlativas = (List<Correlatividad>) request.getAttribute("correlativas");
@SuppressWarnings("unchecked")
List<Correlatividad> dependientes = (List<Correlatividad>) request.getAttribute("dependientes");
@SuppressWarnings("unchecked")
List<Curso> todosLosCursos = (List<Curso>) request.getAttribute("todosLosCursos");

String error = request.getParameter("error");
String success = request.getParameter("success");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionar Correlatividades - <%= curso.getNombre() %></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-link"></i> Gestionar Correlatividades</h2>
                <a href="cursos?accion=ver&id=<%= curso.getId() %>&vista=detalle" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Volver al Curso
                </a>
            </div>
            
            <!-- Info del Curso -->
            <div class="info-card">
                <h3><%= curso.getCodigoCurso() %> - <%= curso.getNombre() %></h3>
                <p><%= curso.getDescripcion() != null ? curso.getDescripcion() : "Sin descripción" %></p>
                <p><strong>Créditos:</strong> <%= curso.getCreditos() %> | <strong>Horas:</strong> <%= curso.getHorasSemanales() %></p>
            </div>
            
            <% if (error != null) { %>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-circle"></i>
                    <% if (error.equals("mismo_curso")) { %>
                        No puede agregar el curso como correlativa de sí mismo
                    <% } else if (error.equals("duplicada")) { %>
                        Esta correlatividad ya existe
                    <% } else if (error.equals("eliminar")) { %>
                        Error al eliminar la correlatividad
                    <% } else { %>
                        Error al procesar la correlatividad
                    <% } %>
                </div>
            <% } %>
            
            <% if (success != null) { %>
                <div class="alert alert-success">
                    <i class="fas fa-check-circle"></i>
                    <% if (success.equals("agregada")) { %>
                        ✅ Correlatividad agregada exitosamente
                    <% } else if (success.equals("eliminada")) { %>
                        ✅ Correlatividad eliminada exitosamente
                    <% } %>
                </div>
            <% } %>
            
            <div class="form-container">
                <!-- Agregar Nueva Correlatividad -->
                <div class="form-section">
                    <h3><i class="fas fa-plus-circle"></i> Agregar Correlatividad</h3>
                    <p class="help-text">
                        Define qué cursos debe aprobar/cursar el estudiante antes de inscribirse a <strong><%= curso.getNombre() %></strong>
                    </p>
                    
                    <form action="correlatividades" method="post" class="student-form">
                        <input type="hidden" name="accion" value="agregar">
                        <input type="hidden" name="cursoId" value="<%= curso.getId() %>">
                        
                        <div class="form-grid">
                            <div class="form-group">
                                <label for="correlativaId"><i class="fas fa-book"></i> Curso Requerido *</label>
                                <select id="correlativaId" name="correlativaId" required>
                                    <option value="">Seleccione un curso...</option>
                                    <% if (todosLosCursos != null) {
                                        for (Curso c : todosLosCursos) {
                                            if (c.getId() != curso.getId()) { %>
                                                <option value="<%= c.getId() %>">
                                                    <%= c.getCodigoCurso() %> - <%= c.getNombre() %>
                                                </option>
                                            <% }
                                        }
                                    } %>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="tipo"><i class="fas fa-tags"></i> Tipo de Correlatividad *</label>
                                <select id="tipo" name="tipo" required>
                                    <option value="APROBADA">APROBADA - Debe aprobar el curso</option>
                                    <option value="REGULAR">REGULAR - Solo debe cursarlo</option>
                                </select>
                                <small class="help-text">
                                    <strong>APROBADA:</strong> El estudiante debe haber aprobado el curso<br>
                                    <strong>REGULAR:</strong> Solo necesita haberlo cursado (cualquier estado)
                                </small>
                            </div>
                        </div>
                        
                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">
                                <i class="fas fa-plus"></i> Agregar Correlatividad
                            </button>
                        </div>
                    </form>
                </div>
                
                <!-- Correlatividades Actuales -->
                <div class="correlativas-section">
                    <h3><i class="fas fa-list"></i> Correlatividades Actuales</h3>
                    
                    <div class="correlativas-container">
                        <!-- Cursos Requeridos -->
                        <div class="correlativas-requeridas">
                            <h4><i class="fas fa-arrow-circle-down"></i> Cursos Requeridos para este Curso</h4>
                            <% if (correlativas != null && !correlativas.isEmpty()) { %>
                                <table class="data-table">
                                    <thead>
                                        <tr>
                                            <th>Curso</th>
                                            <th>Tipo</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Correlatividad corr : correlativas) { %>
                                            <tr>
                                                <td><strong><%= corr.getCorrelativaNombre() %></strong></td>
                                                <td>
                                                    <span class="correlativa-tipo <%= corr.getTipo().name().equals("APROBADA") ? "tipo-aprobada" : "tipo-regular" %>">
                                                        <%= corr.getTipo().name() %>
                                                    </span>
                                                </td>
                                                <td class="actions">
                                                    <form action="correlatividades" method="post" style="display:inline;">
                                                        <input type="hidden" name="accion" value="eliminar">
                                                        <input type="hidden" name="id" value="<%= corr.getId() %>">
                                                        <input type="hidden" name="cursoId" value="<%= curso.getId() %>">
                                                        <button type="submit" class="btn btn-danger btn-sm" 
                                                                onclick="return confirm('¿Eliminar esta correlatividad?')">
                                                            <i class="fas fa-trash"></i> Eliminar
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            <% } else { %>
                                <p class="no-correlativas">
                                    <i class="fas fa-info-circle"></i> 
                                    Este curso no tiene correlatividades. Los estudiantes pueden inscribirse directamente.
                                </p>
                            <% } %>
                        </div>
                        
                        <!-- Cursos que Requieren Este Curso -->
                        <div class="correlativas-dependientes">
                            <h4><i class="fas fa-arrow-circle-up"></i> Cursos que Requieren este Curso</h4>
                            <% if (dependientes != null && !dependientes.isEmpty()) { %>
                                <table class="data-table">
                                    <thead>
                                        <tr>
                                            <th>Curso</th>
                                            <th>Tipo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Correlatividad dep : dependientes) { %>
                                            <tr>
                                                <td><strong><%= dep.getCursoNombre() %></strong></td>
                                                <td>
                                                    <span class="correlativa-tipo <%= dep.getTipo().name().equals("APROBADA") ? "tipo-aprobada" : "tipo-regular" %>">
                                                        <%= dep.getTipo().name() %>
                                                    </span>
                                                </td>
                                            </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            <% } else { %>
                                <p class="no-correlativas">
                                    <i class="fas fa-info-circle"></i> 
                                    Ningún curso requiere este curso como correlativa.
                                </p>
                            <% } %>
                        </div>
                    </div>
                </div>
                
                <div class="detail-actions">
                    <a href="cursos?accion=ver&id=<%= curso.getId() %>&vista=detalle" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Volver al Detalle del Curso
                    </a>
                    <a href="cursos?accion=listar" class="btn btn-outline">
                        <i class="fas fa-list"></i> Volver al Listado
                    </a>
                </div>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>


