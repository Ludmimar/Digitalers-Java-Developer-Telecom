<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Profesor" %>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Aula" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

@SuppressWarnings("unchecked")
List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
@SuppressWarnings("unchecked")
List<Profesor> profesores = (List<Profesor>) request.getAttribute("profesores");
@SuppressWarnings("unchecked")
List<Aula> aulas = (List<Aula>) request.getAttribute("aulas");
String cursoIdPreseleccionado = (String) request.getAttribute("cursoIdPreseleccionado");

// Verificar errores
String errorTipo = request.getParameter("error");
String cursoNombre = request.getParameter("cursoNombre");
String aulaExistente = request.getParameter("aula");
String horarioExistente = request.getParameter("horario");
String profesorExistente = request.getParameter("profesor");
String periodoExistente = request.getParameter("periodo");
String cuposError = request.getParameter("cupos");
String capacidadError = request.getParameter("capacidad");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignar Curso al Período - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-calendar-plus"></i> Asignar Curso al Período Actual</h2>
            
            <!-- Mensajes de Error -->
            <% if ("ya_asignado".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-exclamation-triangle"></i>
                <div>
                    <strong>Este curso ya está asignado al período actual</strong><br>
                    <div style="margin-top: 0.5rem; padding-left: 1rem; border-left: 3px solid var(--danger);">
                        <p style="margin: 0.25rem 0;"><strong>Curso:</strong> <%= cursoNombre %></p>
                        <% if (periodoExistente != null) { %>
                        <p style="margin: 0.25rem 0;"><strong>Período:</strong> <%= periodoExistente %></p>
                        <% } %>
                        <% if (profesorExistente != null) { %>
                        <p style="margin: 0.25rem 0;"><strong>Profesor:</strong> <%= profesorExistente %></p>
                        <% } %>
                        <% if (aulaExistente != null) { %>
                        <p style="margin: 0.25rem 0;"><strong>Aula:</strong> <%= aulaExistente %></p>
                        <% } %>
                        <% if (horarioExistente != null) { %>
                        <p style="margin: 0.25rem 0;"><strong>Horario:</strong> <%= horarioExistente %></p>
                        <% } %>
                    </div>
                    <p style="margin-top: 0.75rem; font-style: italic;">
                        💡 Un curso solo puede ser asignado una vez por período académico.
                    </p>
                </div>
            </div>
            <% } else if ("excede_capacidad".equals(errorTipo)) { %>
            <div class="alert alert-error">
                <i class="fas fa-exclamation-triangle"></i>
                <div>
                    <strong>Los cupos del curso exceden la capacidad del aula</strong><br>
                    <div style="margin-top: 0.5rem; padding-left: 1rem; border-left: 3px solid var(--danger);">
                        <p style="margin: 0.25rem 0;"><strong>Cupos solicitados:</strong> <%= cuposError %> estudiantes</p>
                        <p style="margin: 0.25rem 0;"><strong>Capacidad del aula:</strong> <%= capacidadError %> personas</p>
                        <p style="margin: 0.25rem 0;"><strong>Aula:</strong> <%= aulaExistente %></p>
                    </div>
                    <p style="margin-top: 0.75rem; font-style: italic;">
                        💡 Seleccione un aula con mayor capacidad o reduzca los cupos del curso.
                    </p>
                </div>
            </div>
            <% } %>
            
            <div class="alert alert-info">
                <i class="fas fa-info-circle"></i>
                <div>
                    <strong>Importante:</strong> 
                    Para que un curso aparezca disponible para inscripciones, debe estar asignado a un período 
                    con un profesor, aula y horario.
                </div>
            </div>
            
            <div class="form-container">
                <form action="cursos-ofrecidos" method="post" class="student-form">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="cursoId"><i class="fas fa-book"></i> Curso *</label>
                            <select id="cursoId" name="cursoId" required>
                                <option value="">Seleccione un curso...</option>
                                <% if (cursos != null) {
                                    for (Curso curso : cursos) { 
                                        boolean selected = cursoIdPreseleccionado != null && 
                                                         curso.getId().toString().equals(cursoIdPreseleccionado);
                                %>
                                        <option value="<%= curso.getId() %>" <%= selected ? "selected" : "" %>>
                                            <%= curso.getCodigoCurso() %> - <%= curso.getNombre() %>
                                        </option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="profesorId"><i class="fas fa-chalkboard-teacher"></i> Profesor *</label>
                            <select id="profesorId" name="profesorId" required>
                                <option value="">Seleccione un profesor...</option>
                                <% if (profesores != null) {
                                    for (Profesor prof : profesores) { 
                                        if (prof.getEstadoLaboral().equals("ACTIVO")) { %>
                                            <option value="<%= prof.getId() %>">
                                                <%= prof.getCodigoProfesor() %> - <%= prof.getNombreCompleto() %> (Esp: <%= prof.getEspecialidad() %>)
                                            </option>
                                        <% }
                                    }
                                } %>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="aulaId"><i class="fas fa-door-open"></i> Aula *</label>
                            <select id="aulaId" name="aulaId" required onchange="mostrarInfoAula()">
                                <option value="">Seleccione un aula...</option>
                                <% if (aulas != null) {
                                    for (Aula aula : aulas) { %>
                                        <option value="<%= aula.getId() %>" 
                                                data-codigo="<%= aula.getCodigo() %>"
                                                data-nombre="<%= aula.getNombre() %>"
                                                data-capacidad="<%= aula.getCapacidad() %>"
                                                data-tipo="<%= aula.getTipo() %>"
                                                data-edificio="<%= aula.getEdificio() != null ? aula.getEdificio() : "" %>"
                                                data-piso="<%= aula.getPiso() != null ? aula.getPiso() : "" %>">
                                            <%= aula.getCodigo() %> - <%= aula.getNombre() %> (Cap: <%= aula.getCapacidad() %>)
                                        </option>
                                    <% }
                                } %>
                            </select>
                        </div>
                        
                        <!-- Info del Aula Seleccionada -->
                        <div class="form-group" id="infoAulaContainer" style="display: none;">
                            <div class="info-card">
                                <h4><i class="fas fa-info-circle"></i> Información del Aula</h4>
                                <p><strong>Código:</strong> <span id="infoAulaCodigo">-</span></p>
                                <p><strong>Capacidad:</strong> <span id="infoAulaCapacidad">-</span> personas</p>
                                <p><strong>Tipo:</strong> <span id="infoAulaTipo">-</span></p>
                                <p><strong>Ubicación:</strong> <span id="infoAulaUbicacion">-</span></p>
                            </div>
                        </div>
                        
                        <div class="form-group full-width">
                            <label for="horario"><i class="fas fa-clock"></i> Horario *</label>
                            <input type="text" id="horario" name="horario" required 
                                   placeholder="Ej: Lunes y Miércoles 08:00-10:00">
                        </div>
                        
                        <div class="form-group">
                            <label for="cuposDisponibles"><i class="fas fa-users"></i> Cupos Disponibles *</label>
                            <input type="number" id="cuposDisponibles" name="cuposDisponibles" required
                                   min="5" max="300" value="30"
                                   onchange="validarCuposContraCapacidad()">
                            <small id="warningCapacidad" style="color: var(--danger); display: none;">
                                <i class="fas fa-exclamation-triangle"></i> Los cupos exceden la capacidad del aula
                            </small>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Asignar Curso al Período
                        </button>
                        <a href="cursos?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    let capacidadAulaSeleccionada = 0;
    
    function mostrarInfoAula() {
        const select = document.getElementById('aulaId');
        const option = select.options[select.selectedIndex];
        
        if (option.value) {
            document.getElementById('infoAulaContainer').style.display = 'block';
            document.getElementById('infoAulaCodigo').textContent = option.dataset.codigo;
            document.getElementById('infoAulaCapacidad').textContent = option.dataset.capacidad;
            document.getElementById('infoAulaTipo').textContent = option.dataset.tipo;
            
            const ubicacion = option.dataset.edificio + 
                (option.dataset.piso ? ', Piso ' + option.dataset.piso : '');
            document.getElementById('infoAulaUbicacion').textContent = ubicacion || 'No especificada';
            
            capacidadAulaSeleccionada = parseInt(option.dataset.capacidad);
            validarCuposContraCapacidad();
        } else {
            document.getElementById('infoAulaContainer').style.display = 'none';
            capacidadAulaSeleccionada = 0;
        }
    }
    
    function validarCuposContraCapacidad() {
        const cupos = parseInt(document.getElementById('cuposDisponibles').value) || 0;
        const warning = document.getElementById('warningCapacidad');
        
        if (capacidadAulaSeleccionada > 0 && cupos > capacidadAulaSeleccionada) {
            warning.style.display = 'block';
            warning.textContent = '⚠️ Los cupos (' + cupos + ') exceden la capacidad del aula (' + capacidadAulaSeleccionada + ')';
        } else {
            warning.style.display = 'none';
        }
    }
    </script>
</body>
</html>

<style>
.form-group.full-width {
    grid-column: 1 / -1;
}

.info-card {
    background: linear-gradient(135deg, #f0f9ff 0%, #e0f2fe 100%);
    padding: 1rem;
    border-radius: 8px;
    border-left: 4px solid var(--info);
}

.info-card h4 {
    margin: 0 0 0.75rem 0;
    color: var(--primary);
    font-size: 1rem;
}

.info-card p {
    margin: 0.25rem 0;
    font-size: 0.9rem;
}
</style>

