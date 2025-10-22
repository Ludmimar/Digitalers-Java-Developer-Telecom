<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Estudiante" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

Estudiante estudiante = (Estudiante) request.getAttribute("estudiante");
if (estudiante == null) {
    response.sendRedirect("estudiantes?accion=listar");
    return;
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Estudiante - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2>✏️ Editar Estudiante</h2>
            
            <div class="form-container">
                <form action="estudiantes" method="post" class="student-form">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= estudiante.getId() %>">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label>Tipo de Documento</label>
                            <input type="text" value="<%= estudiante.getTipoDocumento() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label>Número de Documento</label>
                            <input type="text" value="<%= estudiante.getNumeroDocumento() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label for="nombre">Nombre *</label>
                            <input type="text" id="nombre" name="nombre" 
                                   value="<%= estudiante.getNombre() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="apellido">Apellido *</label>
                            <input type="text" id="apellido" name="apellido" 
                                   value="<%= estudiante.getApellido() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Fecha de Nacimiento</label>
                            <input type="date" value="<%= estudiante.getFechaNacimiento() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" 
                                   value="<%= estudiante.getEmail() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="telefono">Teléfono</label>
                            <input type="tel" id="telefono" name="telefono" 
                                   value="<%= estudiante.getTelefono() %>" pattern="[0-9]{10}">
                        </div>
                        
                        <div class="form-group">
                            <label for="direccion">Dirección</label>
                            <input type="text" id="direccion" name="direccion" 
                                   value="<%= estudiante.getDireccion() %>">
                        </div>
                        
                        <div class="form-group">
                            <label>Matrícula</label>
                            <input type="text" value="<%= estudiante.getMatricula() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label>Fecha de Ingreso</label>
                            <input type="date" value="<%= estudiante.getFechaIngreso() %>" disabled>
                        </div>
                    </div>
                    
                    <div class="alert alert-info">
                        ℹ️ Los campos deshabilitados no se pueden modificar por seguridad.
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">💾 Guardar Cambios</button>
                        <a href="estudiantes?accion=listar" class="btn btn-secondary">❌ Cancelar</a>
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

.form-group input:disabled {
    background: #e5e7eb;
    cursor: not-allowed;
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


