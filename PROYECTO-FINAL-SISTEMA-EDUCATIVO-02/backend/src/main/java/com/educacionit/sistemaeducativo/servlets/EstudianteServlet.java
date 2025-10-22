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
 * PROPÓSITO:
 * - Implementa el controlador MVC para gestión de estudiantes
 * - Maneja todas las operaciones CRUD de estudiantes
 * - Demuestra separación de responsabilidades (Controller)
 * - Implementa validaciones de negocio complejas
 * - Maneja errores y redirecciones apropiadas
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Patrón MVC: Controller que coordina Model y View
 * - Servlets: manejo de requests HTTP GET/POST
 * - Validaciones de negocio: unicidad, integridad referencial
 * - Manejo de errores: try-catch con redirecciones
 * - RequestDispatcher: forwarding a JSPs
 * - Encoding: UTF-8 para caracteres especiales
 * 
 * @author Ludmila Martos
 */
public class EstudianteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EstudianteDAOImpl estudianteDAO;  // DAO para acceso a datos de estudiantes

    // INICIALIZACIÓN DEL SERVLET
    /**
     * Inicializa el servlet creando la instancia del DAO
     * Demuestra inicialización de recursos en el ciclo de vida del servlet
     */
    @Override
    public void init() throws ServletException {
        super.init();
        estudianteDAO = new EstudianteDAOImpl();  // Crear instancia del DAO
    }

    // MANEJO DE PETICIONES GET (OPERACIONES DE LECTURA)
    /**
     * Maneja peticiones GET: listar, buscar y mostrar formularios
     * Demuestra routing basado en parámetros de request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");  // Obtener acción solicitada
        
        try {
            if ("listar".equals(accion) || accion == null) {
                listarEstudiantes(request, response);           // Listar todos los estudiantes
            } else if ("nuevo".equals(accion)) {
                mostrarFormularioNuevo(request, response);      // Mostrar formulario de creación
            } else if ("editar".equals(accion)) {
                mostrarFormularioEditar(request, response);     // Mostrar formulario de edición
            } else if ("buscar".equals(accion)) {
                buscarEstudiante(request, response);            // Buscar estudiantes por criterios
            } else if ("ver".equals(accion)) {
                verDetalle(request, response);                  // Ver detalle de un estudiante
            }
        } catch (SQLException e) {
            // Manejo de errores de base de datos
            request.setAttribute("error", "Error al procesar la solicitud: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("estudiantes.jsp");
            rd.forward(request, response);
        }
    }

    // MANEJO DE PETICIONES POST (OPERACIONES DE ESCRITURA)
    /**
     * Maneja peticiones POST: insertar, actualizar y eliminar
     * Demuestra manejo de formularios y operaciones de escritura
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");  // Configurar encoding para caracteres especiales
        String accion = request.getParameter("accion");
        
        try {
            if ("insertar".equals(accion)) {
                insertarEstudiante(request, response);          // Crear nuevo estudiante
            } else if ("actualizar".equals(accion)) {
                actualizarEstudiante(request, response);       // Actualizar estudiante existente
            } else if ("eliminar".equals(accion)) {
                eliminarEstudiante(request, response);          // Eliminar estudiante
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

    // MÉTODO DE INSERCIÓN CON VALIDACIONES COMPLEJAS
    /**
     * Inserta un nuevo estudiante con validaciones de unicidad
     * Demuestra validaciones de negocio complejas antes de persistir
     */
    private void insertarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        // Extraer parámetros críticos para validaciones
        String dni = request.getParameter("numeroDocumento");
        String email = request.getParameter("email");
        String matricula = request.getParameter("matricula");
        
        // VALIDACIÓN 1: Verificar DNI único
        // Demuestra validación de integridad de datos críticos
        if (estudianteDAO.existeDNI(dni, null)) {
            response.sendRedirect("estudiantes?accion=nuevo&error=dni_duplicado&dni=" + dni);
            return;
        }
        
        // VALIDACIÓN 2: Verificar email único
        // Demuestra validación de unicidad para comunicación
        if (estudianteDAO.existeEmail(email, null)) {
            response.sendRedirect("estudiantes?accion=nuevo&error=email_duplicado&email=" + email);
            return;
        }
        
        // VALIDACIÓN 3: Verificar matrícula única
        // Demuestra validación de clave de negocio específica del dominio
        if (estudianteDAO.existeMatricula(matricula, null)) {
            response.sendRedirect("estudiantes?accion=nuevo&error=matricula_duplicada&matricula=" + matricula);
            return;
        }
        
        // CREACIÓN Y MAPEO DEL OBJETO ESTUDIANTE
        Estudiante estudiante = new Estudiante();
        
        // Mapear datos del formulario a la entidad
        // Demuestra conversión de datos HTTP a objetos Java
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
        
        // PERSISTENCIA Y MANEJO DE RESULTADOS
        // Demuestra manejo de éxito/fallo con redirecciones apropiadas
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

    // MÉTODO DE ELIMINACIÓN CON VALIDACIÓN DE INTEGRIDAD REFERENCIAL
    /**
     * Elimina un estudiante con validación de integridad referencial
     * Demuestra validación de reglas de negocio antes de eliminar
     */
    private void eliminarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Estudiante estudiante = estudianteDAO.buscarPorID(id);
        
        // VALIDACIÓN: Verificar que el estudiante existe
        if (estudiante == null) {
            response.sendRedirect("estudiantes?accion=listar&error=notfound");
            return;
        }
        
        // VALIDACIÓN CRÍTICA: Verificar integridad referencial
        // Demuestra validación de reglas de negocio complejas
        if (estudianteDAO.tieneInscripciones(id)) {
            int cantidadInscripciones = estudianteDAO.contarInscripciones(id);
            response.sendRedirect("estudiantes?accion=ver&id=" + id + "&vista=detalle&error=tiene_inscripciones&cantidad=" + cantidadInscripciones);
            return;
        }
        
        // ELIMINACIÓN SEGURA
        // Demuestra manejo de éxito/fallo con redirecciones apropiadas
        if (estudianteDAO.eliminar(estudiante)) {
            response.sendRedirect("estudiantes?accion=listar&success=delete");
        } else {
            response.sendRedirect("estudiantes?accion=listar&error=delete");
        }
    }
}

