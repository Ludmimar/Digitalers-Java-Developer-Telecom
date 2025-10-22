<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
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
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Curso - Sistema Educativo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2>✏️ Editar Curso</h2>
            
            <div class="form-container">
                <form action="cursos" method="post" class="student-form">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= curso.getId() %>">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label>Código del Curso</label>
                            <input type="text" value="<%= curso.getCodigoCurso() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label for="nombre">Nombre del Curso *</label>
                            <input type="text" id="nombre" name="nombre" 
                                   value="<%= curso.getNombre() %>" required>
                        </div>
                        
                        <div class="form-group full-width">
                            <label for="descripcion">Descripción</label>
                            <textarea id="descripcion" name="descripcion" rows="4"><%= curso.getDescripcion() != null ? curso.getDescripcion() : "" %></textarea>
                        </div>
                        
                        <div class="form-group">
                            <label for="creditos">Créditos *</label>
                            <input type="number" id="creditos" name="creditos" 
                                   value="<%= curso.getCreditos() %>" min="1" max="10" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="horasSemanales">Horas Semanales *</label>
                            <input type="number" id="horasSemanales" name="horasSemanales" 
                                   value="<%= curso.getHorasSemanales() %>" min="1" max="20" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="cupoMaximo">Cupo Máximo *</label>
                            <input type="number" id="cupoMaximo" name="cupoMaximo" 
                                   value="<%= curso.getCupoMaximo() %>" min="5" max="100" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="estado">Estado *</label>
                            <select id="estado" name="estado" required>
                                <option value="ACTIVO" <%= "ACTIVO".equals(curso.getEstado()) ? "selected" : "" %>>Activo</option>
                                <option value="INACTIVO" <%= "INACTIVO".equals(curso.getEstado()) ? "selected" : "" %>>Inactivo</option>
                                <option value="FINALIZADO" <%= "FINALIZADO".equals(curso.getEstado()) ? "selected" : "" %>>Finalizado</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="alert alert-info">
                        ℹ️ El código del curso no se puede modificar por seguridad.
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">💾 Guardar Cambios</button>
                        <a href="cursos?accion=listar" class="btn btn-secondary">❌ Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>

<style>
.form-container {
    background: var(--white);
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    margin-top: 1rem;
}

.form-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 1.5rem;
    margin-bottom: 2rem;
}

.form-group.full-width {
    grid-column: 1 / -1;
}

.form-group input:disabled {
    background: #e5e7eb;
    cursor: not-allowed;
}

.form-group textarea {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #d1d5db;
    border-radius: 6px;
    font-family: inherit;
    resize: vertical;
}

.form-actions {
    display: flex;
    gap: 1rem;
    justify-content: flex-end;
}

.alert-info {
    background: #dbeafe;
    color: #1e40af;
    padding: 1rem;
    border-radius: 6px;
    margin-bottom: 1.5rem;
    border-left: 4px solid var(--info);
}
</style>

