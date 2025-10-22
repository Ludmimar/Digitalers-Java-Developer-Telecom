package com.educacionit.sistemaeducativo.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.educacionit.sistemaeducativo.entidades.Profesor;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;
import com.educacionit.sistemaeducativo.implementaciones.ProfesorDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl.CursoOfrecidoDetalle;
import com.educacionit.sistemaeducativo.implementaciones.CursoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.EstudianteDAOImpl;

/**
 * Servlet para manejar operaciones CRUD de Profesores.
 * Procesa peticiones GET (listar, buscar) y POST (insertar, actualizar, eliminar).
 * 
 * @author Ludmila Martos
 */
public class ProfesorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfesorDAOImpl profesorDAO;
    private CursoOfrecidoDAOImpl cursoOfrecidoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        profesorDAO = new ProfesorDAOImpl();
        cursoOfrecidoDAO = new CursoOfrecidoDAOImpl();
    }

    /**
     * Maneja peticiones GET: listar y buscar profesores
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        try {
            if ("listar".equals(accion) || accion == null) {
                listarProfesores(request, response);
            } else if ("nuevo".equals(accion)) {
                mostrarFormularioNuevo(request, response);
            } else if ("editar".equals(accion)) {
                mostrarFormularioEditar(request, response);
            } else if ("buscar".equals(accion)) {
                buscarProfesor(request, response);
            } else if ("ver".equals(accion)) {
                verDetalle(request, response);
            } else if ("verCursos".equals(accion)) {
                verCursosAsignados(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            response.sendRedirect("profesores?accion=listar&error=sql");
        }
    }

    /**
     * Maneja peticiones POST: insertar, actualizar, eliminar
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        
        try {
            if ("insertar".equals(accion)) {
                insertarProfesor(request, response);
            } else if ("actualizar".equals(accion)) {
                actualizarProfesor(request, response);
            } else if ("eliminar".equals(accion)) {
                eliminarProfesor(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }

    private void listarProfesores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        List<Profesor> profesores = profesorDAO.listar();
        request.setAttribute("profesores", profesores);
        RequestDispatcher rd = request.getRequestDispatcher("profesores.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        // Cargar lista de cursos para el dropdown de especialidad
        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        request.setAttribute("cursos", cursoDAO.listar());
        
        RequestDispatcher rd = request.getRequestDispatcher("nuevo-profesor.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Profesor profesor = profesorDAO.buscarPorID(id);
        
        // Cargar lista de cursos para el dropdown de especialidad
        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        request.setAttribute("cursos", cursoDAO.listar());
        request.setAttribute("profesor", profesor);
        
        RequestDispatcher rd = request.getRequestDispatcher("editar-profesor.jsp");
        rd.forward(request, response);
    }

    private void buscarProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String codigo = request.getParameter("codigo");
        String estado = request.getParameter("estado");
        
        List<Profesor> profesores;
        
        // Prioridad: 1. Estado, 2. Código/Nombre, 3. Listar todos
        if (estado != null && !estado.isEmpty()) {
            // Buscar por estado laboral
            profesores = profesorDAO.buscarPorEstado(estado);
        } else if (codigo != null && !codigo.isEmpty()) {
            // Buscar por código o nombre
            Profesor profesor = profesorDAO.buscarPorCodigo(codigo);
            if (profesor != null) {
                profesores = new ArrayList<>();
                profesores.add(profesor);
            } else {
                // Intentar buscar por nombre si no encontró por código
                profesores = profesorDAO.buscarPorNombre(codigo);
            }
        } else {
            // Listar todos
            profesores = profesorDAO.listar();
        }
        
        request.setAttribute("profesores", profesores);
        RequestDispatcher rd = request.getRequestDispatcher("profesores.jsp");
        rd.forward(request, response);
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Profesor profesor = profesorDAO.buscarPorID(id);
        
        request.setAttribute("profesor", profesor);
        
        // Si viene de la acción "ver" se muestra el formulario de edición
        // Si viene de "detalle" se muestra la vista completa
        String vista = "editar-profesor.jsp";
        if ("detalle".equals(request.getParameter("vista"))) {
            vista = "detalle-profesor.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    private void insertarProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String dni = request.getParameter("numeroDocumento");
        String email = request.getParameter("email");
        String codigoProfesor = request.getParameter("codigoProfesor");
        
        // VALIDACIÓN 1: Verificar DNI único
        EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
        if (estudianteDAO.existeDNI(dni, null)) {
            response.sendRedirect("profesores?accion=nuevo&error=dni_duplicado&dni=" + dni);
            return;
        }
        
        // VALIDACIÓN 2: Verificar email único
        if (estudianteDAO.existeEmail(email, null)) {
            response.sendRedirect("profesores?accion=nuevo&error=email_duplicado&email=" + email);
            return;
        }
        
        // VALIDACIÓN 3: Verificar código de profesor único
        if (profesorDAO.existeCodigoProfesor(codigoProfesor, null)) {
            response.sendRedirect("profesores?accion=nuevo&error=codigo_duplicado&codigo=" + codigoProfesor);
            return;
        }
        
        Profesor profesor = new Profesor();
        
        // Mapear datos del formulario
        profesor.setTipoDocumento(TipoDocumento.valueOf(request.getParameter("tipoDocumento")));
        profesor.setNumeroDocumento(dni);
        profesor.setNombre(request.getParameter("nombre"));
        profesor.setApellido(request.getParameter("apellido"));
        profesor.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
        profesor.setEmail(email);
        profesor.setTelefono(request.getParameter("telefono"));
        profesor.setDireccion(request.getParameter("direccion"));
        profesor.setCodigoProfesor(codigoProfesor);
        profesor.setFechaContratacion(LocalDate.parse(request.getParameter("fechaContratacion")));
        profesor.setSueldo(Double.parseDouble(request.getParameter("sueldo")));
        profesor.setEspecialidad(request.getParameter("especialidad"));
        profesor.setGradoAcademico(request.getParameter("gradoAcademico"));
        profesor.setEstadoLaboral("ACTIVO");
        
        // Insertar en base de datos
        if (profesorDAO.insertar(profesor)) {
            response.sendRedirect("profesores?accion=listar&success=insert");
        } else {
            response.sendRedirect("profesores?accion=nuevo&error=insert");
        }
    }

    private void actualizarProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Profesor profesor = profesorDAO.buscarPorID(id);
        
        if (profesor != null) {
            // Actualizar datos editables
            profesor.setNombre(request.getParameter("nombre"));
            profesor.setApellido(request.getParameter("apellido"));
            profesor.setEmail(request.getParameter("email"));
            profesor.setTelefono(request.getParameter("telefono"));
            profesor.setDireccion(request.getParameter("direccion"));
            profesor.setSueldo(Double.parseDouble(request.getParameter("sueldo")));
            profesor.setEspecialidad(request.getParameter("especialidad"));
            profesor.setGradoAcademico(request.getParameter("gradoAcademico"));
            profesor.setEstadoLaboral(request.getParameter("estadoLaboral"));
            
            if (profesorDAO.actualizar(profesor)) {
                request.setAttribute("mensaje", "Profesor actualizado exitosamente");
            }
        }
        
        listarProfesores(request, response);
    }

    private void eliminarProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Profesor profesor = profesorDAO.buscarPorID(id);
        
        if (profesor == null) {
            response.sendRedirect("profesores?accion=listar&error=notfound");
            return;
        }
        
        // VALIDACIÓN: Verificar si tiene cursos asignados
        if (profesorDAO.tieneCursosAsignados(id)) {
            int cantidadCursos = profesorDAO.contarCursosAsignados(id);
            response.sendRedirect("profesores?accion=ver&id=" + id + "&vista=detalle&error=tiene_cursos&cantidad=" + cantidadCursos);
            return;
        }
        
        if (profesorDAO.eliminar(profesor)) {
            response.sendRedirect("profesores?accion=listar&success=delete");
        } else {
            response.sendRedirect("profesores?accion=listar&error=delete");
        }
    }

    /**
     * Muestra los cursos asignados a un profesor.
     */
    private void verCursosAsignados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int profesorId = Integer.parseInt(request.getParameter("id"));
        
        // Obtener información del profesor
        Profesor profesor = profesorDAO.buscarPorID(profesorId);
        
        // Obtener cursos asignados
        List<CursoOfrecidoDetalle> cursosAsignados = cursoOfrecidoDAO.listarPorProfesor(profesorId);
        
        // Enviar al JSP
        request.setAttribute("profesor", profesor);
        request.setAttribute("cursosAsignados", cursosAsignados);
        
        RequestDispatcher rd = request.getRequestDispatcher("cursos-profesor.jsp");
        rd.forward(request, response);
    }
}


