<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.PeriodoAcademico" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.PeriodoAcademico.EstadoPeriodo" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

PeriodoAcademico periodo = (PeriodoAcademico) request.getAttribute("periodo");
if (periodo == null) {
    response.sendRedirect("periodos?accion=listar");
    return;
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Período - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-edit"></i> Editar Período Académico</h2>
            
            <div class="form-container">
                <form action="periodos" method="post" class="student-form" onsubmit="return validarFormularioPeriodo(this)">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= periodo.getId() %>">
                    
                    <div class="form-grid">
                        <!-- Año -->
                        <div class="form-group">
                            <label for="anio"><i class="fas fa-calendar"></i> Año *</label>
                            <input type="number" id="anio" name="anio" required
                                   min="2020" max="2030"
                                   value="<%= periodo.getAnio() %>"
                                   onchange="actualizarNombre()">
                        </div>
                        
                        <!-- Semestre -->
                        <div class="form-group">
                            <label for="semestre"><i class="fas fa-list-ol"></i> Semestre *</label>
                            <select id="semestre" name="semestre" required onchange="actualizarNombre()">
                                <option value="1" <%= "1".equals(periodo.getSemestre()) ? "selected" : "" %>>1° Semestre</option>
                                <option value="2" <%= "2".equals(periodo.getSemestre()) ? "selected" : "" %>>2° Semestre</option>
                            </select>
                        </div>
                        
                        <!-- Nombre -->
                        <div class="form-group full-width">
                            <label for="nombre"><i class="fas fa-tag"></i> Nombre del Período *</label>
                            <input type="text" id="nombre" name="nombre" required
                                   maxlength="100"
                                   value="<%= periodo.getNombre() %>">
                        </div>
                        
                        <!-- Descripción -->
                        <div class="form-group full-width">
                            <label for="descripcion"><i class="fas fa-align-left"></i> Descripción</label>
                            <textarea id="descripcion" name="descripcion" rows="2"><%= periodo.getDescripcion() != null ? periodo.getDescripcion() : "" %></textarea>
                        </div>
                        
                        <!-- Fechas de Inscripción -->
                        <div class="form-group">
                            <label for="fechaInicioInscripciones">
                                <i class="fas fa-calendar-plus"></i> Inicio Inscripciones *
                            </label>
                            <input type="date" id="fechaInicioInscripciones" name="fechaInicioInscripciones" required
                                   value="<%= periodo.getFechaInicioInscripciones() != null ? periodo.getFechaInicioInscripciones() : "" %>">
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaFinInscripciones">
                                <i class="fas fa-calendar-minus"></i> Fin Inscripciones *
                            </label>
                            <input type="date" id="fechaFinInscripciones" name="fechaFinInscripciones" required
                                   value="<%= periodo.getFechaFinInscripciones() != null ? periodo.getFechaFinInscripciones() : "" %>">
                        </div>
                        
                        <!-- Fechas de Clases -->
                        <div class="form-group">
                            <label for="fechaInicioClases">
                                <i class="fas fa-calendar-check"></i> Inicio de Clases *
                            </label>
                            <input type="date" id="fechaInicioClases" name="fechaInicioClases" required
                                   value="<%= periodo.getFechaInicioClases() != null ? periodo.getFechaInicioClases() : "" %>">
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaFinClases">
                                <i class="fas fa-calendar-times"></i> Fin de Clases *
                            </label>
                            <input type="date" id="fechaFinClases" name="fechaFinClases" required
                                   value="<%= periodo.getFechaFinClases() != null ? periodo.getFechaFinClases() : "" %>">
                        </div>
                        
                        <!-- Estado -->
                        <div class="form-group">
                            <label for="estado"><i class="fas fa-flag"></i> Estado *</label>
                            <select id="estado" name="estado" required>
                                <% for (EstadoPeriodo estado : EstadoPeriodo.values()) { %>
                                    <option value="<%= estado.name() %>" <%= estado == periodo.getEstado() ? "selected" : "" %>>
                                        <%= estado.name() %>
                                    </option>
                                <% } %>
                            </select>
                        </div>
                        
                        <!-- Activo -->
                        <div class="form-group">
                            <label for="activo"><i class="fas fa-toggle-on"></i> Período Activo</label>
                            <div style="display: flex; align-items: center; gap: 0.5rem; margin-top: 0.5rem;">
                                <input type="checkbox" id="activo" name="activo" 
                                       <%= periodo.getActivo() ? "checked" : "" %>
                                       style="width: auto; height: 20px;">
                                <small>Si lo activa, desactivará todos los demás períodos</small>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar Cambios
                        </button>
                        <a href="periodos?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    function actualizarNombre() {
        const anio = document.getElementById('anio').value;
        const semestre = document.getElementById('semestre').value;
        
        if (anio && semestre) {
            const nombre = anio + '-' + semestre;
            document.getElementById('nombre').value = nombre;
        }
    }
    
    function validarFormularioPeriodo(form) {
        const fechaInicioInsc = new Date(form.fechaInicioInscripciones.value);
        const fechaFinInsc = new Date(form.fechaFinInscripciones.value);
        const fechaInicioClases = new Date(form.fechaInicioClases.value);
        const fechaFinClases = new Date(form.fechaFinClases.value);
        
        if (fechaFinInsc >= fechaInicioClases) {
            alert('⚠️ La fecha de fin de inscripciones debe ser anterior al inicio de clases.');
            return false;
        }
        
        if (fechaInicioInsc >= fechaFinInsc) {
            alert('⚠️ La fecha de inicio de inscripciones debe ser anterior a la fecha de fin.');
            return false;
        }
        
        if (fechaInicioClases >= fechaFinClases) {
            alert('⚠️ La fecha de inicio de clases debe ser anterior a la fecha de fin.');
            return false;
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


