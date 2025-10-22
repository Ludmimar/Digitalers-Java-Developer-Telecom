<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.Curso" %>
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
String error = request.getParameter("error");
String dni = request.getParameter("dni");
String email = request.getParameter("email");
String codigo = request.getParameter("codigo");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Profesor - Sistema Educativo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2>➕ Registrar Nuevo Profesor</h2>
            
            <% if (error != null) { %>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-circle"></i>
                    <% if (error.equals("dni_duplicado")) { %>
                        <strong>Error:</strong> El DNI <%= dni %> ya está registrado en el sistema.
                    <% } else if (error.equals("email_duplicado")) { %>
                        <strong>Error:</strong> El email <%= email %> ya está registrado en el sistema.
                    <% } else if (error.equals("codigo_duplicado")) { %>
                        <strong>Error:</strong> El código de profesor <%= codigo %> ya está registrado en el sistema.
                    <% } else if (error.equals("insert")) { %>
                        <strong>Error:</strong> No se pudo registrar el profesor. Por favor, intenta nuevamente.
                    <% } %>
                </div>
            <% } %>
            
            <div class="form-container">
                <form action="profesores" method="post" class="student-form" onsubmit="return validarFormularioProfesor(this)">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <!-- Información Personal -->
                        <div class="form-group">
                            <label for="tipoDocumento">Tipo de Documento *</label>
                            <select id="tipoDocumento" name="tipoDocumento" required>
                                <option value="">Seleccione...</option>
                                <option value="DNI">DNI</option>
                                <option value="PASAPORTE">Pasaporte</option>
                                <option value="CI">Cédula de Identidad</option>
                                <option value="LE">Libreta de Enrolamiento</option>
                                <option value="LC">Libreta Cívica</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="numeroDocumento">Número de Documento (DNI: 7-8 dígitos) *</label>
                            <input type="text" id="numeroDocumento" name="numeroDocumento" required
                                   minlength="7" maxlength="8"
                                   pattern="[0-9]{7,8}"
                                   title="DNI debe tener 7 u 8 dígitos numéricos"
                                   placeholder="12345678">
                        </div>
                        
                        <div class="form-group">
                            <label for="nombre">Nombre *</label>
                            <input type="text" id="nombre" name="nombre" required
                                   minlength="2" maxlength="50"
                                   pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+"
                                   title="Solo letras y espacios"
                                   placeholder="Juan">
                        </div>
                        
                        <div class="form-group">
                            <label for="apellido">Apellido *</label>
                            <input type="text" id="apellido" name="apellido" required
                                   minlength="2" maxlength="50"
                                   pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+"
                                   title="Solo letras y espacios"
                                   placeholder="Pérez">
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaNacimiento">Fecha de Nacimiento (mín. 21 años) *</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required
                                   max="<%= java.time.LocalDate.now().minusYears(21) %>"
                                   title="Debe tener al menos 21 años">
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" required
                                   maxlength="100"
                                   placeholder="profesor@mail.com">
                        </div>
                        
                        <div class="form-group">
                            <label for="telefono">Teléfono (10 dígitos)</label>
                            <input type="tel" id="telefono" name="telefono"
                                   pattern="[0-9]{10}"
                                   title="Teléfono debe tener 10 dígitos"
                                   placeholder="1155667788">
                        </div>
                        
                        <div class="form-group">
                            <label for="direccion">Dirección</label>
                            <input type="text" id="direccion" name="direccion"
                                   maxlength="200"
                                   placeholder="Av. Corrientes 1234">
                        </div>
                        
                        <!-- Información Laboral -->
                        <div class="form-group">
                            <label for="codigoProfesor">Código de Profesor (Formato: PROF-XXX) *</label>
                            <input type="text" id="codigoProfesor" name="codigoProfesor" required
                                   pattern="PROF-[0-9]{3,}"
                                   title="Formato: PROF-XXX (ejemplo: PROF-001)"
                                   placeholder="PROF-001">
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaContratacion">Fecha de Contratación *</label>
                            <input type="date" id="fechaContratacion" name="fechaContratacion" required
                                   max="<%= java.time.LocalDate.now() %>"
                                   title="La fecha de contratación no puede ser futura">
                        </div>
                        
                        <div class="form-group">
                            <label for="sueldo">Sueldo (mín. $50,000) *</label>
                            <input type="number" id="sueldo" name="sueldo" required
                                   step="0.01" min="50000" max="1000000"
                                   title="Sueldo entre $50,000 y $1,000,000"
                                   placeholder="75000.00">
                        </div>
                        
                        <div class="form-group">
                            <label for="especialidad"><i class="fas fa-star"></i> Especialidad (Curso) *</label>
                            <select id="especialidad" name="especialidad" required>
                                <option value="">Seleccione especialidad...</option>
                                <% if (cursos != null && !cursos.isEmpty()) {
                                    // Mostrar nombre de cada curso como especialidad
                                    for (Curso curso : cursos) { %>
                                        <option value="<%= curso.getNombre() %>"><%= curso.getNombre() %></option>
                                    <% }
                                } else { %>
                                    <option value="Programación">Programación</option>
                                    <option value="Desarrollo Web">Desarrollo Web</option>
                                    <option value="Base de Datos">Base de Datos</option>
                                    <option value="Informática">Informática</option>
                                <% } %>
                            </select>
                            <small style="color: var(--text-secondary); display: block; margin-top: 0.25rem;">
                                <i class="fas fa-info-circle"></i> Seleccione el curso principal que enseñará
                            </small>
                        </div>
                        
                        <div class="form-group">
                            <label for="gradoAcademico">Grado Académico</label>
                            <select id="gradoAcademico" name="gradoAcademico">
                                <option value="">Seleccione...</option>
                                <option value="Licenciado">Licenciado</option>
                                <option value="Magister">Magister</option>
                                <option value="Doctor">Doctor</option>
                                <option value="Profesor">Profesor</option>
                                <option value="Técnico">Técnico Superior</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar Profesor
                        </button>
                        <a href="profesores?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    function validarFormularioProfesor(form) {
        // Validar edad mínima
        const fechaNac = new Date(form.fechaNacimiento.value);
        const hoy = new Date();
        const edad = Math.floor((hoy - fechaNac) / (1000 * 60 * 60 * 24 * 365.25));
        
        if (edad < 21) {
            alert('❌ El profesor debe tener al menos 21 años.\n\nEdad actual: ' + edad + ' años.');
            form.fechaNacimiento.focus();
            return false;
        }
        
        // Validar fecha de contratación no futura
        const fechaContratacion = new Date(form.fechaContratacion.value);
        if (fechaContratacion > hoy) {
            alert('❌ La fecha de contratación no puede ser futura.');
            form.fechaContratacion.focus();
            return false;
        }
        
        // Validar que fecha de contratación sea posterior a nacimiento
        if (fechaContratacion <= fechaNac) {
            alert('❌ La fecha de contratación debe ser posterior a la fecha de nacimiento.');
            form.fechaContratacion.focus();
            return false;
        }
        
        return true;
    }
    </script>
</body>
</html>

<style>
.form-container {
    background: var(--white);
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    margin-top: 1rem;
}

.form-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 1.5rem;
    margin-bottom: 2rem;
}

.form-actions {
    display: flex;
    gap: 1rem;
    justify-content: flex-end;
}
</style>


