<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Estudiante" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.time.LocalDate" %>
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

// Calcular edad
int edad = Period.between(estudiante.getFechaNacimiento(), LocalDate.now()).getYears();

// Verificar errores
String errorTipo = request.getParameter("error");
String cantidadInscripciones = request.getParameter("cantidad");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Estudiante - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2>👤 Detalle del Estudiante</h2>
                <div>
                    <a href="estudiantes?accion=ver&id=<%= estudiante.getId() %>" 
                       class="btn btn-secondary">✏️ Editar</a>
                    <a href="estudiantes?accion=listar" class="btn btn-secondary">← Volver</a>
                </div>
            </div>
            
            <!-- Mensaje de Error -->
            <% if ("tiene_inscripciones".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                <div>
                    <strong>No se puede eliminar este estudiante</strong><br>
                    Este estudiante tiene <strong><%= cantidadInscripciones %></strong> inscripción/inscripciones registrada(s). 
                    Debe eliminar todas las inscripciones antes de eliminar al estudiante.
                </div>
            </div>
            <% } %>
            
            <div class="detail-container">
                <!-- Información Personal -->
                <div class="detail-section">
                    <h3>📋 Información Personal</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Nombre Completo:</span>
                            <span class="detail-value"><%= estudiante.getNombreCompleto() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Tipo de Documento:</span>
                            <span class="detail-value"><%= estudiante.getTipoDocumento() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Número de Documento:</span>
                            <span class="detail-value"><%= estudiante.getNumeroDocumento() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Nacimiento:</span>
                            <span class="detail-value"><%= estudiante.getFechaNacimiento() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Edad:</span>
                            <span class="detail-value"><%= edad %> años</span>
                        </div>
                    </div>
                </div>
                
                <!-- Información de Contacto -->
                <div class="detail-section">
                    <h3>📞 Información de Contacto</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Email:</span>
                            <span class="detail-value">
                                <a href="mailto:<%= estudiante.getEmail() %>"><%= estudiante.getEmail() %></a>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Teléfono:</span>
                            <span class="detail-value"><%= estudiante.getTelefono() != null ? estudiante.getTelefono() : "No registrado" %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Dirección:</span>
                            <span class="detail-value"><%= estudiante.getDireccion() != null ? estudiante.getDireccion() : "No registrada" %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Información Académica -->
                <div class="detail-section">
                    <h3>🎓 Información Académica</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Matrícula:</span>
                            <span class="detail-value"><strong><%= estudiante.getMatricula() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Ingreso:</span>
                            <span class="detail-value"><%= estudiante.getFechaIngreso() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estado Académico:</span>
                            <span class="badge <%= estudiante.getEstadoAcademico().toString().equals("ACTIVO") ? "badge-success" : "badge-warning" %>">
                                <%= estudiante.getEstadoAcademico() %>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Promedio General:</span>
                            <span class="badge <%= estudiante.getPromedioGeneral() >= 7 ? "badge-success" : "badge-warning" %>">
                                <%= String.format("%.2f", estudiante.getPromedioGeneral()) %>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Créditos Cursados:</span>
                            <span class="detail-value"><%= estudiante.getCreditosCursados() != null ? estudiante.getCreditosCursados() : 0 %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Acciones -->
                <div class="detail-actions">
                    <a href="inscripciones?accion=listarPorEstudiante&estudianteId=<%= estudiante.getId() %>" 
                       class="btn btn-info">
                        <i class="fas fa-clipboard-list"></i> Ver Inscripciones
                    </a>
                    <a href="estudiantes?accion=ver&id=<%= estudiante.getId() %>" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Editar Información
                    </a>
                    <button onclick="confirmarEliminacion(<%= estudiante.getId() %>, '<%= estudiante.getNombreCompleto().replace("'", "\\'") %>')" 
                            class="btn btn-danger">
                        <i class="fas fa-trash"></i> Eliminar Estudiante
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
                    ¿Está seguro de que desea eliminar este estudiante?
                </p>
                <div class="modal-detail">
                    <strong>Estudiante:</strong> <span id="modalEstudianteNombre"></span><br>
                    <strong>Matrícula:</strong> <span id="modalEstudianteMatricula"><%= estudiante.getMatricula() %></span>
                </div>
                <p class="modal-message" style="margin-top: 1rem; color: var(--danger); font-weight: 600;">
                    <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                </p>
            </div>
            <div class="modal-footer">
                <button onclick="cerrarModal()" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </button>
                <button onclick="eliminarEstudiante()" class="btn btn-danger">
                    <i class="fas fa-trash"></i> Sí, Eliminar
                </button>
            </div>
        </div>
    </div>
    
    <script>
    let estudianteIdEliminar = null;
    
    function confirmarEliminacion(id, nombre) {
        estudianteIdEliminar = id;
        document.getElementById('modalEstudianteNombre').textContent = nombre;
        document.getElementById('modalConfirmacion').classList.add('active');
    }
    
    function cerrarModal() {
        document.getElementById('modalConfirmacion').classList.remove('active');
        estudianteIdEliminar = null;
    }
    
    function eliminarEstudiante() {
        if (estudianteIdEliminar) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'estudiantes';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = estudianteIdEliminar;
            
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

.detail-label {
    font-weight: 600;
    color: #6b7280;
    font-size: 0.9rem;
}

.detail-value {
    font-size: 1.1rem;
    color: var(--dark);
}

.detail-value a {
    color: var(--primary);
    text-decoration: none;
}

.detail-value a:hover {
    text-decoration: underline;
}

.detail-actions {
    padding: 2rem;
    background: var(--light);
    display: flex;
    gap: 1rem;
    justify-content: center;
    flex-wrap: wrap;
}

.btn-info {
    background: var(--info);
    color: var(--white);
}

.btn-info:hover {
    background: #2563eb;
}
</style>


