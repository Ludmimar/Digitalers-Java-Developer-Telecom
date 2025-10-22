<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.educacionit.sistemaeducativo.entidades.PeriodoAcademico" %>
<%@ page import="java.util.Map" %>
<%
// Verificar sesión
Boolean autenticado = (Boolean) session.getAttribute("autenticado");
if (autenticado == null || !autenticado) {
    response.sendRedirect("index.jsp");
    return;
}
String usuario = (String) session.getAttribute("usuario");
PeriodoAcademico periodoActivo = (PeriodoAcademico) request.getAttribute("periodoActivo");

@SuppressWarnings("unchecked")
Map<String, Integer> inscripcionesPorEstado = (Map<String, Integer>) request.getAttribute("inscripcionesPorEstado");

// Valores por defecto si es null
int pendientes = inscripcionesPorEstado != null ? inscripcionesPorEstado.getOrDefault("PENDIENTE", 0) : 0;
int cursando = inscripcionesPorEstado != null ? inscripcionesPorEstado.getOrDefault("CURSANDO", 0) : 0;
int aprobados = inscripcionesPorEstado != null ? inscripcionesPorEstado.getOrDefault("APROBADO", 0) : 0;
int reprobados = inscripcionesPorEstado != null ? inscripcionesPorEstado.getOrDefault("REPROBADO", 0) : 0;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Sistema Educativo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=3.0">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
</head>
<body>
    <%@ include file="WEB-INF/includes/header.jsp" %>
    
    <main class="main-content">
        <div class="container">
            <div class="hero">
                <h2>Bienvenido al Sistema de Gestión Educativa</h2>
                <p class="subtitle">Usuario: <strong><%= usuario %></strong></p>
                <% if (periodoActivo != null) { %>
                <p class="subtitle">
                    <i class="fas fa-calendar-check"></i> Período Activo: 
                    <strong><%= periodoActivo.getNombreCompleto() %></strong>
                    (<%= periodoActivo.getEstado() %>)
                </p>
                <% } %>
            </div>
            
            <!-- Estadísticas -->
            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #3b82f6, #1d4ed8);"><i class="fas fa-user-graduate"></i></div>
                    <div class="stat-number"><%= request.getAttribute("totalEstudiantes") != null ? request.getAttribute("totalEstudiantes") : 0 %></div>
                    <div class="stat-label">Estudiantes</div>
                    <a href="estudiantes?accion=listar" class="stat-link">Ver todos <i class="fas fa-arrow-right"></i></a>
                </div>
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #10b981, #059669);"><i class="fas fa-chalkboard-teacher"></i></div>
                    <div class="stat-number"><%= request.getAttribute("totalProfesores") != null ? request.getAttribute("totalProfesores") : 0 %></div>
                    <div class="stat-label">Profesores</div>
                    <a href="profesores?accion=listar" class="stat-link">Ver todos <i class="fas fa-arrow-right"></i></a>
                </div>
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #f59e0b, #d97706);"><i class="fas fa-book"></i></div>
                    <div class="stat-number"><%= request.getAttribute("totalCursos") != null ? request.getAttribute("totalCursos") : 0 %></div>
                    <div class="stat-label">Cursos</div>
                    <div class="stat-sublabel">
                        <i class="fas fa-check-circle"></i> <%= request.getAttribute("cursosActivos") != null ? request.getAttribute("cursosActivos") : 0 %> activos
                    </div>
                    <a href="cursos?accion=listar" class="stat-link">Ver todos <i class="fas fa-arrow-right"></i></a>
                </div>
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #8b5cf6, #7c3aed);"><i class="fas fa-clipboard-list"></i></div>
                    <div class="stat-number"><%= request.getAttribute("totalInscripciones") != null ? request.getAttribute("totalInscripciones") : 0 %></div>
                    <div class="stat-label">Inscripciones</div>
                    <a href="inscripciones?accion=listar" class="stat-link">Ver todos <i class="fas fa-arrow-right"></i></a>
                </div>
                <div class="stat-card">
                    <div class="stat-icon" style="background: linear-gradient(135deg, #ec4899, #db2777);"><i class="fas fa-door-open"></i></div>
                    <div class="stat-number"><%= request.getAttribute("totalAulas") != null ? request.getAttribute("totalAulas") : 0 %></div>
                    <div class="stat-label">Aulas</div>
                    <a href="aulas?accion=listar" class="stat-link">Ver todas <i class="fas fa-arrow-right"></i></a>
                </div>
            </div>
            
            <!-- Gráfico de Inscripciones -->
            <div class="dashboard-section">
                <h3><i class="fas fa-chart-pie"></i> Distribución de Inscripciones</h3>
                <div class="chart-container">
                    <canvas id="chartInscripciones"></canvas>
                </div>
            </div>
            
            <!-- Acciones Rápidas -->
            <div class="quick-actions">
                <h3><i class="fas fa-bolt"></i> Acciones Rápidas</h3>
                <div class="actions-grid">
                    <a href="estudiantes?accion=listar" class="action-card">
                        <span class="action-icon"><i class="fas fa-users"></i></span>
                        <span>Gestionar Estudiantes</span>
                    </a>
                    <a href="estudiantes?accion=nuevo" class="action-card">
                        <span class="action-icon"><i class="fas fa-user-plus"></i></span>
                        <span>Nuevo Estudiante</span>
                    </a>
                    <a href="profesores?accion=nuevo" class="action-card">
                        <span class="action-icon"><i class="fas fa-user-tie"></i></span>
                        <span>Nuevo Profesor</span>
                    </a>
                    <a href="profesores?accion=listar" class="action-card">
                        <span class="action-icon"><i class="fas fa-chalkboard-teacher"></i></span>
                        <span>Gestionar Profesores</span>
                    </a>
                    <a href="cursos?accion=nuevo" class="action-card">
                        <span class="action-icon"><i class="fas fa-book-medical"></i></span>
                        <span>Nuevo Curso</span>
                    </a>
                    <a href="cursos?accion=listar" class="action-card">
                        <span class="action-icon"><i class="fas fa-book-open"></i></span>
                        <span>Gestionar Cursos</span>
                    </a>
                    <a href="inscripciones?accion=nueva" class="action-card">
                        <span class="action-icon"><i class="fas fa-user-check"></i></span>
                        <span>Nueva Inscripción</span>
                    </a>
                    <a href="inscripciones?accion=listar" class="action-card">
                        <span class="action-icon"><i class="fas fa-list-alt"></i></span>
                        <span>Gestionar Inscripciones</span>
                    </a>
                </div>
            </div>
        </div>
    </main>
    
    <%@ include file="WEB-INF/includes/footer.jsp" %>
    
    <script>
    // Gráfico de Dona: Inscripciones por estado
    const ctx = document.getElementById('chartInscripciones');
    new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Pendiente', 'Cursando', 'Aprobado', 'Reprobado'],
            datasets: [{
                data: [<%= pendientes %>, <%= cursando %>, <%= aprobados %>, <%= reprobados %>],
                backgroundColor: [
                    '#fbbf24', // Amarillo - Pendiente
                    '#3b82f6', // Azul - Cursando
                    '#10b981', // Verde - Aprobado
                    '#ef4444'  // Rojo - Reprobado
                ],
                borderWidth: 2,
                borderColor: '#ffffff'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom',
                    labels: {
                        padding: 20,
                        font: {
                            size: 14,
                            family: "'Inter', sans-serif"
                        }
                    }
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            const label = context.label || '';
                            const value = context.parsed || 0;
                            const total = context.dataset.data.reduce((a, b) => a + b, 0);
                            const percentage = total > 0 ? ((value / total) * 100).toFixed(1) : 0;
                            return label + ': ' + value + ' (' + percentage + '%)';
                        }
                    }
                }
            }
        }
    });
    </script>
</body>
</html>

<style>
.dashboard-section {
    background: white;
    border-radius: 12px;
    padding: 2rem;
    margin-top: 2rem;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.dashboard-section h3 {
    margin-bottom: 1.5rem;
    color: var(--primary);
    font-size: 1.25rem;
}

.chart-container {
    position: relative;
    height: 300px;
    max-width: 500px;
    margin: 0 auto;
}

.stat-sublabel {
    font-size: 0.85rem;
    color: var(--text-secondary);
    margin-top: 0.25rem;
}
</style>

