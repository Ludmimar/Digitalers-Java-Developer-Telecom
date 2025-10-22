<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula.TipoAula" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula.EstadoAula" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

// Verificar errores
String errorTipo = request.getParameter("error");
String codigoError = request.getParameter("codigo");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Aula - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-door-open"></i> Registrar Nueva Aula</h2>
            
            <!-- Mensajes de error -->
            <% if ("codigo_duplicado".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-exclamation-circle"></i>
                El código de aula <strong><%= codigoError %></strong> ya está registrado. Por favor, use otro código.
            </div>
            <% } else if ("insert".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                Error al registrar el aula. Por favor, verifique los datos e intente nuevamente.
            </div>
            <% } %>
            
            <div class="form-container">
                <form action="aulas" method="post" class="student-form" onsubmit="return validarFormularioAula(this)">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <!-- Código del Aula -->
                        <div class="form-group">
                            <label for="codigo"><i class="fas fa-barcode"></i> Código del Aula *</label>
                            <input type="text" id="codigo" name="codigo" required
                                   pattern="[A-Z]{3,4}-[0-9]{3}"
                                   title="Formato: XXX-000 o XXXX-000 (Ej: AULA-101, LAB-201)"
                                   placeholder="Ej: AULA-101, LAB-201"
                                   value="<%= codigoError != null ? codigoError : "" %>">
                            <small>Formato: TIPO-NÚMERO (Ej: AULA-101, LAB-201)</small>
                        </div>
                        
                        <!-- Nombre del Aula -->
                        <div class="form-group">
                            <label for="nombre"><i class="fas fa-signature"></i> Nombre del Aula *</label>
                            <input type="text" id="nombre" name="nombre" required
                                   minlength="3" maxlength="100"
                                   placeholder="Ej: Aula 101, Laboratorio de Informática">
                        </div>
                        
                        <!-- Tipo de Aula -->
                        <div class="form-group">
                            <label for="tipo"><i class="fas fa-list"></i> Tipo de Aula *</label>
                            <select id="tipo" name="tipo" required>
                                <option value="">Seleccione un tipo...</option>
                                <% for (TipoAula tipo : TipoAula.values()) { %>
                                    <option value="<%= tipo.name() %>"><%= tipo.name() %></option>
                                <% } %>
                            </select>
                        </div>
                        
                        <!-- Capacidad -->
                        <div class="form-group">
                            <label for="capacidad"><i class="fas fa-users"></i> Capacidad *</label>
                            <input type="number" id="capacidad" name="capacidad" required
                                   min="5" max="300"
                                   placeholder="Ej: 30">
                            <small>Número de personas que puede albergar</small>
                        </div>
                        
                        <!-- Edificio -->
                        <div class="form-group">
                            <label for="edificio"><i class="fas fa-building"></i> Edificio</label>
                            <input type="text" id="edificio" name="edificio"
                                   maxlength="50"
                                   placeholder="Ej: Edificio A, Pabellón Central">
                        </div>
                        
                        <!-- Piso -->
                        <div class="form-group">
                            <label for="piso"><i class="fas fa-layer-group"></i> Piso</label>
                            <input type="number" id="piso" name="piso"
                                   min="-2" max="20"
                                   placeholder="Ej: 1, 2, 3">
                            <small>Use números negativos para subsuelos</small>
                        </div>
                        
                        <!-- Estado -->
                        <div class="form-group">
                            <label for="estado"><i class="fas fa-toggle-on"></i> Estado *</label>
                            <select id="estado" name="estado" required>
                                <% for (EstadoAula estado : EstadoAula.values()) { %>
                                    <option value="<%= estado.name() %>" <%= estado.name().equals("DISPONIBLE") ? "selected" : "" %>>
                                        <%= estado.name() %>
                                    </option>
                                <% } %>
                            </select>
                        </div>
                        
                        <!-- Equipamiento -->
                        <div class="form-group full-width">
                            <label for="equipamiento"><i class="fas fa-boxes"></i> Equipamiento</label>
                            <textarea id="equipamiento" name="equipamiento" rows="3"
                                      placeholder="Ej: Proyector, Pizarra Digital, Aire Acondicionado, 30 PCs"></textarea>
                            <small>Describa el equipamiento disponible en el aula</small>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Registrar Aula
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
    
    <script>
    function validarFormularioAula(form) {
        const capacidad = parseInt(form.capacidad.value);
        const tipo = form.tipo.value;
        
        // Validar capacidad según tipo
        if (tipo === 'LABORATORIO' && capacidad > 30) {
            if (!confirm('⚠️ La capacidad para un laboratorio parece alta (' + capacidad + ').\n\n¿Está seguro?')) {
                return false;
            }
        }
        
        if (tipo === 'AUDITORIO' && capacidad < 50) {
            if (!confirm('⚠️ La capacidad para un auditorio parece baja (' + capacidad + ').\n\n¿Está seguro?')) {
                return false;
            }
        }
        
        return true;
    }
    </script>
</body>
</html>

<style>
.form-group.full-width {
    grid-column: 1 / -1;
}
</style>


