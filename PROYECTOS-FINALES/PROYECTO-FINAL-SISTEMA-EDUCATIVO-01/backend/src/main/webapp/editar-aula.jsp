<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula.TipoAula" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula.EstadoAula" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

Aula aula = (Aula) request.getAttribute("aula");
if (aula == null) {
    response.sendRedirect("aulas?accion=listar");
    return;
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Aula - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-edit"></i> Editar Aula</h2>
            
            <div class="form-container">
                <form action="aulas" method="post" class="student-form">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= aula.getId() %>">
                    
                    <div class="form-grid">
                        <!-- Código del Aula (NO EDITABLE) -->
                        <div class="form-group">
                            <label for="codigo"><i class="fas fa-barcode"></i> Código del Aula *</label>
                            <input type="text" id="codigo" name="codigo" 
                                   value="<%= aula.getCodigo() %>" readonly
                                   style="background-color: #f3f4f6; cursor: not-allowed;">
                            <small>El código no se puede modificar</small>
                        </div>
                        
                        <!-- Nombre del Aula -->
                        <div class="form-group">
                            <label for="nombre"><i class="fas fa-signature"></i> Nombre del Aula *</label>
                            <input type="text" id="nombre" name="nombre" required
                                   minlength="3" maxlength="100"
                                   value="<%= aula.getNombre() %>">
                        </div>
                        
                        <!-- Tipo de Aula -->
                        <div class="form-group">
                            <label for="tipo"><i class="fas fa-list"></i> Tipo de Aula *</label>
                            <select id="tipo" name="tipo" required>
                                <% for (TipoAula tipo : TipoAula.values()) { %>
                                    <option value="<%= tipo.name() %>" <%= tipo == aula.getTipo() ? "selected" : "" %>>
                                        <%= tipo.name() %>
                                    </option>
                                <% } %>
                            </select>
                        </div>
                        
                        <!-- Capacidad -->
                        <div class="form-group">
                            <label for="capacidad"><i class="fas fa-users"></i> Capacidad *</label>
                            <input type="number" id="capacidad" name="capacidad" required
                                   min="5" max="300"
                                   value="<%= aula.getCapacidad() %>">
                        </div>
                        
                        <!-- Edificio -->
                        <div class="form-group">
                            <label for="edificio"><i class="fas fa-building"></i> Edificio</label>
                            <input type="text" id="edificio" name="edificio"
                                   maxlength="50"
                                   value="<%= aula.getEdificio() != null ? aula.getEdificio() : "" %>">
                        </div>
                        
                        <!-- Piso -->
                        <div class="form-group">
                            <label for="piso"><i class="fas fa-layer-group"></i> Piso</label>
                            <input type="number" id="piso" name="piso"
                                   min="-2" max="20"
                                   value="<%= aula.getPiso() != null ? aula.getPiso() : "" %>">
                        </div>
                        
                        <!-- Estado -->
                        <div class="form-group">
                            <label for="estado"><i class="fas fa-toggle-on"></i> Estado *</label>
                            <select id="estado" name="estado" required>
                                <% for (EstadoAula estado : EstadoAula.values()) { %>
                                    <option value="<%= estado.name() %>" <%= estado == aula.getEstado() ? "selected" : "" %>>
                                        <%= estado.name() %>
                                    </option>
                                <% } %>
                            </select>
                        </div>
                        
                        <!-- Equipamiento -->
                        <div class="form-group full-width">
                            <label for="equipamiento"><i class="fas fa-boxes"></i> Equipamiento</label>
                            <textarea id="equipamiento" name="equipamiento" rows="3"><%= aula.getEquipamiento() != null ? aula.getEquipamiento() : "" %></textarea>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar Cambios
                        </button>
                        <a href="aulas?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
</body>
</html>

<style>
.form-group.full-width {
    grid-column: 1 / -1;
}
</style>


