<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Inscripcion" %>
<%@ page import="com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl.InscripcionDetalle" %>
<%@ page import="com.educacionit.sistemaeducativo.enumerados.EstadoInscripcion" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

Inscripcion inscripcion = (Inscripcion) request.getAttribute("inscripcion");
InscripcionDetalle detalle = (InscripcionDetalle) request.getAttribute("detalle");
String returnUrl = (String) request.getAttribute("returnUrl");

if (inscripcion == null || detalle == null) {
    response.sendRedirect("inscripciones?accion=listar");
    return;
}

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle Inscripción - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="page-header">
                <h2><i class="fas fa-clipboard-list"></i> Detalle de Inscripción</h2>
                <a href="<%= returnUrl != null ? returnUrl : "inscripciones?accion=listar" %>" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Volver
                </a>
            </div>
            
            <div class="detail-container">
                <!-- Información del Estudiante -->
                <div class="detail-section">
                    <h3><i class="fas fa-user-graduate"></i> Estudiante</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Nombre Completo:</span>
                            <span class="detail-value"><strong><%= detalle.estudianteNombre %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Matrícula:</span>
                            <span class="detail-value"><%= detalle.matricula %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Email:</span>
                            <span class="detail-value"><a href="mailto:<%= detalle.estudianteEmail %>"><%= detalle.estudianteEmail %></a></span>
                        </div>
                    </div>
                </div>
                
                <!-- Información del Curso -->
                <div class="detail-section">
                    <h3><i class="fas fa-book"></i> Curso</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Código del Curso:</span>
                            <span class="detail-value"><strong><%= detalle.codigoCurso %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Nombre del Curso:</span>
                            <span class="detail-value"><%= detalle.cursoNombre %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Créditos:</span>
                            <span class="detail-value"><%= detalle.creditos %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Aula:</span>
                            <span class="detail-value"><%= detalle.aula != null ? detalle.aula : "No asignada" %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Horario:</span>
                            <span class="detail-value"><%= detalle.horario != null ? detalle.horario : "No asignado" %></span>
                        </div>
                    </div>
                </div>
                
                <!-- Información de la Inscripción -->
                <div class="detail-section">
                    <h3><i class="fas fa-info-circle"></i> Información de la Inscripción</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">ID de Inscripción:</span>
                            <span class="detail-value"><strong>#<%= inscripcion.getId() %></strong></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Fecha de Inscripción:</span>
                            <span class="detail-value"><%= inscripcion.getFechaInscripcion().format(formatter) %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Estado Actual:</span>
                            <span class="badge <%= 
                                inscripcion.getEstado().toString().equals("APROBADO") ? "badge-success" : 
                                inscripcion.getEstado().toString().equals("REPROBADO") ? "badge-danger" : 
                                inscripcion.getEstado().toString().equals("CURSANDO") ? "badge-info" : "badge-secondary" %>">
                                <%= inscripcion.getEstado() %>
                            </span>
                        </div>
                    </div>
                </div>
                
                <!-- Calificaciones y Asistencia -->
                <div class="detail-section">
                    <h3><i class="fas fa-chart-line"></i> Rendimiento Académico</h3>
                    <div class="detail-grid">
                        <div class="detail-item">
                            <span class="detail-label">Nota Final:</span>
                            <% if (inscripcion.getNotaFinal() != null && inscripcion.getNotaFinal() > 0) { %>
                                <span class="badge <%= inscripcion.getNotaFinal() >= 7 ? "badge-success" : "badge-warning" %>">
                                    <%= String.format("%.2f", inscripcion.getNotaFinal()) %>
                                </span>
                            <% } else { %>
                                <span class="badge badge-secondary">Pendiente</span>
                            <% } %>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Asistencia:</span>
                            <% if (inscripcion.getAsistenciaPorcentaje() != null && inscripcion.getAsistenciaPorcentaje() > 0) { %>
                                <span class="detail-value">
                                    <div class="progress-bar">
                                        <div class="progress-fill" style="width: <%= inscripcion.getAsistenciaPorcentaje() %>%"></div>
                                    </div>
                                    <strong><%= String.format("%.1f%%", inscripcion.getAsistenciaPorcentaje()) %></strong>
                                </span>
                            <% } else { %>
                                <span class="badge badge-secondary">No registrada</span>
                            <% } %>
                        </div>
                    </div>
                </div>
                
                <!-- Formulario de Actualización -->
                <div class="detail-section">
                    <h3><i class="fas fa-edit"></i> Actualizar Información</h3>
                    <form action="inscripciones" method="post" onsubmit="return validarActualizacionInscripcion(this)">
                        <input type="hidden" name="accion" value="actualizar">
                        <input type="hidden" name="id" value="<%= inscripcion.getId() %>">
                        <% if (returnUrl != null) { %>
                            <input type="hidden" name="returnUrl" value="<%= returnUrl %>">
                        <% } %>
                        
                        <div class="form-grid">
                            <div class="form-group">
                                <label for="estado">Estado *</label>
                                <select id="estado" name="estado" required>
                                    <% for (EstadoInscripcion estado : EstadoInscripcion.values()) { %>
                                        <option value="<%= estado %>" <%= estado == inscripcion.getEstado() ? "selected" : "" %>>
                                            <%= estado %>
                                        </option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div class="form-group">
                                <label for="notaFinal">Nota Final (0-10)</label>
                                <input type="number" id="notaFinal" name="notaFinal" 
                                       min="0" max="10" step="0.01"
                                       value="<%= inscripcion.getNotaFinal() != null ? inscripcion.getNotaFinal() : "" %>"
                                       placeholder="Ej: 8.50">
                            </div>
                        </div>
                        
                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i> Guardar Cambios
                            </button>
                            <button type="button" onclick="confirmarEliminacion(<%= inscripcion.getId() %>, '<%= returnUrl != null ? returnUrl : "" %>')" 
                                    class="btn btn-danger">
                                <i class="fas fa-trash"></i> Dar de Baja
                            </button>
                        </div>
                    </form>
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
                    <h3 class="modal-title">Confirmar Dar de Baja</h3>
                </div>
            </div>
            <div class="modal-body">
                <p class="modal-message">
                    ¿Está seguro de que desea dar de baja esta inscripción?
                </p>
                <div class="modal-detail">
                    <strong>Estudiante:</strong> <%= detalle.estudianteNombre %><br>
                    <strong>Curso:</strong> <%= detalle.cursoNombre %><br>
                    <strong>Estado actual:</strong> <span class="badge badge-info"><%= inscripcion.getEstado() %></span>
                </div>
                <p class="modal-message" style="margin-top: 1rem; color: var(--danger); font-weight: 600;">
                    <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                </p>
            </div>
            <div class="modal-footer">
                <button onclick="cerrarModal()" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancelar
                </button>
                <button onclick="eliminarInscripcion()" class="btn btn-danger">
                    <i class="fas fa-trash"></i> Sí, Dar de Baja
                </button>
            </div>
        </div>
    </div>
    
    <script>
    let inscripcionIdEliminar = null;
    let inscripcionReturnUrl = null;
    
    function validarActualizacionInscripcion(form) {
        const estado = form.estado.value;
        const nota = parseFloat(form.notaFinal.value) || 0;
        
        // Validar estado APROBADO
        if (estado === 'APROBADO') {
            if (nota < 7) {
                alert('⚠️ Para aprobar, la nota debe ser al menos 7.0\n\n' +
                      'Nota actual: ' + nota.toFixed(2) + '\n' +
                      'Nota mínima: 7.00');
                form.notaFinal.focus();
                return false;
            }
        }
        
        // Validar estado REPROBADO con nota aprobatoria
        if (estado === 'REPROBADO') {
            if (nota >= 7) {
                return confirm('⚠️ La nota (' + nota.toFixed(2) + ') es aprobatoria.\n\n' +
                              '¿Está seguro que desea marcar como REPROBADO?');
            }
        }
        
        // Validar que tenga nota si es APROBADO o REPROBADO
        if ((estado === 'APROBADO' || estado === 'REPROBADO') && nota === 0) {
            alert('⚠️ Debe ingresar una nota para cambiar el estado a ' + estado);
            form.notaFinal.focus();
            return false;
        }
        
        return true;
    }
    
    function confirmarEliminacion(id, returnUrl) {
        inscripcionIdEliminar = id;
        inscripcionReturnUrl = returnUrl;
        document.getElementById('modalConfirmacion').classList.add('active');
    }
    
    function cerrarModal() {
        document.getElementById('modalConfirmacion').classList.remove('active');
        inscripcionIdEliminar = null;
        inscripcionReturnUrl = null;
    }
    
    function eliminarInscripcion() {
        if (inscripcionIdEliminar) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'inscripciones';
            
            var inputAccion = document.createElement('input');
            inputAccion.type = 'hidden';
            inputAccion.name = 'accion';
            inputAccion.value = 'eliminar';
            
            var inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'id';
            inputId.value = inscripcionIdEliminar;
            
            form.appendChild(inputAccion);
            form.appendChild(inputId);
            
            // Agregar returnUrl si existe
            if (inscripcionReturnUrl && inscripcionReturnUrl !== '') {
                var inputReturn = document.createElement('input');
                inputReturn.type = 'hidden';
                inputReturn.name = 'returnUrl';
                inputReturn.value = inscripcionReturnUrl;
                form.appendChild(inputReturn);
            }
            
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

