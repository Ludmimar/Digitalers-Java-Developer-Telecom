<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Profesor" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.time.LocalDate" %>
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

// Calcular años de servicio
int anosServicio = Period.between(profesor.getFechaContratacion(), LocalDate.now()).getYears();

// Verificar errores
String errorTipo = request.getParameter("error");
String cantidadCursos = request.getParameter("cantidad");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Profesor - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.1">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-user-tie"></i> Detalle del Profesor</h2>
                <div>
                    <a href="profesores?accion=ver&id=<%= profesor.getId() %>" 
                       class="btn btn-secondary"><i class="fas fa-edit"></i> Editar</a>
                    <a href="profesores?accion=listar" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> Volver</a>
                </div>
            </div>
            
            <!-- Mensaje de Error -->
            <% if ("tiene_cursos".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                <div>
                    <strong>No se puede eliminar este profesor</strong><br>
                    Este profesor tiene <strong><%= cantidadCursos %></strong> curso(s) asignado(s). 
                    Debe reasignar o eliminar todos los cursos antes de eliminar al profesor.
                </div>
            </div>
            <% } %>
            
            <div class="detail-container">
                <!-- Información Personal -->
                <div class="detail-section">
                    <h3><i class="fas fa-id-card"></i> Información Personal</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Nombre Completo:</span>
                            <span class="detail-value"><%= profesor.getNombreCompleto() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Tipo de Documento:</span>
                            <span class="detail-value"><%= profesor.getTipoDocumento() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Número de Documento:</span>
                            <span class="detail-value"><%= profesor.getNumeroDocumento() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Nacimiento:</span>
                            <span class="detail-value"><%= profesor.getFechaNacimiento() %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Información de Contacto -->
                <div class="detail-section">
                    <h3><i class="fas fa-address-book"></i> Información de Contacto</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Email:</span>
                            <span class="detail-value">
                                <a href="mailto:<%= profesor.getEmail() %>"><%= profesor.getEmail() %></a>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Teléfono:</span>
                            <span class="detail-value"><%= profesor.getTelefono() != null ? profesor.getTelefono() : "No registrado" %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Dirección:</span>
                            <span class="detail-value"><%= profesor.getDireccion() != null ? profesor.getDireccion() : "No registrada" %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Información Laboral -->
                <div class="detail-section">
                    <h3><i class="fas fa-briefcase"></i> Información Laboral</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Código de Profesor:</span>
                            <span class="detail-value"><strong><%= profesor.getCodigoProfesor() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Contratación:</span>
                            <span class="detail-value"><%= profesor.getFechaContratacion() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Años de Servicio:</span>
                            <span class="detail-value"><%= anosServicio %> años</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Sueldo:</span>
                            <span class="detail-value">$<%= String.format("%,.2f", profesor.getSueldo()) %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Especialidad:</span>
                            <span class="detail-value"><strong><%= profesor.getEspecialidad() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Grado Académico:</span>
                            <span class="detail-value"><%= profesor.getGradoAcademico() != null ? profesor.getGradoAcademico() : "No especificado" %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estado Laboral:</span>
                            <span class="badge <%= profesor.getEstadoLaboral().equals("ACTIVO") ? "badge-success" : "badge-warning" %>">
                                <%= profesor.getEstadoLaboral() %>
                            </span>
                        </div>
                    </div>
                </div>
                
                <!-- Acciones -->
                <div class="detail-actions">
                    <a href="profesores?accion=verCursos&id=<%= profesor.getId() %>" class="btn btn-info">
                        <i class="fas fa-chalkboard-teacher"></i> Ver Cursos Asignados
                    </a>
                    <a href="profesores?accion=ver&id=<%= profesor.getId() %>" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Editar Información
                    </a>
                    <button onclick="confirmarEliminacion(<%= profesor.getId() %>, '<%= profesor.getNombreCompleto() %>')" 
                            class="btn btn-danger">
                        <i class="fas fa-trash-alt"></i> Eliminar Profesor
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
                    ¿Está seguro de que desea eliminar este profesor?
                </p>
                <div class="modal-detail">
                    <strong>Profesor:</strong> <span id="modalProfesorNombre"></span><br>
                    <strong>Código:</strong> <span id="modalProfesorCodigo"><%= profesor.getCodigoProfesor() %></span>
                </div>
                <p class="modal-message" style="margin-top: 1rem; color: var(--danger); font-weight: 600;">
                    <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                </p>
            </div>
            <div class="modal-footer">
                <button onclick="cerrarModal()" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </button>
                <button onclick="eliminarProfesor()" class="btn btn-danger">
                    <i class="fas fa-trash"></i> Sí, Eliminar
                </button>
            </div>
        </div>
    </div>
    
    <script>
    let profesorIdEliminar = null;
    
    function confirmarEliminacion(id, nombre) {
        profesorIdEliminar = id;
        document.getElementById('modalProfesorNombre').textContent = nombre;
        document.getElementById('modalConfirmacion').classList.add('active');
    }
    
    function cerrarModal() {
        document.getElementById('modalConfirmacion').classList.remove('active');
        profesorIdEliminar = null;
    }
    
    function eliminarProfesor() {
        if (profesorIdEliminar) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'profesores';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = profesorIdEliminar;
            
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


