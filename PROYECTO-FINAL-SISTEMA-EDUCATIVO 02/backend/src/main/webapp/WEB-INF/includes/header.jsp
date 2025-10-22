<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String currentPage = request.getRequestURI();
    String contextPath = request.getContextPath();
    String servletPath = request.getServletPath(); // Obtiene el path del servlet (más preciso)
    
    // Determinar qué página está activa basándose en el servlet
    boolean isDashboard = servletPath.contains("dashboard");
    boolean isEstudiantes = servletPath.contains("/estudiantes");
    boolean isProfesores = servletPath.contains("/profesores");
    boolean isCursos = servletPath.contains("/cursos") && !servletPath.contains("/inscripciones");
    boolean isInscripciones = servletPath.contains("/inscripciones");
    boolean isAulas = servletPath.contains("/aulas");
    boolean isPeriodos = servletPath.contains("/periodos");
%>
<header class="main-header">
    <div class="container header-content">
        <div class="logo">
            <h1><i class="fas fa-graduation-cap"></i> Sistema de Gestión Educativa</h1>
        </div>
        <nav class="nav-menu">
            <a href="dashboard" class="nav-link <%= isDashboard ? "active" : "" %>" data-page="dashboard">
                <i class="fas fa-home"></i> Inicio
            </a>
            <a href="estudiantes?accion=listar" class="nav-link <%= isEstudiantes ? "active" : "" %>" data-page="estudiantes">
                <i class="fas fa-user-graduate"></i> Estudiantes
            </a>
            <a href="profesores?accion=listar" class="nav-link <%= isProfesores ? "active" : "" %>" data-page="profesores">
                <i class="fas fa-chalkboard-teacher"></i> Profesores
            </a>
            <a href="cursos?accion=listar" class="nav-link <%= isCursos ? "active" : "" %>" data-page="cursos">
                <i class="fas fa-book"></i> Cursos
            </a>
            <a href="inscripciones?accion=listar" class="nav-link <%= isInscripciones ? "active" : "" %>" data-page="inscripciones">
                <i class="fas fa-clipboard-list"></i> Inscripciones
            </a>
            <a href="aulas?accion=listar" class="nav-link <%= isAulas ? "active" : "" %>" data-page="aulas">
                <i class="fas fa-door-open"></i> Aulas
            </a>
            <a href="periodos?accion=listar" class="nav-link <%= isPeriodos ? "active" : "" %>" data-page="periodos">
                <i class="fas fa-calendar-alt"></i> Períodos
            </a>
            <a href="logout" class="nav-logout"><i class="fas fa-sign-out-alt"></i> Salir</a>
        </nav>
    </div>
</header>

<script>
// Prevenir recarga si ya estás en la página
document.addEventListener('DOMContentLoaded', function() {
    const navLinks = document.querySelectorAll('.nav-link');
    
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            // Si el link tiene la clase 'active', prevenir navegación
            if (this.classList.contains('active')) {
                e.preventDefault();
                
                // Efecto visual sutil para indicar que ya estás aquí
                this.style.transform = 'scale(0.95)';
                setTimeout(() => {
                    this.style.transform = 'scale(1)';
                }, 150);
                
                // Opcional: mostrar un mensaje discreto
                // console.log('Ya estás en esta página');
            }
        });
    });
});
</script>

