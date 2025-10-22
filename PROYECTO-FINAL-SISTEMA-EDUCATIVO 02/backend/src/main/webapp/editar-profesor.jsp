<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Profesor" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

Profesor profesor = (Profesor) request.getAttribute("profesor");
if (profesor == null) {
    response.sendRedirect("profesores?accion=listar");
    return;
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Profesor - Sistema Educativo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2>✏️ Editar Profesor</h2>
            
            <div class="form-container">
                <form action="profesores" method="post" class="student-form">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= profesor.getId() %>">
                    
                    <div class="form-grid">
                        <!-- Información Personal (no editable) -->
                        <div class="form-group">
                            <label>Tipo de Documento</label>
                            <input type="text" value="<%= profesor.getTipoDocumento() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label>Número de Documento</label>
                            <input type="text" value="<%= profesor.getNumeroDocumento() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label for="nombre">Nombre *</label>
                            <input type="text" id="nombre" name="nombre" 
                                   value="<%= profesor.getNombre() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="apellido">Apellido *</label>
                            <input type="text" id="apellido" name="apellido" 
                                   value="<%= profesor.getApellido() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Fecha de Nacimiento</label>
                            <input type="date" value="<%= profesor.getFechaNacimiento() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" 
                                   value="<%= profesor.getEmail() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="telefono">Teléfono</label>
                            <input type="tel" id="telefono" name="telefono" 
                                   value="<%= profesor.getTelefono() != null ? profesor.getTelefono() : "" %>" 
                                   pattern="[0-9]{10}">
                        </div>
                        
                        <div class="form-group">
                            <label for="direccion">Dirección</label>
                            <input type="text" id="direccion" name="direccion" 
                                   value="<%= profesor.getDireccion() != null ? profesor.getDireccion() : "" %>">
                        </div>
                        
                        <!-- Información Laboral -->
                        <div class="form-group">
                            <label>Código de Profesor</label>
                            <input type="text" value="<%= profesor.getCodigoProfesor() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label>Fecha de Contratación</label>
                            <input type="date" value="<%= profesor.getFechaContratacion() %>" disabled>
                        </div>
                        
                        <div class="form-group">
                            <label for="sueldo">Sueldo *</label>
                            <input type="number" id="sueldo" name="sueldo" 
                                   value="<%= profesor.getSueldo() %>" 
                                   step="0.01" min="0" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="especialidad">Especialidad *</label>
                            <input type="text" id="especialidad" name="especialidad" 
                                   value="<%= profesor.getEspecialidad() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="gradoAcademico">Grado Académico</label>
                            <select id="gradoAcademico" name="gradoAcademico">
                                <option value="">Seleccione...</option>
                                <option value="Licenciado" <%= "Licenciado".equals(profesor.getGradoAcademico()) ? "selected" : "" %>>Licenciado</option>
                                <option value="Magister" <%= "Magister".equals(profesor.getGradoAcademico()) ? "selected" : "" %>>Magister</option>
                                <option value="Doctor" <%= "Doctor".equals(profesor.getGradoAcademico()) ? "selected" : "" %>>Doctor</option>
                                <option value="Profesor" <%= "Profesor".equals(profesor.getGradoAcademico()) ? "selected" : "" %>>Profesor</option>
                                <option value="Técnico" <%= "Técnico".equals(profesor.getGradoAcademico()) ? "selected" : "" %>>Técnico Superior</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="estadoLaboral">Estado Laboral *</label>
                            <select id="estadoLaboral" name="estadoLaboral" required>
                                <option value="ACTIVO" <%= "ACTIVO".equals(profesor.getEstadoLaboral()) ? "selected" : "" %>>Activo</option>
                                <option value="LICENCIA" <%= "LICENCIA".equals(profesor.getEstadoLaboral()) ? "selected" : "" %>>En Licencia</option>
                                <option value="RETIRADO" <%= "RETIRADO".equals(profesor.getEstadoLaboral()) ? "selected" : "" %>>Retirado</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="alert alert-info">
                        ℹ️ Los campos deshabilitados no se pueden modificar por seguridad.
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">💾 Guardar Cambios</button>
                        <a href="profesores?accion=listar" class="btn btn-secondary">❌ Cancelar</a>
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


