<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

String error = request.getParameter("error");
String dni = request.getParameter("dni");
String email = request.getParameter("email");
String matricula = request.getParameter("matricula");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Estudiante - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.2">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2>➕ Registrar Nuevo Estudiante</h2>
            
            <% if (error != null) { %>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-circle"></i>
                    <% if (error.equals("dni_duplicado")) { %>
                        <strong>Error:</strong> El DNI <%= dni %> ya está registrado en el sistema.
                    <% } else if (error.equals("email_duplicado")) { %>
                        <strong>Error:</strong> El email <%= email %> ya está registrado en el sistema.
                    <% } else if (error.equals("matricula_duplicada")) { %>
                        <strong>Error:</strong> La matrícula <%= matricula %> ya está registrada en el sistema.
                    <% } else if (error.equals("insert")) { %>
                        <strong>Error:</strong> No se pudo registrar el estudiante. Por favor, intenta nuevamente.
                    <% } %>
                </div>
            <% } %>
            
            <div class="form-container">
                <form action="estudiantes" method="post" class="student-form" onsubmit="return validarFormularioEstudiante(this)">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <!-- Información Personal -->
                        <div class="form-group">
                            <label for="tipoDocumento">Tipo de Documento *</label>
                            <select id="tipoDocumento" name="tipoDocumento" required>
                                <option value="">Seleccione...</option>
                                <option value="DNI" selected>DNI</option>
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
                            <label for="fechaNacimiento">Fecha de Nacimiento (mín. 16 años) *</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required
                                   max="<%= java.time.LocalDate.now().minusYears(16) %>"
                                   title="Debe tener al menos 16 años">
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" required
                                   maxlength="100"
                                   placeholder="estudiante@mail.com">
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
                        
                        <!-- Información Académica -->
                        <div class="form-group">
                            <label for="matricula">Matrícula/Legajo (Formato: EST-XXX) *</label>
                            <input type="text" id="matricula" name="matricula" required
                                   pattern="EST-[0-9]{3,}"
                                   title="Formato: EST-XXX (ejemplo: EST-001)"
                                   placeholder="EST-001">
                        </div>
                        
                        <div class="form-group">
                            <label for="fechaIngreso">Fecha de Ingreso *</label>
                            <input type="date" id="fechaIngreso" name="fechaIngreso" required
                                   max="<%= java.time.LocalDate.now() %>"
                                   title="La fecha de ingreso no puede ser futura">
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar Estudiante
                        </button>
                        <a href="estudiantes?accion=listar" class="btn btn-secondary">
                            <i class="fas fa-times"></i> Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    function validarFormularioEstudiante(form) {
        // Validar edad mínima
        const fechaNac = new Date(form.fechaNacimiento.value);
        const hoy = new Date();
        const edad = Math.floor((hoy - fechaNac) / (1000 * 60 * 60 * 24 * 365.25));
        
        if (edad < 16) {
            alert('❌ El estudiante debe tener al menos 16 años.\n\nEdad actual: ' + edad + ' años.');
            form.fechaNacimiento.focus();
            return false;
        }
        
        // Validar fecha de ingreso no futura
        const fechaIngreso = new Date(form.fechaIngreso.value);
        if (fechaIngreso > hoy) {
            alert('❌ La fecha de ingreso no puede ser futura.');
            form.fechaIngreso.focus();
            return false;
        }
        
        // Validar que fecha de ingreso sea posterior a fecha de nacimiento
        if (fechaIngreso <= fechaNac) {
            alert('❌ La fecha de ingreso debe ser posterior a la fecha de nacimiento.');
            form.fechaIngreso.focus();
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

