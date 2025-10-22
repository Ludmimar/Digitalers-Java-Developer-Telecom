<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Estudiante" %>
<%@ page import="com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl.CursoOfrecidoDetalle" %>
<%@ page import="java.util.List" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

@SuppressWarnings("unchecked")
List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
@SuppressWarnings("unchecked")
List<CursoOfrecidoDetalle> cursosOfrecidos = (List<CursoOfrecidoDetalle>) request.getAttribute("cursosOfrecidos");
String error = request.getParameter("error");
String periodoNombre = request.getParameter("periodo");
String periodoEstado = request.getParameter("estado");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Inscripción - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-user-plus"></i> Registrar Nueva Inscripción</h2>
            
            <% if (error != null) { %>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-circle"></i>
                    <% if (error.equals("duplicado")) { %>
                        El estudiante ya está inscrito en este curso
                    <% } else if (error.equals("cupo_lleno")) { %>
                        El curso no tiene cupos disponibles
                    <% } else if (error.equals("fuera_de_plazo")) { %>
                        <div>
                            <strong>Fuera del período de inscripción</strong><br>
                            <% if (periodoNombre != null) { %>
                                Período: <strong><%= periodoNombre %></strong><br>
                            <% } %>
                            <% if (periodoEstado != null) { %>
                                Estado: <strong><%= periodoEstado %></strong><br>
                            <% } %>
                            El período actual no está aceptando inscripciones. 
                            Contacte al administrador para activar el período de inscripción.
                        </div>
                    <% } else if (error.equals("falta_correlativa")) { %>
                        <div>
                            <strong>No cumple con las correlatividades requeridas</strong><br>
                            Cursos faltantes: <strong><%= request.getParameter("cursos") %></strong><br>
                            El estudiante debe aprobar estos cursos antes de inscribirse.
                        </div>
                    <% } else if (error.equals("excede_creditos")) { %>
                        <div>
                            <strong>Excede el límite de créditos por período</strong><br>
                            Créditos actuales: <strong><%= request.getParameter("actual") %></strong><br>
                            Créditos del curso: <strong><%= request.getParameter("nuevo") %></strong><br>
                            Límite permitido: <strong><%= request.getParameter("limite") %></strong><br>
                            No puede inscribirse a este curso porque excedería el límite de créditos permitidos por período.
                        </div>
                    <% } else { %>
                        Error al procesar la inscripción
                    <% } %>
                </div>
            <% } %>
            
            <div class="form-container">
                <form action="inscripciones" method="post" class="student-form">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="estudianteId"><i class="fas fa-user-graduate"></i> Estudiante *</label>
                            <select id="estudianteId" name="estudianteId" required onchange="mostrarInfoEstudiante(this)">
                                <option value="">Seleccione un estudiante...</option>
                                <% if (estudiantes != null) {
                                    for (Estudiante est : estudiantes) {
                                        if (est.getEstadoAcademico().toString().equals("ACTIVO")) { %>
                                            <option value="<%= est.getId() %>" 
                                                    data-matricula="<%= est.getMatricula() %>"
                                                    data-nombre="<%= est.getNombreCompleto() %>">
                                                <%= est.getMatricula() %> - <%= est.getNombreCompleto() %>
                                            </option>
                                        <% }
                                    }
                                } %>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="cursoOfrecidoId"><i class="fas fa-book"></i> Curso Ofrecido *</label>
                            <select id="cursoOfrecidoId" name="cursoOfrecidoId" required onchange="mostrarInfoCurso(this)">
                                <option value="">Seleccione un curso...</option>
                                <% if (cursosOfrecidos != null) {
                                    for (CursoOfrecidoDetalle co : cursosOfrecidos) { %>
                                        <option value="<%= co.id %>"
                                                data-codigo="<%= co.codigoCurso %>"
                                                data-nombre="<%= co.cursoNombre %>"
                                                data-creditos="<%= co.creditos %>"
                                                data-aula="<%= co.aula %>"
                                                data-horario="<%= co.horario %>"
                                                data-profesor="<%= co.profesorNombre %>">
                                            <%= co.codigoCurso %> - <%= co.cursoNombre %> (Aula: <%= co.aula %>)
                                        </option>
                                    <% }
                                } %>
                            </select>
                        </div>
                    </div>
                    
                    <!-- Info cards -->
                    <div class="info-cards" id="infoCards" style="display: none;">
                        <div class="info-card">
                            <h4><i class="fas fa-user-graduate"></i> Estudiante Seleccionado</h4>
                            <p><strong>Matrícula:</strong> <span id="infoMatricula">-</span></p>
                            <p><strong>Nombre:</strong> <span id="infoNombre">-</span></p>
                        </div>
                        <div class="info-card">
                            <h4><i class="fas fa-book"></i> Curso Seleccionado</h4>
                            <p><strong>Código:</strong> <span id="infoCodigo">-</span></p>
                            <p><strong>Nombre:</strong> <span id="infoCursoNombre">-</span></p>
                            <p><strong>Créditos:</strong> <span id="infoCreditos">-</span></p>
                            <p><strong>Aula:</strong> <span id="infoAula">-</span></p>
                            <p><strong>Horario:</strong> <span id="infoHorario">-</span></p>
                            <p><strong>Profesor:</strong> <span id="infoProfesor">-</span></p>
                        </div>
                    </div>
                    
                    <div class="alert alert-info">
                        <i class="fas fa-info-circle"></i> <strong>Nota:</strong> La inscripción se creará con estado "INSCRITO". 
                        Las calificaciones y asistencia se pueden actualizar posteriormente.
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar Inscripción
                        </button>
                        <a href="inscripciones?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    function mostrarInfoEstudiante(select) {
        var option = select.options[select.selectedIndex];
        if (option.value) {
            document.getElementById('infoMatricula').textContent = option.dataset.matricula;
            document.getElementById('infoNombre').textContent = option.dataset.nombre;
            mostrarInfoCards();
        }
    }
    
    function mostrarInfoCurso(select) {
        var option = select.options[select.selectedIndex];
        if (option.value) {
            document.getElementById('infoCodigo').textContent = option.dataset.codigo;
            document.getElementById('infoCursoNombre').textContent = option.dataset.nombre;
            document.getElementById('infoCreditos').textContent = option.dataset.creditos;
            document.getElementById('infoAula').textContent = option.dataset.aula || 'N/A';
            document.getElementById('infoHorario').textContent = option.dataset.horario || 'N/A';
            document.getElementById('infoProfesor').textContent = option.dataset.profesor || 'N/A';
            mostrarInfoCards();
        }
    }
    
    function mostrarInfoCards() {
        var estudiante = document.getElementById('estudianteId').value;
        var curso = document.getElementById('cursoOfrecidoId').value;
        
        if (estudiante && curso) {
            document.getElementById('infoCards').style.display = 'grid';
        }
    }
    </script>
</body>
</html>

<style>
.info-cards {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 1.5rem;
    margin: 2rem 0;
}

.info-card {
    background: linear-gradient(135deg, #f8fafc 0%, #e5e7eb 100%);
    padding: 1.5rem;
    border-radius: var(--border-radius-lg);
    border-left: 4px solid var(--primary);
}

.info-card h4 {
    color: var(--primary);
    margin-bottom: 1rem;
    font-size: 1.1rem;
}

.info-card p {
    margin: 0.5rem 0;
    color: var(--text-primary);
}

.info-card strong {
    color: var(--text-secondary);
    font-size: 0.9rem;
}

.info-card span {
    color: var(--text-primary);
    font-weight: 600;
}
</style>

