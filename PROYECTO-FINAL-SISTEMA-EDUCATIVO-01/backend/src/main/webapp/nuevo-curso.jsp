<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}

String error = request.getParameter("error");
String codigo = request.getParameter("codigo");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Curso - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <h2><i class="fas fa-book-medical"></i> Registrar Nuevo Curso</h2>
            
            <% if (error != null) { %>
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-circle"></i>
                    <% if (error.equals("codigo_duplicado")) { %>
                        <strong>Error:</strong> El código <%= codigo %> ya está registrado en el sistema.
                    <% } else if (error.equals("insert")) { %>
                        <strong>Error:</strong> No se pudo registrar el curso. Por favor, intenta nuevamente.
                    <% } %>
                </div>
            <% } %>
            
            <div class="form-container">
                <form action="cursos" method="post" class="student-form" onsubmit="return validarFormularioCurso(this)">
                    <input type="hidden" name="accion" value="insertar">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="codigoCurso"><i class="fas fa-barcode"></i> Código del Curso (Formato: XXXX-XXX) *</label>
                            <input type="text" id="codigoCurso" name="codigoCurso" required
                                   pattern="[A-Z]{3,4}-[0-9]{2,3}"
                                   title="Formato: XXXX-XXX (ej: PROG-101, WEB-201)"
                                   placeholder="PROG-101">
                        </div>
                        
                        <div class="form-group">
                            <label for="nombre"><i class="fas fa-heading"></i> Nombre del Curso *</label>
                            <input type="text" id="nombre" name="nombre" required
                                   minlength="5" maxlength="150"
                                   placeholder="Programación en Java">
                        </div>
                        
                        <div class="form-group full-width">
                            <label for="descripcion"><i class="fas fa-align-left"></i> Descripción</label>
                            <textarea id="descripcion" name="descripcion" rows="4"
                                      minlength="20" maxlength="500"
                                      placeholder="Descripción detallada del curso (mínimo 20 caracteres)..."></textarea>
                        </div>
                        
                        <div class="form-group">
                            <label for="creditos"><i class="fas fa-graduation-cap"></i> Créditos *</label>
                            <input type="number" id="creditos" name="creditos" required
                                   min="1" max="10" value="3"
                                   title="Entre 1 y 10 créditos">
                        </div>
                        
                        <div class="form-group">
                            <label for="horasSemanales"><i class="fas fa-clock"></i> Horas Semanales *</label>
                            <input type="number" id="horasSemanales" name="horasSemanales" required
                                   min="1" max="20" value="4"
                                   title="Entre 1 y 20 horas semanales">
                        </div>
                        
                        <div class="form-group">
                            <label for="cupoMaximo"><i class="fas fa-users"></i> Cupo Máximo *</label>
                            <input type="number" id="cupoMaximo" name="cupoMaximo" required
                                   min="5" max="100" value="30"
                                   title="Entre 5 y 100 estudiantes">
                        </div>
                        
                        <div class="form-group">
                            <label for="estado">Estado *</label>
                            <select id="estado" name="estado" required>
                                <option value="ACTIVO" selected>Activo</option>
                                <option value="INACTIVO">Inactivo</option>
                                <option value="FINALIZADO">Finalizado</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save"></i> Guardar Curso
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
    function validarFormularioCurso(form) {
        const creditos = parseInt(form.creditos.value);
        const horas = parseInt(form.horasSemanales.value);
        
        // Validar relación lógica: 1 crédito ≈ 3-4 horas semanales
        if (horas < creditos * 2 || horas > creditos * 5) {
            const sugerenciaMin = creditos * 3;
            const sugerenciaMax = creditos * 4;
            alert('⚠️ Las horas semanales no son coherentes con los créditos.\n\n' +
                  'Créditos: ' + creditos + '\n' +
                  'Horas: ' + horas + '\n\n' +
                  'Sugerencia: ' + sugerenciaMin + ' a ' + sugerenciaMax + ' horas semanales.');
            form.horasSemanales.focus();
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

.form-group.full-width {
    grid-column: 1 / -1;
}

.form-group textarea {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #d1d5db;
    border-radius: 6px;
    font-family: inherit;
    resize: vertical;
}

.form-actions {
    display: flex;
    gap: 1rem;
    justify-content: flex-end;
}
</style>

