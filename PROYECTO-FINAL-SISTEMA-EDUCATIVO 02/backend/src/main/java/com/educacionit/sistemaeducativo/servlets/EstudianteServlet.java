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

import com.educacionit.sistemaeducativo.entidades.Estudiante;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;
import com.educacionit.sistemaeducativo.implementaciones.EstudianteDAOImpl;

/**
 * Servlet para manejar operaciones CRUD de Estudiantes.
 * Procesa peticiones GET (listar, buscar) y POST (insertar, actualizar, eliminar).
 * 
 * @author Ludmila Martos
 */
public class EstudianteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EstudianteDAOImpl estudianteDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        estudianteDAO = new EstudianteDAOImpl();
    }

    /**
     * Maneja peticiones GET: listar y buscar estudiantes
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        try {
            if ("listar".equals(accion) || accion == null) {
                listarEstudiantes(request, response);
            } else if ("nuevo".equals(accion)) {
                mostrarFormularioNuevo(request, response);
            } else if ("editar".equals(accion)) {
                mostrarFormularioEditar(request, response);
            } else if ("buscar".equals(accion)) {
                buscarEstudiante(request, response);
            } else if ("ver".equals(accion)) {
                verDetalle(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("estudiantes.jsp");
            rd.forward(request, response);
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
                insertarEstudiante(request, response);
            } else if ("actualizar".equals(accion)) {
                actualizarEstudiante(request, response);
            } else if ("eliminar".equals(accion)) {
                eliminarEstudiante(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            response.sendRedirect("estudiantes?accion=listar&error=sql");
        }
    }

    private void listarEstudiantes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        List<Estudiante> estudiantes = estudianteDAO.listar();
        request.setAttribute("estudiantes", estudiantes);
        RequestDispatcher rd = request.getRequestDispatcher("estudiantes.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("nuevo-estudiante.jsp");
        rd.forward(request, response);
    }
    
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = estudianteDAO.buscarPorID(id);
        
        request.setAttribute("estudiante", estudiante);
        RequestDispatcher rd = request.getRequestDispatcher("editar-estudiante.jsp");
        rd.forward(request, response);
    }

    private void buscarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String matricula = request.getParameter("matricula");
        String estado = request.getParameter("estado");
        
        List<Estudiante> estudiantes;
        
        // Prioridad: 1. Estado, 2. Matrícula/Nombre, 3. Listar todos
        if (estado != null && !estado.isEmpty()) {
            // Buscar por estado
            estudiantes = estudianteDAO.buscarPorEstado(estado);
        } else if (matricula != null && !matricula.isEmpty()) {
            // Buscar por matrícula o nombre
            Estudiante estudiante = estudianteDAO.buscarPorMatricula(matricula);
            if (estudiante != null) {
                estudiantes = new ArrayList<>();
                estudiantes.add(estudiante);
            } else {
                // Intentar buscar por nombre si no encontró por matrícula
                estudiantes = estudianteDAO.buscarPorNombre(matricula);
            }
        } else {
            // Listar todos
            estudiantes = estudianteDAO.listar();
        }
        
        request.setAttribute("estudiantes", estudiantes);
        RequestDispatcher rd = request.getRequestDispatcher("estudiantes.jsp");
        rd.forward(request, response);
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = estudianteDAO.buscarPorID(id);
        
        request.setAttribute("estudiante", estudiante);
        
        // Si viene de la acción "ver" se muestra el formulario de edición
        // Si viene de "detalle" se muestra la vista completa
        String vista = "editar-estudiante.jsp";
        if ("detalle".equals(request.getParameter("vista"))) {
            vista = "detalle-estudiante.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    private void insertarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String dni = request.getParameter("numeroDocumento");
        String email = request.getParameter("email");
        String matricula = request.getParameter("matricula");
        
        // VALIDACIÓN 1: Verificar DNI único
        if (estudianteDAO.existeDNI(dni, null)) {
            response.sendRedirect("estudiantes?accion=nuevo&error=dni_duplicado&dni=" + dni);
            return;
        }
        
        // VALIDACIÓN 2: Verificar email único
        if (estudianteDAO.existeEmail(email, null)) {
            response.sendRedirect("estudiantes?accion=nuevo&error=email_duplicado&email=" + email);
            return;
        }
        
        // VALIDACIÓN 3: Verificar matrícula única
        if (estudianteDAO.existeMatricula(matricula, null)) {
            response.sendRedirect("estudiantes?accion=nuevo&error=matricula_duplicada&matricula=" + matricula);
            return;
        }
        
        Estudiante estudiante = new Estudiante();
        
        // Mapear datos del formulario
        estudiante.setTipoDocumento(TipoDocumento.valueOf(request.getParameter("tipoDocumento")));
        estudiante.setNumeroDocumento(dni);
        estudiante.setNombre(request.getParameter("nombre"));
        estudiante.setApellido(request.getParameter("apellido"));
        estudiante.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
        estudiante.setEmail(email);
        estudiante.setTelefono(request.getParameter("telefono"));
        estudiante.setDireccion(request.getParameter("direccion"));
        estudiante.setMatricula(matricula);
        estudiante.setFechaIngreso(LocalDate.parse(request.getParameter("fechaIngreso")));
        
        // Insertar en base de datos
        if (estudianteDAO.insertar(estudiante)) {
            response.sendRedirect("estudiantes?accion=listar&success=insert");
        } else {
            response.sendRedirect("estudiantes?accion=nuevo&error=insert");
        }
    }

    private void actualizarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = estudianteDAO.buscarPorID(id);
        
        if (estudiante != null) {
            String nuevoEmail = request.getParameter("email");
            
            // VALIDACIÓN: Verificar email único (excluyendo el propio estudiante)
            if (!nuevoEmail.equals(estudiante.getEmail()) && 
                estudianteDAO.existeEmail(nuevoEmail, estudiante.getPersonaId())) {
                response.sendRedirect("estudiantes?accion=editar&id=" + id + "&error=email_duplicado");
                return;
            }
            
            // Actualizar datos
            estudiante.setNombre(request.getParameter("nombre"));
            estudiante.setApellido(request.getParameter("apellido"));
            estudiante.setEmail(nuevoEmail);
            estudiante.setTelefono(request.getParameter("telefono"));
            estudiante.setDireccion(request.getParameter("direccion"));
            
            if (estudianteDAO.actualizar(estudiante)) {
                response.sendRedirect("estudiantes?accion=listar&success=update");
            } else {
                response.sendRedirect("estudiantes?accion=editar&id=" + id + "&error=update");
            }
        }
    }

    private void eliminarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = estudianteDAO.buscarPorID(id);
        
        if (estudiante == null) {
            response.sendRedirect("estudiantes?accion=listar&error=notfound");
            return;
        }
        
        // VALIDACIÓN: Verificar si tiene inscripciones
        if (estudianteDAO.tieneInscripciones(id)) {
            int cantidadInscripciones = estudianteDAO.contarInscripciones(id);
            response.sendRedirect("estudiantes?accion=ver&id=" + id + "&vista=detalle&error=tiene_inscripciones&cantidad=" + cantidadInscripciones);
            return;
        }
        
        if (estudianteDAO.eliminar(estudiante)) {
            response.sendRedirect("estudiantes?accion=listar&success=delete");
        } else {
            response.sendRedirect("estudiantes?accion=listar&error=delete");
        }
    }
}

