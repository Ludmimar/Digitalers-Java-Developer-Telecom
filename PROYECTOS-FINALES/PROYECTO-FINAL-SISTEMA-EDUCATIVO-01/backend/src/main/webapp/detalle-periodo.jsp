<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.PeriodoAcademico" %>
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

// Verificar errores
String errorTipo = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Período - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-calendar-alt"></i> Detalle del Período Académico</h2>
                <div>
                    <a href="periodos?accion=ver&id=<%= periodo.getId() %>" 
                       class="btn btn-secondary"><i class="fas fa-edit"></i> Editar</a>
                    <a href="periodos?accion=listar" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Volver</a>
                </div>
            </div>
            
            <!-- Mensaje de Error -->
            <% if ("tiene_cursos".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                <div>
                    <strong>No se puede eliminar este período académico</strong><br>
                    Este período tiene cursos ofrecidos asignados. 
                    Debe eliminar todos los cursos ofrecidos antes de eliminar el período.
                </div>
            </div>
            <% } %>
            
            <!-- Banner si es el período activo -->
            <% if (periodo.getActivo()) { %>
            <div class="alert alert-info" style="background: linear-gradient(135deg, #dbeafe 0%, #93c5fd 100%); border-left-color: var(--primary);">
                <i class="fas fa-star" style="color: gold; font-size: 1.5rem;"></i>
                <div>
                    <strong style="font-size: 1.2rem;">Este es el Período Activo</strong><br>
                    <small>Todas las nuevas inscripciones y asignaciones se realizan en este período</small>
                </div>
            </div>
            <% } %>
            
            <div class="detail-container">
                <!-- Información General -->
                <div class="detail-section">
                    <h3><i class="fas fa-info-circle"></i> Información General</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Nombre del Período:</span>
                            <span class="detail-value"><strong><%= periodo.getNombre() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Año:</span>
                            <span class="detail-value"><%= periodo.getAnio() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Semestre:</span>
                            <span class="detail-value"><%= periodo.getSemestre() %>° Semestre</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estado:</span>
                            <span class="badge <%= 
                                periodo.getEstado().name().equals("CURSANDO") ? "badge-success" : 
                                periodo.getEstado().name().equals("INSCRIPCION") ? "badge-info" :
                                periodo.getEstado().name().equals("PLANIFICACION") ? "badge-warning" : "badge-secondary" %>">
                                <%= periodo.getEstado() %>
                            </span>
                        </div>
                        <div class="detail-item full-width">
                            <span class="detail-label">Descripción:</span>
                            <span class="detail-value"><%= periodo.getDescripcion() != null ? periodo.getDescripcion() : "No especificada" %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Período de Inscripciones -->
                <div class="detail-section">
                    <h3><i class="fas fa-user-plus"></i> Período de Inscripciones</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Inicio:</span>
                            <span class="detail-value">
                                <i class="fas fa-calendar-plus"></i>
                                <%= periodo.getFechaInicioInscripciones() != null ? periodo.getFechaInicioInscripciones() : "No definida" %>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Fin:</span>
                            <span class="detail-value">
                                <i class="fas fa-calendar-minus"></i>
                                <%= periodo.getFechaFinInscripciones() != null ? periodo.getFechaFinInscripciones() : "No definida" %>
                            </span>
                        </div>
                        <div class="detail-item full-width">
                            <span class="detail-label">Rango Completo:</span>
                            <span class="detail-value"><strong><%= periodo.getRangoInscripciones() %></strong></span>
                        </div>
                    </div>
                </div>
                
                <!-- Período de Clases -->
                <div class="detail-section">
                    <h3><i class="fas fa-chalkboard-teacher"></i> Período de Clases</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Inicio:</span>
                            <span class="detail-value">
                                <i class="fas fa-calendar-check"></i>
                                <%= periodo.getFechaInicioClases() != null ? periodo.getFechaInicioClases() : "No definida" %>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Fin:</span>
                            <span class="detail-value">
                                <i class="fas fa-calendar-times"></i>
                                <%= periodo.getFechaFinClases() != null ? periodo.getFechaFinClases() : "No definida" %>
                            </span>
                        </div>
                        <div class="detail-item full-width">
                            <span class="detail-label">Rango Completo:</span>
                            <span class="detail-value"><strong><%= periodo.getRangoClases() %></strong></span>
                        </div>
                    </div>
                </div>
                
                <!-- Acciones -->
                <div class="detail-actions">
                    <% if (!periodo.getActivo()) { %>
                    <form method="post" action="periodos" style="display: inline;">
                        <input type="hidden" name="accion" value="activar">
                        <input type="hidden" name="id" value="<%= periodo.getId() %>">
                        <button type="submit" class="btn btn-success">
                            <i class="fas fa-toggle-on"></i> Activar Período
                        </button>
                    </form>
                    <% } %>
                    <a href="periodos?accion=ver&id=<%= periodo.getId() %>" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Editar Información
                    </a>
                    <button onclick="confirmarEliminacion(<%= periodo.getId() %>, '<%= periodo.getNombre().replace("'", "\\'") %>')" 
                            class="btn btn-danger">
                        <i class="fas fa-trash"></i> Eliminar Período
                    </button>
                </div>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <!-- Modal de Confirmación -->
    <div id="modalConfirmacion" class="modal-overlay">
        <div class="modal-content">
            <div class="modal-header danger">
                <div class="modal-icon danger">
                    <i class="fas fa-exclamation-triangle"></i>
                </div>
                <div>
                    <h3 class="modal-title">Confirmar Eliminación</h3>
                </div>
            </div>
            <div class="modal-body">
                <p class="modal-message">
                    ¿Está seguro de que desea eliminar este período académico?
                </p>
                <div class="modal-detail">
                    <strong>Período:</strong> <span id="modalPeriodoNombre"></span>
                </div>
                <p class="modal-message" style="margin-top: 1rem; color: var(--danger); font-weight: 600;">
                    <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                </p>
            </div>
            <div class="modal-footer">
                <button onclick="cerrarModal()" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </button>
                <button onclick="eliminarPeriodo()" class="btn btn-danger">
                    <i class="fas fa-trash"></i> Sí, Eliminar
                </button>
            </div>
        </div>
    </div>
    
    <script>
    let periodoIdEliminar = null;
    
    function confirmarEliminacion(id, nombre) {
        periodoIdEliminar = id;
        document.getElementById('modalPeriodoNombre').textContent = nombre;
        document.getElementById('modalConfirmacion').classList.add('active');
    }
    
    function cerrarModal() {
        document.getElementById('modalConfirmacion').classList.remove('active');
        periodoIdEliminar = null;
    }
    
    function eliminarPeriodo() {
        if (periodoIdEliminar) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'periodos';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = periodoIdEliminar;
            
            form.appendChild(inputAccion);
            form.appendChild(inputId);
            document.body.appendChild(form);
            form.submit();
        }
    }
    
    // Cerrar modal con click fuera o ESC
    document.getElementById('modalConfirmacion').addEventListener('click', function(e) {
        if (e.target === this) cerrarModal();
    });
    
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') cerrarModal();
    });
    </script>
</body>
</html>

<style>
.form-group.full-width,
.detail-item.full-width {
    grid-column: 1 / -1;
}

.detail-container {
    background: var(--white);
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.detail-section {
    padding: 2rem;
    border-bottom: 1px solid #e5e7eb;
}

.detail-section:last-child {
    border-bottom: none;
}

.detail-section h3 {
    margin-bottom: 1.5rem;
    color: var(--primary);
    font-size: 1.25rem;
}

.detail-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 1.5rem;
}

.detail-item {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
}

.detail-label {
    font-weight: 600;
    color: #6b7280;
    font-size: 0.9rem;
}

.detail-value {
    font-size: 1.1rem;
    color: var(--dark);
}

.detail-actions {
    padding: 2rem;
    background: var(--light);
    display: flex;
    gap: 1rem;
    justify-content: center;
    flex-wrap: wrap;
}
</style>


