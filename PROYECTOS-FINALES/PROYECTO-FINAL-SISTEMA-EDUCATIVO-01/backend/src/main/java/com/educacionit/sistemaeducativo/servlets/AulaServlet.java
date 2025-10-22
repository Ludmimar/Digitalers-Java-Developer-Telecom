package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.entidades.Aula;
import com.educacionit.sistemaeducativo.entidades.Aula.EstadoAula;
import com.educacionit.sistemaeducativo.entidades.Aula.TipoAula;
import com.educacionit.sistemaeducativo.implementaciones.AulaDAOImpl;

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
 * Servlet para gestionar las operaciones CRUD de Aulas.
 * 
 * @author Ludmila Martos
 */
public class AulaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AulaDAOImpl aulaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
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

        String accion = request.getParameter("accion");
        
        try {
            if (accion == null || accion.equals("listar")) {
                listarAulas(request, response);
            } else if (accion.equals("nuevo")) {
                mostrarFormularioNuevo(request, response);
            } else if (accion.equals("editar")) {
                mostrarFormularioEditar(request, response);
            } else if (accion.equals("ver")) {
                verDetalle(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("aulas?accion=listar&error=sql");
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
                insertarAula(request, response);
            } else if (accion.equals("actualizar")) {
                actualizarAula(request, response);
            } else if (accion.equals("eliminar")) {
                eliminarAula(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    private void listarAulas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        String tipoFiltro = request.getParameter("tipo");
        String estadoFiltro = request.getParameter("estado");
        List<Aula> aulas;

        if (tipoFiltro != null && !tipoFiltro.isEmpty()) {
            aulas = aulaDAO.listarPorTipo(TipoAula.valueOf(tipoFiltro));
            request.setAttribute("tipoFiltro", tipoFiltro);
        } else if (estadoFiltro != null && !estadoFiltro.isEmpty()) {
            aulas = aulaDAO.listarPorEstado(EstadoAula.valueOf(estadoFiltro));
            request.setAttribute("estadoFiltro", estadoFiltro);
        } else {
            aulas = aulaDAO.listar();
        }

        request.setAttribute("aulas", aulas);
        request.getRequestDispatcher("aulas.jsp").forward(request, response);
    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("nueva-aula.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Aula aula = aulaDAO.buscarPorID(id);
        
        request.setAttribute("aula", aula);
        RequestDispatcher rd = request.getRequestDispatcher("editar-aula.jsp");
        rd.forward(request, response);
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String vista = request.getParameter("vista");
        
        Aula aula = aulaDAO.buscarPorID(id);
        
        if (aula != null) {
            request.setAttribute("aula", aula);
            
            if ("detalle".equals(vista)) {
                // Obtener información adicional
                int cursosAsignados = aulaDAO.contarCursosAsignados(id);
                request.setAttribute("cursosAsignados", cursosAsignados);
                
                request.getRequestDispatcher("detalle-aula.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("editar-aula.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("aulas?accion=listar");
        }
    }

    private void insertarAula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String codigo = request.getParameter("codigo");
        
        // Validar código único
        if (aulaDAO.existeCodigo(codigo)) {
            response.sendRedirect("aulas?accion=nuevo&error=codigo_duplicado&codigo=" + codigo);
            return;
        }
        
        Aula aula = new Aula();
        aula.setCodigo(codigo);
        aula.setNombre(request.getParameter("nombre"));
        aula.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
        aula.setEdificio(request.getParameter("edificio"));
        
        String pisoStr = request.getParameter("piso");
        if (pisoStr != null && !pisoStr.trim().isEmpty()) {
            aula.setPiso(Integer.parseInt(pisoStr));
        }
        
        aula.setTipo(TipoAula.valueOf(request.getParameter("tipo")));
        aula.setEquipamiento(request.getParameter("equipamiento"));
        aula.setEstado(EstadoAula.valueOf(request.getParameter("estado")));

        if (aulaDAO.insertar(aula)) {
            response.sendRedirect("aulas?accion=listar&success=insert");
        } else {
            response.sendRedirect("aulas?accion=nuevo&error=insert");
        }
    }

    private void actualizarAula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        Aula aula = new Aula();
        aula.setId(Integer.parseInt(request.getParameter("id")));
        aula.setCodigo(request.getParameter("codigo"));
        aula.setNombre(request.getParameter("nombre"));
        aula.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
        aula.setEdificio(request.getParameter("edificio"));
        
        String pisoStr = request.getParameter("piso");
        if (pisoStr != null && !pisoStr.trim().isEmpty()) {
            aula.setPiso(Integer.parseInt(pisoStr));
        }
        
        aula.setTipo(TipoAula.valueOf(request.getParameter("tipo")));
        aula.setEquipamiento(request.getParameter("equipamiento"));
        aula.setEstado(EstadoAula.valueOf(request.getParameter("estado")));

        if (aulaDAO.actualizar(aula)) {
            response.sendRedirect("aulas?accion=listar&success=update");
        } else {
            response.sendRedirect("aulas?accion=ver&id=" + aula.getId() + "&error=update");
        }
    }

    private void eliminarAula(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Aula aula = aulaDAO.buscarPorID(id);

        if (aula == null) {
            response.sendRedirect("aulas?accion=listar&error=notfound");
            return;
        }
        
        // Validar si tiene cursos asignados
        if (aulaDAO.tieneCursosAsignados(id)) {
            int cantidadCursos = aulaDAO.contarCursosAsignados(id);
            response.sendRedirect("aulas?accion=ver&id=" + id + "&vista=detalle&error=tiene_cursos&cantidad=" + cantidadCursos);
            return;
        }

        if (aulaDAO.eliminar(aula)) {
            response.sendRedirect("aulas?accion=listar&success=delete");
        } else {
            response.sendRedirect("aulas?accion=listar&error=delete");
        }
    }
}


