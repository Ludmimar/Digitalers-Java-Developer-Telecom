package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.entidades.Curso;
import com.educacionit.sistemaeducativo.entidades.Correlatividad;
import com.educacionit.sistemaeducativo.implementaciones.CursoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CorrelatividadDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl.InscripcionDetalle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet para gestionar las operaciones CRUD de Cursos.
 * 
 * @author Ludmila Martos
 */
public class CursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CursoDAOImpl cursoDAO;
    private InscripcionDAOImpl inscripcionDAO;
    private CorrelatividadDAOImpl correlatividadDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDAO = new CursoDAOImpl();
        inscripcionDAO = new InscripcionDAOImpl();
        correlatividadDAO = new CorrelatividadDAOImpl();
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

        String accion = request.getParameter("accion");
        
        try {
            if (accion == null || accion.equals("listar")) {
                listarCursos(request, response);
            } else if (accion.equals("nuevo")) {
                mostrarFormularioNuevo(request, response);
            } else if (accion.equals("editar")) {
                mostrarFormularioEditar(request, response);
            } else if (accion.equals("ver")) {
                verDetalle(request, response);
            } else if (accion.equals("verEstudiantes")) {
                verEstudiantesInscritos(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("cursos?accion=listar&error=sql");
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
        
        try {
            if (accion.equals("insertar")) {
                insertarCurso(request, response);
            } else if (accion.equals("actualizar")) {
                actualizarCurso(request, response);
            } else if (accion.equals("eliminar")) {
                eliminarCurso(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    /**
     * Lista todos los cursos o filtra por búsqueda.
     */
    private void listarCursos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        String busqueda = request.getParameter("busqueda");
        String estado = request.getParameter("estado");
        List<Curso> cursos;

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            cursos = cursoDAO.buscarPorNombre(busqueda);
            request.setAttribute("busqueda", busqueda);
        } else if (estado != null && !estado.isEmpty()) {
            cursos = cursoDAO.listarPorEstado(estado);
            request.setAttribute("estadoFiltro", estado);
        } else {
            cursos = cursoDAO.listar();
        }

        request.setAttribute("cursos", cursos);
        request.getRequestDispatcher("cursos.jsp").forward(request, response);
    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("nuevo-curso.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Curso curso = cursoDAO.buscarPorID(id);
        
        request.setAttribute("curso", curso);
        RequestDispatcher rd = request.getRequestDispatcher("editar-curso.jsp");
        rd.forward(request, response);
    }

    /**
     * Muestra el detalle de un curso o prepara para edición.
     */
    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String vista = request.getParameter("vista");
        
        Curso curso = cursoDAO.buscarPorID(id);
        
        if (curso != null) {
            request.setAttribute("curso", curso);
            
            // Si viene con parámetro vista=detalle, va a detalle, sino a editar
            if ("detalle".equals(vista)) {
                // Obtener información adicional
                int inscritos = cursoDAO.contarEstudiantesInscritos(id);
                boolean tieneCupos = cursoDAO.tieneCuposDisponibles(id);
                
                // Obtener correlatividades
                List<Correlatividad> correlativas = correlatividadDAO.obtenerCorrelativas(id);
                List<Correlatividad> dependientes = correlatividadDAO.obtenerDependientes(id);
                
                request.setAttribute("estudiantesInscritos", inscritos);
                request.setAttribute("tieneCupos", tieneCupos);
                request.setAttribute("correlativas", correlativas);
                request.setAttribute("dependientes", dependientes);
                request.getRequestDispatcher("detalle-curso.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("editar-curso.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("cursos?accion=listar");
        }
    }

    /**
     * Inserta un nuevo curso en la base de datos.
     */
    private void insertarCurso(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String codigoCurso = request.getParameter("codigoCurso");
        
        // VALIDACIÓN: Verificar código único
        if (cursoDAO.existeCodigoCurso(codigoCurso, null)) {
            response.sendRedirect("cursos?accion=nuevo&error=codigo_duplicado&codigo=" + codigoCurso);
            return;
        }
        
        Curso curso = new Curso();
        curso.setCodigoCurso(codigoCurso);
        curso.setNombre(request.getParameter("nombre"));
        curso.setDescripcion(request.getParameter("descripcion"));
        curso.setCreditos(Integer.parseInt(request.getParameter("creditos")));
        curso.setHorasSemanales(Integer.parseInt(request.getParameter("horasSemanales")));
        curso.setCupoMaximo(Integer.parseInt(request.getParameter("cupoMaximo")));
        curso.setEstado(request.getParameter("estado"));

        if (cursoDAO.insertar(curso)) {
            response.sendRedirect("cursos?accion=listar&success=insert");
        } else {
            response.sendRedirect("cursos?accion=nuevo&error=insert");
        }
    }

    /**
     * Actualiza un curso existente en la base de datos.
     */
    private void actualizarCurso(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        Curso curso = new Curso();
        curso.setId(Integer.parseInt(request.getParameter("id")));
        curso.setNombre(request.getParameter("nombre"));
        curso.setDescripcion(request.getParameter("descripcion"));
        curso.setCreditos(Integer.parseInt(request.getParameter("creditos")));
        curso.setHorasSemanales(Integer.parseInt(request.getParameter("horasSemanales")));
        curso.setCupoMaximo(Integer.parseInt(request.getParameter("cupoMaximo")));
        curso.setEstado(request.getParameter("estado"));

        if (cursoDAO.actualizar(curso)) {
            response.sendRedirect("cursos?accion=listar&success=update");
        } else {
            response.sendRedirect("cursos?accion=ver&id=" + curso.getId() + "&error=update");
        }
    }

    /**
     * Elimina un curso de la base de datos.
     */
    private void eliminarCurso(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Curso curso = cursoDAO.buscarPorID(id);

        if (curso == null) {
            response.sendRedirect("cursos?accion=listar&error=notfound");
            return;
        }
        
        // VALIDACIÓN: Verificar si tiene períodos asignados
        if (cursoDAO.tienePeriodosAsignados(id)) {
            int cantidadPeriodos = cursoDAO.contarPeriodosAsignados(id);
            response.sendRedirect("cursos?accion=ver&id=" + id + "&vista=detalle&error=tiene_periodos&cantidad=" + cantidadPeriodos);
            return;
        }

        if (cursoDAO.eliminar(curso)) {
            response.sendRedirect("cursos?accion=listar&success=delete");
        } else {
            response.sendRedirect("cursos?accion=listar&error=delete");
        }
    }

    /**
     * Muestra los estudiantes inscritos en un curso.
     */
    private void verEstudiantesInscritos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int cursoId = Integer.parseInt(request.getParameter("id"));
        Curso curso = cursoDAO.buscarPorID(cursoId);
        
        if (curso != null) {
            // Obtener inscripciones del curso
            List<InscripcionDetalle> inscripciones = inscripcionDAO.buscarPorCursoId(cursoId);
            
            request.setAttribute("curso", curso);
            request.setAttribute("inscripciones", inscripciones);
            request.getRequestDispatcher("estudiantes-curso.jsp").forward(request, response);
        } else {
            response.sendRedirect("cursos?accion=listar&error=notfound");
        }
    }
}

