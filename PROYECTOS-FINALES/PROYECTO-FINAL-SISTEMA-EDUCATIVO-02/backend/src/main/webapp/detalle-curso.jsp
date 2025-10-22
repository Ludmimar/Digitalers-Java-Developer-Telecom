<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Correlatividad" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

Curso curso = (Curso) request.getAttribute("curso");
if (curso == null) {
    response.sendRedirect("cursos?accion=listar");
    return;
}

Integer estudiantesInscritos = (Integer) request.getAttribute("estudiantesInscritos");
Boolean tieneCupos = (Boolean) request.getAttribute("tieneCupos");
int cuposDisponibles = curso.getCupoMaximo() - (estudiantesInscritos != null ? estudiantesInscritos : 0);
double porcentajeOcupacion = (estudiantesInscritos != null && curso.getCupoMaximo() > 0) 
    ? (estudiantesInscritos * 100.0 / curso.getCupoMaximo()) : 0;

// Verificar errores
String errorTipo = request.getParameter("error");
String cantidadPeriodos = request.getParameter("cantidad");

// Correlatividades
@SuppressWarnings("unchecked")
List<Correlatividad> correlativas = (List<Correlatividad>) request.getAttribute("correlativas");
@SuppressWarnings("unchecked")
List<Correlatividad> dependientes = (List<Correlatividad>) request.getAttribute("dependientes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Curso - Sistema Educativo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2>📚 Detalle del Curso</h2>
                <div>
                    <a href="cursos?accion=ver&id=<%= curso.getId() %>" 
                       class="btn btn-secondary">✏️ Editar</a>
                    <a href="cursos?accion=listar" class="btn btn-secondary">← Volver</a>
                </div>
            </div>
            
            <!-- Mensaje de Error -->
            <% if ("tiene_periodos".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-times-circle"></i>
                <div>
                    <strong>No se puede eliminar este curso</strong><br>
                    Este curso tiene <strong><%= cantidadPeriodos %></strong> período(s) académico(s) asignado(s). 
                    Debe eliminar o reasignar todos los períodos antes de eliminar el curso.
                </div>
            </div>
            <% } %>
            
            <div class="detail-container">
                <!-- Información General -->
                <div class="detail-section">
                    <h3>📋 Información General</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Código del Curso:</span>
                            <span class="detail-value"><strong><%= curso.getCodigoCurso() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Nombre:</span>
                            <span class="detail-value"><%= curso.getNombre() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estado:</span>
                            <span class="badge <%= curso.getEstado().equals("ACTIVO") ? "badge-success" : 
                                                   curso.getEstado().equals("INACTIVO") ? "badge-warning" : "badge-secondary" %>">
                                <%= curso.getEstado() %>
                            </span>
                        </div>
                        <div class="detail-item full-width">
                            <span class="detail-label">Descripción:</span>
                            <span class="detail-value"><%= curso.getDescripcion() != null ? curso.getDescripcion() : "No especificada" %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Información Académica -->
                <div class="detail-section">
                    <h3>🎓 Información Académica</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Créditos:</span>
                            <span class="detail-value"><strong><%= curso.getCreditos() %></strong> créditos</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Horas Semanales:</span>
                            <span class="detail-value"><strong><%= curso.getHorasSemanales() %></strong> horas</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Duración Total:</span>
                            <span class="detail-value"><%= curso.getHorasSemanales() * 16 %> horas (semestre)</span>
                        </div>
                    </div>
                </div>
                
                <!-- Información de Inscripciones -->
                <div class="detail-section">
                    <h3>👥 Información de Inscripciones</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Cupo Máximo:</span>
                            <span class="detail-value"><strong><%= curso.getCupoMaximo() %></strong> estudiantes</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estudiantes Inscritos:</span>
                            <span class="detail-value"><strong><%= estudiantesInscritos != null ? estudiantesInscritos : 0 %></strong> estudiantes</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Cupos Disponibles:</span>
                            <span class="detail-value">
                                <strong class="<%= tieneCupos ? "text-success" : "text-danger" %>">
                                    <%= cuposDisponibles %>
                                </strong> 
                                <%= tieneCupos ? "✅" : "❌ COMPLETO" %>
                            </span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Porcentaje de Ocupación:</span>
                            <span class="detail-value">
                                <div class="progress-bar">
                                    <div class="progress-fill" style="width: <%= porcentajeOcupacion %>%"></div>
                                </div>
                                <span><%= String.format("%.1f", porcentajeOcupacion) %>%</span>
                            </span>
                        </div>
                    </div>
                </div>
                
                <!-- Correlatividades -->
                <div class="detail-section">
                    <h3>🔗 Correlatividades</h3>
                    
                    <div class="correlativas-container">
                        <div class="correlativas-requeridas">
                            <h4><i class="fas fa-arrow-circle-down"></i> Cursos Requeridos (Prerrequisitos)</h4>
                            <% if (correlativas != null && !correlativas.isEmpty()) { %>
                                <ul class="correlativas-list">
                                    <% for (Correlatividad corr : correlativas) { %>
                                        <li>
                                            <span class="correlativa-nombre"><%= corr.getCorrelativaNombre() %></span>
                                            <span class="correlativa-tipo <%= corr.getTipo().name().equals("APROBADA") ? "tipo-aprobada" : "tipo-regular" %>">
                                                <%= corr.getTipo().name().equals("APROBADA") ? "APROBADA" : "REGULAR" %>
                                            </span>
                                        </li>
                                    <% } %>
                                </ul>
                            <% } else { %>
                                <p class="no-correlativas"><i class="fas fa-check-circle"></i> Este curso no tiene correlatividades previas</p>
                            <% } %>
                        </div>
                        
                        <div class="correlativas-dependientes">
                            <h4><i class="fas fa-arrow-circle-up"></i> Cursos que Requieren este Curso</h4>
                            <% if (dependientes != null && !dependientes.isEmpty()) { %>
                                <ul class="correlativas-list">
                                    <% for (Correlatividad dep : dependientes) { %>
                                        <li>
                                            <span class="correlativa-nombre"><%= dep.getCursoNombre() %></span>
                                        </li>
                                    <% } %>
                                </ul>
                            <% } else { %>
                                <p class="no-correlativas"><i class="fas fa-info-circle"></i> Ningún curso requiere este curso como correlativa</p>
                            <% } %>
                        </div>
                    </div>
                </div>
                
                <!-- Acciones -->
                <div class="detail-actions">
                    <a href="cursos?accion=verEstudiantes&id=<%= curso.getId() %>" class="btn btn-info">
                        <i class="fas fa-users"></i> Ver Estudiantes Inscritos
                    </a>
                    <a href="correlatividades?accion=gestionar&cursoId=<%= curso.getId() %>" class="btn btn-warning">
                        <i class="fas fa-link"></i> Gestionar Correlatividades
                    </a>
                    <a href="cursos-ofrecidos?cursoId=<%= curso.getId() %>" class="btn btn-success">
                        <i class="fas fa-calendar-plus"></i> Asignar al Período
                    </a>
                    <a href="cursos?accion=ver&id=<%= curso.getId() %>" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Editar Información
                    </a>
                    <button onclick="confirmarEliminacion(<%= curso.getId() %>, '<%= curso.getNombre().replace("'", "\\'") %>')" 
                            class="btn btn-danger">
                        <i class="fas fa-trash"></i> Eliminar Curso
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
                    ¿Está seguro de que desea eliminar este curso?
                </p>
                <div class="modal-detail">
                    <strong>Curso:</strong> <span id="modalCursoNombre"></span><br>
                    <strong>Código:</strong> <span id="modalCursoCodigo"></span>
                </div>
                <p class="modal-message" style="margin-top: 1rem; color: var(--danger); font-weight: 600;">
                    <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                </p>
            </div>
            <div class="modal-footer">
                <button onclick="cerrarModal()" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </button>
                <button onclick="eliminarCurso()" class="btn btn-danger">
                    <i class="fas fa-trash"></i> Sí, Eliminar
                </button>
            </div>
        </div>
    </div>
    
    <script>
    let cursoIdEliminar = null;
    
    function confirmarEliminacion(id, nombre) {
        cursoIdEliminar = id;
        document.getElementById('modalCursoNombre').textContent = nombre;
        document.getElementById('modalCursoCodigo').textContent = '<%= curso.getCodigoCurso() %>';
        document.getElementById('modalConfirmacion').classList.add('active');
    }
    
    function cerrarModal() {
        document.getElementById('modalConfirmacion').classList.remove('active');
        cursoIdEliminar = null;
    }
    
    function eliminarCurso() {
        if (cursoIdEliminar) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'cursos';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = cursoIdEliminar;
            
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

.text-success {
    color: var(--success);
}

.text-danger {
    color: var(--danger);
}

.progress-bar {
    width: 100%;
    height: 20px;
    background: #e5e7eb;
    border-radius: 10px;
    overflow: hidden;
    margin: 0.5rem 0;
}

.progress-fill {
    height: 100%;
    background: linear-gradient(90deg, var(--success), var(--primary));
    transition: width 0.3s ease;
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

