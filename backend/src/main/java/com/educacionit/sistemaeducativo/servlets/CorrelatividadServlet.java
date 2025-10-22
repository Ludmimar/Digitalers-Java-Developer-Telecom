package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.entidades.Correlatividad;
import com.educacionit.sistemaeducativo.entidades.Correlatividad.TipoCorrelatividad;
import com.educacionit.sistemaeducativo.entidades.Curso;
import com.educacionit.sistemaeducativo.implementaciones.CorrelatividadDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet para gestionar correlatividades entre cursos.
 * 
 * @author Ludmila Martos
 */
public class CorrelatividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CorrelatividadDAOImpl correlatividadDAO;
    private CursoDAOImpl cursoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        correlatividadDAO = new CorrelatividadDAOImpl();
        cursoDAO = new CursoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Validar sesión
        Boolean autenticado = (Boolean) request.getSession().getAttribute("autenticado");
        if (autenticado == null || !autenticado) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String accion = request.getParameter("accion");
        
        try {
            if ("gestionar".equals(accion)) {
                mostrarGestion(request, response);
            } else {
                response.sendRedirect("cursos?accion=listar");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al gestionar correlatividades", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Validar sesión
        Boolean autenticado = (Boolean) request.getSession().getAttribute("autenticado");
        if (autenticado == null || !autenticado) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String accion = request.getParameter("accion");
        
        try {
            if ("agregar".equals(accion)) {
                agregarCorrelatividad(request, response);
            } else if ("eliminar".equals(accion)) {
                eliminarCorrelatividad(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error al procesar correlatividad", e);
        }
    }
    
    /**
     * Muestra la página de gestión de correlatividades para un curso.
     */
    private void mostrarGestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int cursoId = Integer.parseInt(request.getParameter("cursoId"));
        
        Curso curso = cursoDAO.buscarPorID(cursoId);
        List<Correlatividad> correlativas = correlatividadDAO.obtenerCorrelativas(cursoId);
        List<Correlatividad> dependientes = correlatividadDAO.obtenerDependientes(cursoId);
        List<Curso> todosLosCursos = cursoDAO.listar();
        
        request.setAttribute("curso", curso);
        request.setAttribute("correlativas", correlativas);
        request.setAttribute("dependientes", dependientes);
        request.setAttribute("todosLosCursos", todosLosCursos);
        
        request.getRequestDispatcher("gestionar-correlatividades.jsp").forward(request, response);
    }
    
    /**
     * Agrega una correlatividad.
     */
    private void agregarCorrelatividad(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int cursoId = Integer.parseInt(request.getParameter("cursoId"));
        int correlativaId = Integer.parseInt(request.getParameter("correlativaId"));
        String tipo = request.getParameter("tipo");
        
        // Validar que no sea el mismo curso
        if (cursoId == correlativaId) {
            response.sendRedirect("correlatividades?accion=gestionar&cursoId=" + cursoId + 
                "&error=mismo_curso");
            return;
        }
        
        Correlatividad correlatividad = new Correlatividad();
        correlatividad.setCursoId(cursoId);
        correlatividad.setCorrelativaId(correlativaId);
        correlatividad.setTipo(TipoCorrelatividad.valueOf(tipo));
        
        if (correlatividadDAO.insertar(correlatividad)) {
            response.sendRedirect("correlatividades?accion=gestionar&cursoId=" + cursoId + 
                "&success=agregada");
        } else {
            response.sendRedirect("correlatividades?accion=gestionar&cursoId=" + cursoId + 
                "&error=duplicada");
        }
    }
    
    /**
     * Elimina una correlatividad.
     */
    private void eliminarCorrelatividad(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        int cursoId = Integer.parseInt(request.getParameter("cursoId"));
        
        if (correlatividadDAO.eliminar(id)) {
            response.sendRedirect("correlatividades?accion=gestionar&cursoId=" + cursoId + 
                "&success=eliminada");
        } else {
            response.sendRedirect("correlatividades?accion=gestionar&cursoId=" + cursoId + 
                "&error=eliminar");
        }
    }
}


