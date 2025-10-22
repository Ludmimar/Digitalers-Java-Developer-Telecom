package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.implementaciones.CursoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl.AsignacionExistente;
import com.educacionit.sistemaeducativo.implementaciones.ProfesorDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.AulaDAOImpl;
import com.educacionit.sistemaeducativo.entidades.Curso;
import com.educacionit.sistemaeducativo.entidades.Aula;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet para gestionar cursos ofrecidos (asignar cursos a períodos).
 * 
 * @author Ludmila Martos
 */
public class CursoOfrecidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CursoDAOImpl cursoDAO;
    private ProfesorDAOImpl profesorDAO;
    private CursoOfrecidoDAOImpl cursoOfrecidoDAO;
    private AulaDAOImpl aulaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDAO = new CursoDAOImpl();
        profesorDAO = new ProfesorDAOImpl();
        cursoOfrecidoDAO = new CursoOfrecidoDAOImpl();
        aulaDAO = new AulaDAOImpl();
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
            // Cargar listas para el formulario
            request.setAttribute("cursos", cursoDAO.listar());
            request.setAttribute("profesores", profesorDAO.listar());
            request.setAttribute("aulas", aulaDAO.listarPorEstado(Aula.EstadoAula.DISPONIBLE));
            
            // Si viene cursoId, pre-seleccionarlo
            String cursoIdParam = request.getParameter("cursoId");
            if (cursoIdParam != null) {
                request.setAttribute("cursoIdPreseleccionado", cursoIdParam);
            }
            
            request.getRequestDispatcher("asignar-curso.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("autenticado") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String accion = request.getParameter("accion");
        
        if ("insertar".equals(accion)) {
            try {
                insertarCursoOfrecido(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error al asignar curso: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Inserta un nuevo curso ofrecido.
     */
    private void insertarCursoOfrecido(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int cursoId = Integer.parseInt(request.getParameter("cursoId"));
        int profesorId = Integer.parseInt(request.getParameter("profesorId"));
        int aulaId = Integer.parseInt(request.getParameter("aulaId"));
        String horario = request.getParameter("horario");
        int cuposDisponibles = request.getParameter("cuposDisponibles") != null 
            ? Integer.parseInt(request.getParameter("cuposDisponibles")) 
            : 30;
        
        // Obtener el aula para validaciones
        Aula aula = aulaDAO.buscarPorID(aulaId);
        if (aula == null) {
            response.sendRedirect("cursos-ofrecidos?error=aula_no_encontrada");
            return;
        }
        
        // VALIDACIÓN: Verificar capacidad del aula
        if (cuposDisponibles > aula.getCapacidad()) {
            response.sendRedirect("cursos-ofrecidos?cursoId=" + cursoId + 
                "&error=excede_capacidad&cupos=" + cuposDisponibles + 
                "&capacidad=" + aula.getCapacidad() + 
                "&aula=" + java.net.URLEncoder.encode(aula.getNombre(), "UTF-8"));
            return;
        }
        
        // Obtener el período activo
        int periodoId = obtenerPeriodoActivo();
        
        // ✅ VALIDACIÓN: Verificar si el curso ya está asignado a este período
        if (cursoOfrecidoDAO.yaEstaAsignado(cursoId, periodoId)) {
            // Obtener detalles de la asignación existente
            AsignacionExistente asignacion = cursoOfrecidoDAO.obtenerAsignacionExistente(cursoId, periodoId);
            
            // Obtener el nombre del curso para el mensaje de error
            Curso curso = cursoDAO.buscarPorID(cursoId);
            String cursoNombre = curso != null ? curso.getNombre() : "Curso";
            
            // Redirigir con error y detalles de la asignación existente
            String redirectUrl = "cursos-ofrecidos?cursoId=" + cursoId + 
                "&error=ya_asignado" +
                "&cursoNombre=" + java.net.URLEncoder.encode(cursoNombre, "UTF-8");
            
            if (asignacion != null) {
                redirectUrl += "&aula=" + java.net.URLEncoder.encode(asignacion.aula, "UTF-8") +
                    "&horario=" + java.net.URLEncoder.encode(asignacion.horario, "UTF-8") +
                    "&profesor=" + java.net.URLEncoder.encode(asignacion.profesorNombre, "UTF-8") +
                    "&periodo=" + java.net.URLEncoder.encode(asignacion.periodoNombre, "UTF-8");
            }
            
            response.sendRedirect(redirectUrl);
            return;
        }
        
        String sql = "INSERT INTO cursos_ofrecidos (curso_id, periodo_id, profesor_id, aula, aula_id, horario, cupos_disponibles) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            ps.setInt(2, periodoId);
            ps.setInt(3, profesorId);
            ps.setString(4, aula.getCodigo());
            ps.setInt(5, aulaId);
            ps.setString(6, horario);
            ps.setInt(7, cuposDisponibles);
            
            if (ps.executeUpdate() > 0) {
                response.sendRedirect("cursos?accion=listar&success=asignado");
            } else {
                response.sendRedirect("cursos-ofrecidos?error=insert");
            }
        }
    }

    /**
     * Obtiene el ID del período académico activo.
     */
    private int obtenerPeriodoActivo() throws SQLException {
        String sql = "SELECT id FROM periodos_academicos WHERE activo = TRUE LIMIT 1";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        
        // Si no hay período activo, retornar 1 (el primero)
        return 1;
    }
}

