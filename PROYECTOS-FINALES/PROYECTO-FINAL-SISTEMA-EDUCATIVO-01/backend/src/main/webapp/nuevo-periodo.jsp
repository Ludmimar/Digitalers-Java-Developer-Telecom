<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.PeriodoAcademico.EstadoPeriodo" %>
<%@ page import="java.time.LocalDate" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

// Sugerir año actual
int anioActual = LocalDate.now().getYear();

// Verificar errores
String errorTipo = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Período Académico - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-calendar-plus"></i> Crear Nuevo Período Académico</h2>
            
            <!-- Mensajes de error -->
            <% if ("insert".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                Error al crear el período académico. Por favor, verifique los datos e intente nuevamente.
            </div>
            <% } %>
            
            <div class="alert alert-info">
                <i class="fas fa-info-circle"></i>
                <div>
                    <strong>Información:</strong> Configure las fechas del período académico. 
                    El sistema generará automáticamente el nombre si lo deja en blanco.
                </div>
            </div>
            
            <div class="form-container">
                <form action="periodos" method="post" class="student-form" onsubmit="return validarFormularioPeriodo(this)">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <!-- Año -->
                        <div class="form-group">
                            <label for="anio"><i class="fas fa-calendar"></i> Año *</label>
                            <input type="number" id="anio" name="anio" required
                                   min="2020" max="2030"
                                   value="<%= anioActual %>"
                                   onchange="actualizarNombre()">
                        </div>
                        
                        <!-- Semestre -->
                        <div class="form-group">
                            <label for="semestre"><i class="fas fa-list-ol"></i> Semestre *</label>
                            <select id="semestre" name="semestre" required onchange="actualizarNombre()">
                                <option value="">Seleccione...</option>
                                <option value="1">1° Semestre</option>
                                <option value="2">2° Semestre</option>
                            </select>
                        </div>
                        
                        <!-- Nombre (auto-generado) -->
                        <div class="form-group full-width">
                            <label for="nombre"><i class="fas fa-tag"></i> Nombre del Período *</label>
                            <input type="text" id="nombre" name="nombre" required
                                   maxlength="100"
                                   placeholder="Se genera automáticamente">
                            <small>Ej: 2024-1, Primer Semestre 2024</small>
                        </div>
                        
                        <!-- Descripción -->
                        <div class="form-group full-width">
                            <label for="descripcion"><i class="fas fa-align-left"></i> Descripción</label>
                            <textarea id="descripcion" name="descripcion" rows="2"
                                      placeholder="Descripción del período académico"></textarea>
                        </div>
                        
                        <!-- Fechas de Inscripción -->
                        <div class="form-group">
                            <label for="fechaInicioInscripciones">
                                <i class="fas fa-calendar-plus"></i> Inicio Inscripciones *
                            </label>
                            <input type="date" id="fechaInicioInscripciones" name="fechaInicioInscripciones" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaFinInscripciones">
                                <i class="fas fa-calendar-minus"></i> Fin Inscripciones *
                            </label>
                            <input type="date" id="fechaFinInscripciones" name="fechaFinInscripciones" required>
                        </div>
                        
                        <!-- Fechas de Clases -->
                        <div class="form-group">
                            <label for="fechaInicioClases">
                                <i class="fas fa-calendar-check"></i> Inicio de Clases *
                            </label>
                            <input type="date" id="fechaInicioClases" name="fechaInicioClases" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaFinClases">
                                <i class="fas fa-calendar-times"></i> Fin de Clases *
                            </label>
                            <input type="date" id="fechaFinClases" name="fechaFinClases" required>
                        </div>
                        
                        <!-- Estado -->
                        <div class="form-group">
                            <label for="estado"><i class="fas fa-flag"></i> Estado *</label>
                            <select id="estado" name="estado" required>
                                <% for (EstadoPeriodo estado : EstadoPeriodo.values()) { %>
                                    <option value="<%= estado.name() %>" <%= estado.name().equals("PLANIFICACION") ? "selected" : "" %>>
                                        <%= estado.name() %>
                                    </option>
                                <% } %>
                            </select>
                            <small>PLANIFICACION → INSCRIPCION → CURSANDO → FINALIZADO</small>
                        </div>
                        
                        <!-- Activo -->
                        <div class="form-group">
                            <label for="activo"><i class="fas fa-toggle-on"></i> Período Activo</label>
                            <div style="display: flex; align-items: center; gap: 0.5rem; margin-top: 0.5rem;">
                                <input type="checkbox" id="activo" name="activo" style="width: auto; height: 20px;">
                                <small>Marque si este será el período activo (desactivará los demás)</small>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Crear Período
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
    // Auto-generar nombre del período
    function actualizarNombre() {
        const anio = document.getElementById('anio').value;
        const semestre = document.getElementById('semestre').value;
        
        if (anio && semestre) {
            const nombre = anio + '-' + semestre;
            document.getElementById('nombre').value = nombre;
            
            const descripcion = 'Período Académico ' + semestre + '° Semestre ' + anio;
            if (!document.getElementById('descripcion').value) {
                document.getElementById('descripcion').value = descripcion;
            }
        }
    }
    
    function validarFormularioPeriodo(form) {
        const fechaInicioInsc = new Date(form.fechaInicioInscripciones.value);
        const fechaFinInsc = new Date(form.fechaFinInscripciones.value);
        const fechaInicioClases = new Date(form.fechaInicioClases.value);
        const fechaFinClases = new Date(form.fechaFinClases.value);
        
        // Validar que las inscripciones terminen antes de las clases
        if (fechaFinInsc >= fechaInicioClases) {
            alert('⚠️ La fecha de fin de inscripciones debe ser anterior al inicio de clases.\n\n' +
                  'Fin inscripciones: ' + form.fechaFinInscripciones.value + '\n' +
                  'Inicio clases: ' + form.fechaInicioClases.value);
            form.fechaFinInscripciones.focus();
            return false;
        }
        
        // Validar que inicio < fin para inscripciones
        if (fechaInicioInsc >= fechaFinInsc) {
            alert('⚠️ La fecha de inicio de inscripciones debe ser anterior a la fecha de fin.\n\n' +
                  'Inicio: ' + form.fechaInicioInscripciones.value + '\n' +
                  'Fin: ' + form.fechaFinInscripciones.value);
            form.fechaInicioInscripciones.focus();
            return false;
        }
        
        // Validar que inicio < fin para clases
        if (fechaInicioClases >= fechaFinClases) {
            alert('⚠️ La fecha de inicio de clases debe ser anterior a la fecha de fin.\n\n' +
                  'Inicio: ' + form.fechaInicioClases.value + '\n' +
                  'Fin: ' + form.fechaFinClases.value);
            form.fechaInicioClases.focus();
            return false;
        }
        
        // Validar duración del semestre (mínimo 3 meses, máximo 8 meses)
        const duracionMeses = (fechaFinClases - fechaInicioClases) / (1000 * 60 * 60 * 24 * 30);
        if (duracionMeses < 3) {
            alert('⚠️ La duración del período es muy corta (< 3 meses).\n\n¿Está seguro?');
            if (!confirm('¿Desea continuar de todos modos?')) {
                return false;
            }
        }
        if (duracionMeses > 8) {
            alert('⚠️ La duración del período es muy larga (> 8 meses).\n\n¿Está seguro?');
            if (!confirm('¿Desea continuar de todos modos?')) {
                return false;
            }
        }
        
        return true;
    }
    
    // Auto-generar al cargar
    window.addEventListener('DOMContentLoaded', function() {
        actualizarNombre();
    });
    </script>
</body>
</html>

<style>
.form-group.full-width {
    grid-column: 1 / -1;
}
</style>


