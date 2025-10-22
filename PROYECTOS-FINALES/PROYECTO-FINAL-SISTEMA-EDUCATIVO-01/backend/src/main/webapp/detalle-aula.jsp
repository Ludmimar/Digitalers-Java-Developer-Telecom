<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula" %>
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

Integer cursosAsignados = (Integer) request.getAttribute("cursosAsignados");

// Verificar errores
String errorTipo = request.getParameter("error");
String cantidadCursos = request.getParameter("cantidad");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Aula - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-door-open"></i> Detalle del Aula</h2>
                <div>
                    <a href="aulas?accion=ver&id=<%= aula.getId() %>" 
                       class="btn btn-secondary"><i class="fas fa-edit"></i> Editar</a>
                    <a href="aulas?accion=listar" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Volver</a>
                </div>
            </div>
            
            <!-- Mensaje de Error -->
            <% if ("tiene_cursos".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                <div>
                    <strong>No se puede eliminar esta aula</strong><br>
                    Esta aula tiene <strong><%= cantidadCursos %></strong> curso(s) asignado(s). 
                    Debe reasignar todos los cursos a otras aulas antes de eliminar.
                </div>
            </div>
            <% } %>
            
            <div class="detail-container">
                <!-- Información General -->
                <div class="detail-section">
                    <h3><i class="fas fa-info-circle"></i> Información General</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Código del Aula:</span>
                            <span class="detail-value"><strong><%= aula.getCodigo() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Nombre:</span>
                            <span class="detail-value"><%= aula.getNombre() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Tipo:</span>
                            <span class="badge <%= 
                                aula.getTipo().name().equals("LABORATORIO") ? "badge-info" : 
                                aula.getTipo().name().equals("AUDITORIO") ? "badge-warning" :
                                aula.getTipo().name().equals("TALLER") ? "badge-secondary" : "badge-primary" %>">
                                <i class="fas fa-<%= 
                                    aula.getTipo().name().equals("LABORATORIO") ? "laptop" : 
                                    aula.getTipo().name().equals("AUDITORIO") ? "users" :
                                    aula.getTipo().name().equals("TALLER") ? "tools" : "chalkboard" %>"></i>
                                <%= aula.getTipo() %>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estado:</span>
                            <span class="badge <%= 
                                aula.getEstado().name().equals("DISPONIBLE") ? "badge-success" : 
                                aula.getEstado().name().equals("EN_USO") ? "badge-info" :
                                aula.getEstado().name().equals("MANTENIMIENTO") ? "badge-warning" : "badge-secondary" %>">
                                <%= aula.getEstado() %>
                            </span>
                        </div>
                    </div>
                </div>
                
                <!-- Ubicación -->
                <div class="detail-section">
                    <h3><i class="fas fa-map-marker-alt"></i> Ubicación</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Edificio:</span>
                            <span class="detail-value"><%= aula.getEdificio() != null ? aula.getEdificio() : "No especificado" %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Piso:</span>
                            <span class="detail-value"><%= aula.getPiso() != null ? aula.getPiso() : "No especificado" %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Ubicación Completa:</span>
                            <span class="detail-value"><strong><%= aula.getUbicacionCompleta() %></strong></span>
                        </div>
                    </div>
                </div>
                
                <!-- Capacidad y Uso -->
                <div class="detail-section">
                    <h3><i class="fas fa-users"></i> Capacidad y Uso</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Capacidad Máxima:</span>
                            <span class="detail-value">
                                <strong style="font-size: 1.5rem; color: var(--primary);">
                                    <%= aula.getCapacidad() %>
                                </strong> personas
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Cursos Asignados:</span>
                            <span class="detail-value">
                                <strong style="font-size: 1.5rem; color: <%= cursosAsignados > 0 ? "var(--success)" : "var(--secondary)" %>;">
                                    <%= cursosAsignados != null ? cursosAsignados : 0 %>
                                </strong> cursos
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Disponibilidad:</span>
                            <span class="detail-value">
                                <% if (aula.estaDisponible()) { %>
                                    <i class="fas fa-check-circle" style="color: var(--success); font-size: 1.5rem;"></i>
                                    <strong style="color: var(--success);">Disponible</strong>
                                <% } else { %>
                                    <i class="fas fa-times-circle" style="color: var(--danger); font-size: 1.5rem;"></i>
                                    <strong style="color: var(--danger);"><%= aula.getEstado() %></strong>
                                <% } %>
                            </span>
                        </div>
                    </div>
                </div>
                
                <!-- Equipamiento -->
                <div class="detail-section">
                    <h3><i class="fas fa-tools"></i> Equipamiento</h3>
                    <div class="detail-grid">
                        <div class="detail-item full-width">
                            <span class="detail-label">Descripción del Equipamiento:</span>
                            <span class="detail-value">
                                <%= aula.getEquipamiento() != null ? aula.getEquipamiento() : "No especificado" %>
                            </span>
                        </div>
                    </div>
                </div>
                
                <!-- Acciones -->
                <div class="detail-actions">
                    <a href="aulas?accion=ver&id=<%= aula.getId() %>" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Editar Información
                    </a>
                    <button onclick="confirmarEliminacion(<%= aula.getId() %>, '<%= aula.getNombre().replace("'", "\\'") %>')" 
                            class="btn btn-danger">
                        <i class="fas fa-trash"></i> Eliminar Aula
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
                    ¿Está seguro de que desea eliminar esta aula?
                </p>
                <div class="modal-detail">
                    <strong>Aula:</strong> <span id="modalAulaNombre"></span><br>
                    <strong>Código:</strong> <span id="modalAulaCodigo"><%= aula.getCodigo() %></span>
                </div>
                <p class="modal-message" style="margin-top: 1rem; color: var(--danger); font-weight: 600;">
                    <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                </p>
            </div>
            <div class="modal-footer">
                <button onclick="cerrarModal()" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </button>
                <button onclick="eliminarAula()" class="btn btn-danger">
                    <i class="fas fa-trash"></i> Sí, Eliminar
                </button>
            </div>
        </div>
    </div>
    
    <script>
    let aulaIdEliminar = null;
    
    function confirmarEliminacion(id, nombre) {
        aulaIdEliminar = id;
        document.getElementById('modalAulaNombre').textContent = nombre;
        document.getElementById('modalConfirmacion').classList.add('active');
    }
    
    function cerrarModal() {
        document.getElementById('modalConfirmacion').classList.remove('active');
        aulaIdEliminar = null;
    }
    
    function eliminarAula() {
        if (aulaIdEliminar) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'aulas';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = aulaIdEliminar;
            
            form.appendChild(inputAccion);
            form.appendChild(inputId);
            document.body.appendChild(form);
            form.submit();
        }
    }
    
    // Cerrar modal al hacer click fuera de él
    document.getElementById('modalConfirmacion').addEventListener('click', function(e) {
        if (e.target === this) {
            cerrarModal();
        }
    });
    
    // Cerrar modal con tecla ESC
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            cerrarModal();
        }
    });
    </script>
</body>
</html>

<style>
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

.detail-item.full-width {
    grid-column: 1 / -1;
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


