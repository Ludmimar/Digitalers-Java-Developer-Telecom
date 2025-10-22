package com.educacionit.sistemaeducativo.servlets;

import com.educacionit.sistemaeducativo.entidades.Inscripcion;
import com.educacionit.sistemaeducativo.entidades.Estudiante;
import com.educacionit.sistemaeducativo.enumerados.EstadoInscripcion;
import com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.InscripcionDAOImpl.InscripcionDetalle;
import com.educacionit.sistemaeducativo.implementaciones.EstudianteDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CursoOfrecidoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.PeriodoAcademicoDAOImpl;
import com.educacionit.sistemaeducativo.implementaciones.CorrelatividadDAOImpl;
import com.educacionit.sistemaeducativo.entidades.PeriodoAcademico;
import com.educacionit.sistemaeducativo.entidades.Correlatividad;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servlet para gestionar las operaciones CRUD de Inscripciones.
 * 
 * @author Ludmila Martos
 */
public class InscripcionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InscripcionDAOImpl inscripcionDAO;
    private EstudianteDAOImpl estudianteDAO;
    private CursoDAOImpl cursoDAO;
    private CursoOfrecidoDAOImpl cursoOfrecidoDAO;
    private PeriodoAcademicoDAOImpl periodoDAO;
    private CorrelatividadDAOImpl correlatividadDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        inscripcionDAO = new InscripcionDAOImpl();
        estudianteDAO = new EstudianteDAOImpl();
        cursoDAO = new CursoDAOImpl();
        cursoOfrecidoDAO = new CursoOfrecidoDAOImpl();
        periodoDAO = new PeriodoAcademicoDAOImpl();
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
                listarInscripciones(request, response);
            } else if (accion.equals("listarPorEstudiante")) {
                listarPorEstudiante(request, response);
            } else if (accion.equals("ver")) {
                verDetalle(request, response);
            } else if (accion.equals("nueva")) {
                mostrarFormularioNuevo(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos: " + e.getMessage(), e);
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
                insertarInscripcion(request, response);
            } else if (accion.equals("actualizar")) {
                actualizarInscripcion(request, response);
            } else if (accion.equals("eliminar")) {
                eliminarInscripcion(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos: " + e.getMessage(), e);
        }
    }

    /**
     * Lista todas las inscripciones con detalles y filtros.
     */
    private void listarInscripciones(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        String cursoId = request.getParameter("cursoId");
        String estado = request.getParameter("estado");
        
        List<InscripcionDetalle> inscripciones;
        
        if (cursoId != null && !cursoId.isEmpty()) {
            inscripciones = inscripcionDAO.buscarPorCursoId(Integer.parseInt(cursoId));
        } else if (estado != null && !estado.isEmpty()) {
            inscripciones = inscripcionDAO.buscarPorEstado(estado);
        } else {
            inscripciones = inscripcionDAO.listarConDetalles();
        }
        
        // Cargar lista de cursos para el filtro
        request.setAttribute("cursos", cursoDAO.listar());
        request.setAttribute("inscripciones", inscripciones);
        request.getRequestDispatcher("inscripciones.jsp").forward(request, response);
    }

    /**
     * Lista inscripciones de un estudiante específico.
     */
    private void listarPorEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int estudianteId = Integer.parseInt(request.getParameter("estudianteId"));
        
        // Obtener inscripciones del estudiante
        List<InscripcionDetalle> inscripciones = inscripcionDAO.listarConDetalles();
        
        // Filtrar por estudiante
        inscripciones.removeIf(i -> !i.estudianteId.equals(estudianteId));
        
        // Obtener info del estudiante para mostrar
        Estudiante estudiante = estudianteDAO.buscarPorID(estudianteId);
        
        request.setAttribute("inscripciones", inscripciones);
        request.setAttribute("estudianteFiltro", estudiante);
        request.setAttribute("cursos", cursoDAO.listar());
        request.getRequestDispatcher("inscripciones.jsp").forward(request, response);
    }

    /**
     * Muestra el detalle de una inscripción con información completa.
     */
    private void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Obtener inscripción básica para el formulario
        Inscripcion inscripcion = inscripcionDAO.buscarPorID(id);
        
        // Obtener detalles completos para mostrar
        InscripcionDetalle detalle = inscripcionDAO.buscarDetallesPorID(id);
        
        if (inscripcion != null && detalle != null) {
            request.setAttribute("inscripcion", inscripcion);
            request.setAttribute("detalle", detalle);
            
            // Capturar returnUrl si existe (de dónde viene)
            String returnUrl = request.getParameter("returnUrl");
            if (returnUrl != null && !returnUrl.isEmpty()) {
                request.setAttribute("returnUrl", returnUrl);
            }
            
            request.getRequestDispatcher("detalle-inscripcion.jsp").forward(request, response);
        } else {
            response.sendRedirect("inscripciones?accion=listar&error=notfound");
        }
    }

    /**
     * Muestra el formulario para nueva inscripción.
     */
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        
        // Cargar listas de estudiantes y cursos ofrecidos para el formulario
        request.setAttribute("estudiantes", estudianteDAO.listar());
        request.setAttribute("cursosOfrecidos", cursoOfrecidoDAO.listarConDetalles());
        request.getRequestDispatcher("nueva-inscripcion.jsp").forward(request, response);
    }

    /**
     * Inserta una nueva inscripción con validaciones.
     */
    private void insertarInscripcion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int estudianteId = Integer.parseInt(request.getParameter("estudianteId"));
        int cursoOfrecidoId = Integer.parseInt(request.getParameter("cursoOfrecidoId"));
        
        // VALIDACIÓN 1: Verificar si ya existe la inscripción
        if (inscripcionDAO.existeInscripcion(estudianteId, cursoOfrecidoId)) {
            response.sendRedirect("inscripciones?accion=nueva&error=duplicado");
            return;
        }
        
        // VALIDACIÓN 2: Verificar cupos disponibles
        int cursoId = inscripcionDAO.obtenerCursoIdDesdeOfrecido(cursoOfrecidoId);
        if (cursoId > 0 && !cursoDAO.tieneCuposDisponibles(cursoId)) {
            response.sendRedirect("inscripciones?accion=nueva&error=cupo_lleno");
            return;
        }
        
        // VALIDACIÓN 3: Verificar que el período acepta inscripciones
        PeriodoAcademico periodoActivo = periodoDAO.obtenerPeriodoActivo();
        if (periodoActivo != null && !periodoActivo.aceptaInscripciones()) {
            response.sendRedirect("inscripciones?accion=nueva&error=fuera_de_plazo&periodo=" + 
                java.net.URLEncoder.encode(periodoActivo.getNombre(), "UTF-8") +
                "&estado=" + periodoActivo.getEstado());
            return;
        }
        
        // VALIDACIÓN 4: Verificar correlatividades
        if (!correlatividadDAO.cumpleCorrelativas(estudianteId, cursoId)) {
            List<Correlatividad> faltantes = correlatividadDAO.obtenerCorrelativasFaltantes(estudianteId, cursoId);
            StringBuilder correlativasFaltantes = new StringBuilder();
            for (Correlatividad corr : faltantes) {
                if (correlativasFaltantes.length() > 0) {
                    correlativasFaltantes.append(", ");
                }
                correlativasFaltantes.append(corr.getCorrelativaNombre());
            }
            
            response.sendRedirect("inscripciones?accion=nueva&error=falta_correlativa&cursos=" + 
                java.net.URLEncoder.encode(correlativasFaltantes.toString(), "UTF-8"));
            return;
        }
        
        // VALIDACIÓN 5: Límite de créditos por estudiante
        int creditosActuales = inscripcionDAO.calcularCreditosActuales(estudianteId, periodoActivo.getId());
        int creditosCursoNuevo = cursoDAO.buscarPorID(cursoId).getCreditos();
        final int LIMITE_CREDITOS = 30;
        
        if (creditosActuales + creditosCursoNuevo > LIMITE_CREDITOS) {
            response.sendRedirect("inscripciones?accion=nueva&error=excede_creditos&actual=" + 
                creditosActuales + "&nuevo=" + creditosCursoNuevo + "&limite=" + LIMITE_CREDITOS);
            return;
        }
        
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudianteId(estudianteId);
        inscripcion.setCursoOfrecidoId(cursoOfrecidoId);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstado(EstadoInscripcion.INSCRITO);

        if (inscripcionDAO.insertar(inscripcion)) {
            response.sendRedirect("inscripciones?accion=listar&success=insert");
        } else {
            response.sendRedirect("inscripciones?accion=nueva&error=insert");
        }
    }

    /**
     * Actualiza una inscripción (estado, nota, asistencia).
     */
    private void actualizarInscripcion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(Integer.parseInt(request.getParameter("id")));
        inscripcion.setEstado(EstadoInscripcion.valueOf(request.getParameter("estado")));
        
        String notaStr = request.getParameter("notaFinal");
        if (notaStr != null && !notaStr.isEmpty()) {
            inscripcion.setNotaFinal(Double.parseDouble(notaStr));
            
            // Si la nota es >= 7 y el estado es APROBADO, guardar fecha de aprobación
            if (inscripcion.getNotaFinal() >= 7 && inscripcion.getEstado() == EstadoInscripcion.APROBADO) {
                inscripcion.setFechaAprobacion(java.time.LocalDate.now());
            }
        }

        // Obtener returnUrl si existe
        String returnUrl = request.getParameter("returnUrl");
        
        if (inscripcionDAO.actualizar(inscripcion)) {
            // Redirigir a la URL de origen o a la lista general
            if (returnUrl != null && !returnUrl.isEmpty()) {
                response.sendRedirect(returnUrl + "&success=update");
            } else {
                response.sendRedirect("inscripciones?accion=listar&success=update");
            }
        } else {
            response.sendRedirect("inscripciones?accion=ver&id=" + inscripcion.getId() + "&error=update");
        }
    }

    /**
     * Elimina una inscripción (dar de baja).
     */
    private void eliminarInscripcion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Inscripcion inscripcion = inscripcionDAO.buscarPorID(id);

        // Obtener returnUrl si existe
        String returnUrl = request.getParameter("returnUrl");
        
        if (inscripcion != null && inscripcionDAO.eliminar(inscripcion)) {
            // Redirigir a la URL de origen o a la lista general
            if (returnUrl != null && !returnUrl.isEmpty()) {
                response.sendRedirect(returnUrl + "&success=delete");
            } else {
                response.sendRedirect("inscripciones?accion=listar&success=delete");
            }
        } else {
            if (returnUrl != null && !returnUrl.isEmpty()) {
                response.sendRedirect(returnUrl + "&error=delete");
            } else {
                response.sendRedirect("inscripciones?accion=listar&error=delete");
            }
        }
    }
}

