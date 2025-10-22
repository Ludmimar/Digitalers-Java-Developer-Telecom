package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.entidades.PeriodoAcademico;
import com.educacionit.sistemaeducativo.entidades.PeriodoAcademico.EstadoPeriodo;
import com.educacionit.sistemaeducativo.implementaciones.PeriodoAcademicoDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Servlet para gestionar las operaciones CRUD de Períodos Académicos.
 * 
 * @author Ludmila Martos
 */
public class PeriodoAcademicoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeriodoAcademicoDAOImpl periodoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
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

        String accion = request.getParameter("accion");
        
        try {
            if (accion == null || accion.equals("listar")) {
                listarPeriodos(request, response);
            } else if (accion.equals("nuevo")) {
                mostrarFormularioNuevo(request, response);
            } else if (accion.equals("editar")) {
                mostrarFormularioEditar(request, response);
            } else if (accion.equals("ver")) {
                verDetalle(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("periodos?accion=listar&error=sql");
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
                insertarPeriodo(request, response);
            } else if (accion.equals("actualizar")) {
                actualizarPeriodo(request, response);
            } else if (accion.equals("eliminar")) {
                eliminarPeriodo(request, response);
            } else if (accion.equals("activar")) {
                activarPeriodo(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    private void listarPeriodos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        List<PeriodoAcademico> periodos = periodoDAO.listar();
        PeriodoAcademico periodoActivo = periodoDAO.obtenerPeriodoActivo();

        request.setAttribute("periodos", periodos);
        request.setAttribute("periodoActivo", periodoActivo);
        request.getRequestDispatcher("periodos.jsp").forward(request, response);
    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("nuevo-periodo.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        PeriodoAcademico periodo = periodoDAO.buscarPorID(id);
        
        request.setAttribute("periodo", periodo);
        RequestDispatcher rd = request.getRequestDispatcher("editar-periodo.jsp");
        rd.forward(request, response);
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String vista = request.getParameter("vista");
        
        PeriodoAcademico periodo = periodoDAO.buscarPorID(id);
        
        if (periodo != null) {
            request.setAttribute("periodo", periodo);
            
            if ("detalle".equals(vista)) {
                // Información adicional si se necesita
                request.getRequestDispatcher("detalle-periodo.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("editar-periodo.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("periodos?accion=listar");
        }
    }

    private void insertarPeriodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        PeriodoAcademico periodo = new PeriodoAcademico();
        periodo.setNombre(request.getParameter("nombre"));
        periodo.setAnio(Integer.parseInt(request.getParameter("anio")));
        periodo.setSemestre(request.getParameter("semestre"));
        periodo.setActivo(request.getParameter("activo") != null);
        
        // Fechas
        String fechaInicioInsc = request.getParameter("fechaInicioInscripciones");
        if (fechaInicioInsc != null && !fechaInicioInsc.isEmpty()) {
            periodo.setFechaInicioInscripciones(LocalDate.parse(fechaInicioInsc));
        }
        
        String fechaFinInsc = request.getParameter("fechaFinInscripciones");
        if (fechaFinInsc != null && !fechaFinInsc.isEmpty()) {
            periodo.setFechaFinInscripciones(LocalDate.parse(fechaFinInsc));
        }
        
        String fechaInicioClases = request.getParameter("fechaInicioClases");
        if (fechaInicioClases != null && !fechaInicioClases.isEmpty()) {
            periodo.setFechaInicioClases(LocalDate.parse(fechaInicioClases));
        }
        
        String fechaFinClases = request.getParameter("fechaFinClases");
        if (fechaFinClases != null && !fechaFinClases.isEmpty()) {
            periodo.setFechaFinClases(LocalDate.parse(fechaFinClases));
        }
        
        periodo.setDescripcion(request.getParameter("descripcion"));
        periodo.setEstado(EstadoPeriodo.valueOf(request.getParameter("estado")));

        if (periodoDAO.insertar(periodo)) {
            response.sendRedirect("periodos?accion=listar&success=insert");
        } else {
            response.sendRedirect("periodos?accion=nuevo&error=insert");
        }
    }

    private void actualizarPeriodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        PeriodoAcademico periodo = new PeriodoAcademico();
        periodo.setId(Integer.parseInt(request.getParameter("id")));
        periodo.setNombre(request.getParameter("nombre"));
        periodo.setAnio(Integer.parseInt(request.getParameter("anio")));
        periodo.setSemestre(request.getParameter("semestre"));
        periodo.setActivo(request.getParameter("activo") != null);
        
        // Fechas
        String fechaInicioInsc = request.getParameter("fechaInicioInscripciones");
        if (fechaInicioInsc != null && !fechaInicioInsc.isEmpty()) {
            periodo.setFechaInicioInscripciones(LocalDate.parse(fechaInicioInsc));
        }
        
        String fechaFinInsc = request.getParameter("fechaFinInscripciones");
        if (fechaFinInsc != null && !fechaFinInsc.isEmpty()) {
            periodo.setFechaFinInscripciones(LocalDate.parse(fechaFinInsc));
        }
        
        String fechaInicioClases = request.getParameter("fechaInicioClases");
        if (fechaInicioClases != null && !fechaInicioClases.isEmpty()) {
            periodo.setFechaInicioClases(LocalDate.parse(fechaInicioClases));
        }
        
        String fechaFinClases = request.getParameter("fechaFinClases");
        if (fechaFinClases != null && !fechaFinClases.isEmpty()) {
            periodo.setFechaFinClases(LocalDate.parse(fechaFinClases));
        }
        
        periodo.setDescripcion(request.getParameter("descripcion"));
        periodo.setEstado(EstadoPeriodo.valueOf(request.getParameter("estado")));

        if (periodoDAO.actualizar(periodo)) {
            response.sendRedirect("periodos?accion=listar&success=update");
        } else {
            response.sendRedirect("periodos?accion=ver&id=" + periodo.getId() + "&error=update");
        }
    }

    private void eliminarPeriodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        PeriodoAcademico periodo = periodoDAO.buscarPorID(id);

        if (periodo == null) {
            response.sendRedirect("periodos?accion=listar&error=notfound");
            return;
        }
        
        // Validar si tiene cursos ofrecidos
        if (periodoDAO.tieneCursosOfrecidos(id)) {
            response.sendRedirect("periodos?accion=ver&id=" + id + "&vista=detalle&error=tiene_cursos");
            return;
        }

        if (periodoDAO.eliminar(periodo)) {
            response.sendRedirect("periodos?accion=listar&success=delete");
        } else {
            response.sendRedirect("periodos?accion=listar&error=delete");
        }
    }
    
    private void activarPeriodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (periodoDAO.activarPeriodo(id)) {
            response.sendRedirect("periodos?accion=listar&success=activado");
        } else {
            response.sendRedirect("periodos?accion=listar&error=activar");
        }
    }
}


