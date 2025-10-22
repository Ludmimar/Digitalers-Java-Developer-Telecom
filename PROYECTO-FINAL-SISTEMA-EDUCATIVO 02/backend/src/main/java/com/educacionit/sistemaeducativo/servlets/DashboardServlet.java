package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.implementaciones.EstudianteDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.ProfesorDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.AulaDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.PeriodoAcademicoDAOImpl;
import com.educacionit.sistemaeducativo.entidades.PeriodoAcademico;
import com.educacionit.sistemaeducativo.enumerados.EstadoInscripcion;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet para el Dashboard con estadísticas dinámicas.
 * 
 * @author Ludmila Martos
 */
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EstudianteDAOImpl estudianteDAO;
    private ProfesorDAOImpl profesorDAO;
    private CursoDAOImpl cursoDAO;
    private InscripcionDAOImpl inscripcionDAO;
    private AulaDAOImpl aulaDAO;
    private PeriodoAcademicoDAOImpl periodoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        estudianteDAO = new EstudianteDAOImpl();
        profesorDAO = new ProfesorDAOImpl();
        cursoDAO = new CursoDAOImpl();
        inscripcionDAO = new InscripcionDAOImpl();
        aulaDAO = new AulaDAOImpl();
        periodoDAO = new PeriodoAcademicoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("autenticado") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            // Contadores principales
            int totalEstudiantes = estudianteDAO.listar().size();
            int totalProfesores = profesorDAO.listar().size();
            int totalCursos = cursoDAO.listar().size();
            int totalInscripciones = inscripcionDAO.contarTotal();
            int totalAulas = aulaDAO.listar().size();
            
            // Período activo
            PeriodoAcademico periodoActivo = periodoDAO.obtenerPeriodoActivo();
            
            // Estadísticas de inscripciones por estado
            Map<String, Integer> inscripcionesPorEstado = new HashMap<String, Integer>();
            inscripcionesPorEstado.put("PENDIENTE", inscripcionDAO.buscarPorEstado("PENDIENTE").size());
            inscripcionesPorEstado.put("CURSANDO", inscripcionDAO.buscarPorEstado("CURSANDO").size());
            inscripcionesPorEstado.put("APROBADO", inscripcionDAO.buscarPorEstado("APROBADO").size());
            inscripcionesPorEstado.put("REPROBADO", inscripcionDAO.buscarPorEstado("REPROBADO").size());
            
            // Cursos activos
            int cursosActivos = cursoDAO.listarPorEstado("ACTIVO").size();
            
            // Pasar al JSP
            request.setAttribute("totalEstudiantes", totalEstudiantes);
            request.setAttribute("totalProfesores", totalProfesores);
            request.setAttribute("totalCursos", totalCursos);
            request.setAttribute("totalInscripciones", totalInscripciones);
            request.setAttribute("totalAulas", totalAulas);
            request.setAttribute("cursosActivos", cursosActivos);
            request.setAttribute("periodoActivo", periodoActivo);
            request.setAttribute("inscripcionesPorEstado", inscripcionesPorEstado);
            
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Error al obtener estadísticas", e);
        }
    }
}



