package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.dao.DAO;
import com.educacionit.sistemaeducativo.entidades.Inscripcion;
import com.educacionit.sistemaeducativo.enumerados.EstadoInscripcion;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón DAO para la entidad Inscripcion.
 * Gestiona las operaciones CRUD en la base de datos MySQL.
 * 
 * @author Ludmila Martos
 */
public class InscripcionDAOImpl implements DAO<Integer, Inscripcion> {

    /**
     * Busca una inscripción por su ID.
     */
    @Override
    public Inscripcion buscarPorID(Integer id) throws SQLException {
        String sql = "SELECT * FROM inscripciones WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearInscripcion(rs);
                }
            }
        }
        return null;
    }

    /**
     * Busca una inscripción por ID con todos sus detalles (JOIN completo).
     */
    public InscripcionDetalle buscarDetallesPorID(Integer id) throws SQLException {
        String sql = "SELECT " +
                     "i.id, i.fecha_inscripcion, i.estado, i.nota_final, i.fecha_aprobacion, " +
                     "e.id as estudiante_id, e.matricula, " +
                     "p.nombre as estudiante_nombre, p.apellido as estudiante_apellido, p.email, " +
                     "c.id as curso_id, c.nombre as curso_nombre, c.codigo_curso, c.creditos, " +
                     "co.id as curso_ofrecido_id, co.aula, co.horario, " +
                     "prof.codigo_profesor, " +
                     "pp.nombre as profesor_nombre, pp.apellido as profesor_apellido " +
                     "FROM inscripciones i " +
                     "INNER JOIN estudiantes e ON i.estudiante_id = e.id " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas pp ON prof.persona_id = pp.id " +
                     "WHERE i.id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    InscripcionDetalle detalle = new InscripcionDetalle();
                    detalle.id = rs.getInt("id");
                    detalle.fechaInscripcion = rs.getTimestamp("fecha_inscripcion").toLocalDateTime();
                    detalle.estado = EstadoInscripcion.valueOf(rs.getString("estado"));
                    
                    Double nota = rs.getDouble("nota_final");
                    detalle.notaFinal = rs.wasNull() ? null : nota;
                    
                    Date fechaAprob = rs.getDate("fecha_aprobacion");
                    detalle.fechaAprobacion = rs.wasNull() ? null : fechaAprob;
                    
                    detalle.estudianteId = rs.getInt("estudiante_id");
                    detalle.matricula = rs.getString("matricula");
                    detalle.estudianteNombre = rs.getString("estudiante_nombre") + " " + rs.getString("estudiante_apellido");
                    detalle.estudianteEmail = rs.getString("email");
                    detalle.cursoId = rs.getInt("curso_id");
                    detalle.cursoNombre = rs.getString("curso_nombre");
                    detalle.codigoCurso = rs.getString("codigo_curso");
                    detalle.creditos = rs.getInt("creditos");
                    detalle.aula = rs.getString("aula");
                    detalle.horario = rs.getString("horario");
                    detalle.profesorNombre = rs.getString("profesor_nombre") + " " + rs.getString("profesor_apellido");
                    
                    return detalle;
                }
            }
        }
        return null;
    }

    /**
     * Inserta una nueva inscripción en la base de datos.
     */
    @Override
    public boolean insertar(Inscripcion inscripcion) throws SQLException {
        String sql = "INSERT INTO inscripciones (estudiante_id, curso_ofrecido_id, " +
                     "fecha_inscripcion, estado, nota_final, fecha_aprobacion) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, inscripcion.getEstudianteId());
            ps.setInt(2, inscripcion.getCursoOfrecidoId());
            ps.setTimestamp(3, Timestamp.valueOf(inscripcion.getFechaInscripcion()));
            ps.setString(4, inscripcion.getEstado().toString());
            
            if (inscripcion.getNotaFinal() != null) {
                ps.setDouble(5, inscripcion.getNotaFinal());
            } else {
                ps.setNull(5, Types.DECIMAL);
            }
            
            if (inscripcion.getFechaAprobacion() != null) {
                ps.setDate(6, Date.valueOf(inscripcion.getFechaAprobacion()));
            } else {
                ps.setNull(6, Types.DATE);
            }
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        inscripcion.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Actualiza una inscripción existente.
     */
    @Override
    public boolean actualizar(Inscripcion inscripcion) throws SQLException {
        String sql = "UPDATE inscripciones SET estado = ?, nota_final = ?, " +
                     "fecha_aprobacion = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, inscripcion.getEstado().toString());
            
            if (inscripcion.getNotaFinal() != null) {
                ps.setDouble(2, inscripcion.getNotaFinal());
            } else {
                ps.setNull(2, Types.DECIMAL);
            }
            
            if (inscripcion.getFechaAprobacion() != null) {
                ps.setDate(3, Date.valueOf(inscripcion.getFechaAprobacion()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            
            ps.setInt(4, inscripcion.getId());
            
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina una inscripción (dar de baja).
     */
    @Override
    public boolean eliminar(Inscripcion inscripcion) throws SQLException {
        String sql = "DELETE FROM inscripciones WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, inscripcion.getId());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Lista todas las inscripciones.
     */
    @Override
    public List<Inscripcion> listar() throws SQLException {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones ORDER BY fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                inscripciones.add(mapearInscripcion(rs));
            }
        }
        return inscripciones;
    }

    /**
     * Lista inscripciones por estudiante.
     */
    public List<Inscripcion> listarPorEstudiante(Integer estudianteId) throws SQLException {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones WHERE estudiante_id = ? " +
                     "ORDER BY fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    inscripciones.add(mapearInscripcion(rs));
                }
            }
        }
        return inscripciones;
    }

    /**
     * Lista inscripciones por curso ofrecido.
     */
    public List<Inscripcion> listarPorCursoOfrecido(Integer cursoOfrecidoId) throws SQLException {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones WHERE curso_ofrecido_id = ? " +
                     "ORDER BY fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoOfrecidoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    inscripciones.add(mapearInscripcion(rs));
                }
            }
        }
        return inscripciones;
    }

    /**
     * Lista inscripciones con información completa (JOIN).
     */
    public List<InscripcionDetalle> listarConDetalles() throws SQLException {
        List<InscripcionDetalle> inscripciones = new ArrayList<>();
        String sql = "SELECT " +
                     "i.id, i.fecha_inscripcion, i.estado, i.nota_final, i.fecha_aprobacion, " +
                     "e.id as estudiante_id, e.matricula, " +
                     "p.nombre as estudiante_nombre, p.apellido as estudiante_apellido, p.email, " +
                     "c.id as curso_id, c.nombre as curso_nombre, c.codigo_curso, c.creditos, " +
                     "co.id as curso_ofrecido_id, co.aula, co.horario, " +
                     "prof.codigo_profesor, " +
                     "pp.nombre as profesor_nombre, pp.apellido as profesor_apellido " +
                     "FROM inscripciones i " +
                     "INNER JOIN estudiantes e ON i.estudiante_id = e.id " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas pp ON prof.persona_id = pp.id " +
                     "ORDER BY i.fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                InscripcionDetalle detalle = new InscripcionDetalle();
                detalle.id = rs.getInt("id");
                detalle.fechaInscripcion = rs.getTimestamp("fecha_inscripcion").toLocalDateTime();
                detalle.estado = EstadoInscripcion.valueOf(rs.getString("estado"));
                
                Double nota = rs.getDouble("nota_final");
                detalle.notaFinal = rs.wasNull() ? null : nota;
                
                Date fechaAprob = rs.getDate("fecha_aprobacion");
                detalle.fechaAprobacion = rs.wasNull() ? null : fechaAprob;
                
                detalle.estudianteId = rs.getInt("estudiante_id");
                detalle.matricula = rs.getString("matricula");
                detalle.estudianteNombre = rs.getString("estudiante_nombre") + " " + rs.getString("estudiante_apellido");
                detalle.estudianteEmail = rs.getString("email");
                detalle.cursoId = rs.getInt("curso_id");
                detalle.cursoNombre = rs.getString("curso_nombre");
                detalle.codigoCurso = rs.getString("codigo_curso");
                detalle.creditos = rs.getInt("creditos");
                detalle.aula = rs.getString("aula");
                detalle.horario = rs.getString("horario");
                detalle.profesorNombre = rs.getString("profesor_nombre") + " " + rs.getString("profesor_apellido");
                
                inscripciones.add(detalle);
            }
        }
        return inscripciones;
    }

    /**
     * Busca inscripciones por ID de curso.
     */
    public List<InscripcionDetalle> buscarPorCursoId(Integer cursoId) throws SQLException {
        List<InscripcionDetalle> inscripciones = new ArrayList<>();
        String sql = "SELECT " +
                     "i.id, i.fecha_inscripcion, i.estado, i.nota_final, i.fecha_aprobacion, " +
                     "e.id as estudiante_id, e.matricula, " +
                     "p.nombre as estudiante_nombre, p.apellido as estudiante_apellido, p.email, " +
                     "c.id as curso_id, c.nombre as curso_nombre, c.codigo_curso, c.creditos, " +
                     "co.id as curso_ofrecido_id, co.aula, co.horario, " +
                     "prof.codigo_profesor, " +
                     "pp.nombre as profesor_nombre, pp.apellido as profesor_apellido " +
                     "FROM inscripciones i " +
                     "INNER JOIN estudiantes e ON i.estudiante_id = e.id " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas pp ON prof.persona_id = pp.id " +
                     "WHERE c.id = ? " +
                     "ORDER BY i.fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InscripcionDetalle detalle = new InscripcionDetalle();
                    detalle.id = rs.getInt("id");
                    detalle.fechaInscripcion = rs.getTimestamp("fecha_inscripcion").toLocalDateTime();
                    detalle.estado = EstadoInscripcion.valueOf(rs.getString("estado"));
                    
                    Double nota = rs.getDouble("nota_final");
                    detalle.notaFinal = rs.wasNull() ? null : nota;
                    
                    Date fechaAprob = rs.getDate("fecha_aprobacion");
                    detalle.fechaAprobacion = rs.wasNull() ? null : fechaAprob;
                    
                    detalle.estudianteId = rs.getInt("estudiante_id");
                    detalle.matricula = rs.getString("matricula");
                    detalle.estudianteNombre = rs.getString("estudiante_nombre") + " " + rs.getString("estudiante_apellido");
                    detalle.estudianteEmail = rs.getString("email");
                    detalle.cursoId = rs.getInt("curso_id");
                    detalle.cursoNombre = rs.getString("curso_nombre");
                    detalle.codigoCurso = rs.getString("codigo_curso");
                    detalle.creditos = rs.getInt("creditos");
                    detalle.aula = rs.getString("aula");
                    detalle.horario = rs.getString("horario");
                    detalle.profesorNombre = rs.getString("profesor_nombre") + " " + rs.getString("profesor_apellido");
                    
                    inscripciones.add(detalle);
                }
            }
        }
        return inscripciones;
    }

    /**
     * Busca inscripciones por nombre de curso.
     */
    public List<InscripcionDetalle> buscarPorCurso(String nombreCurso) throws SQLException {
        List<InscripcionDetalle> inscripciones = new ArrayList<>();
        String sql = "SELECT " +
                     "i.id, i.fecha_inscripcion, i.estado, i.nota_final, i.fecha_aprobacion, " +
                     "e.id as estudiante_id, e.matricula, " +
                     "p.nombre as estudiante_nombre, p.apellido as estudiante_apellido, p.email, " +
                     "c.id as curso_id, c.nombre as curso_nombre, c.codigo_curso, c.creditos, " +
                     "co.id as curso_ofrecido_id, co.aula, co.horario, " +
                     "prof.codigo_profesor, " +
                     "pp.nombre as profesor_nombre, pp.apellido as profesor_apellido " +
                     "FROM inscripciones i " +
                     "INNER JOIN estudiantes e ON i.estudiante_id = e.id " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas pp ON prof.persona_id = pp.id " +
                     "WHERE c.nombre LIKE ? OR c.codigo_curso LIKE ? " +
                     "ORDER BY i.fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String patron = "%" + nombreCurso + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InscripcionDetalle detalle = new InscripcionDetalle();
                    detalle.id = rs.getInt("id");
                    detalle.fechaInscripcion = rs.getTimestamp("fecha_inscripcion").toLocalDateTime();
                    detalle.estado = EstadoInscripcion.valueOf(rs.getString("estado"));
                    
                    Double nota = rs.getDouble("nota_final");
                    detalle.notaFinal = rs.wasNull() ? null : nota;
                    
                    Date fechaAprob = rs.getDate("fecha_aprobacion");
                    detalle.fechaAprobacion = rs.wasNull() ? null : fechaAprob;
                    
                    detalle.estudianteId = rs.getInt("estudiante_id");
                    detalle.matricula = rs.getString("matricula");
                    detalle.estudianteNombre = rs.getString("estudiante_nombre") + " " + rs.getString("estudiante_apellido");
                    detalle.estudianteEmail = rs.getString("email");
                    detalle.cursoId = rs.getInt("curso_id");
                    detalle.cursoNombre = rs.getString("curso_nombre");
                    detalle.codigoCurso = rs.getString("codigo_curso");
                    detalle.creditos = rs.getInt("creditos");
                    detalle.aula = rs.getString("aula");
                    detalle.horario = rs.getString("horario");
                    detalle.profesorNombre = rs.getString("profesor_nombre") + " " + rs.getString("profesor_apellido");
                    
                    inscripciones.add(detalle);
                }
            }
        }
        return inscripciones;
    }

    /**
     * Busca inscripciones por estado.
     */
    public List<InscripcionDetalle> buscarPorEstado(String estado) throws SQLException {
        List<InscripcionDetalle> inscripciones = new ArrayList<>();
        String sql = "SELECT " +
                     "i.id, i.fecha_inscripcion, i.estado, i.nota_final, i.fecha_aprobacion, " +
                     "e.id as estudiante_id, e.matricula, " +
                     "p.nombre as estudiante_nombre, p.apellido as estudiante_apellido, p.email, " +
                     "c.id as curso_id, c.nombre as curso_nombre, c.codigo_curso, c.creditos, " +
                     "co.id as curso_ofrecido_id, co.aula, co.horario, " +
                     "prof.codigo_profesor, " +
                     "pp.nombre as profesor_nombre, pp.apellido as profesor_apellido " +
                     "FROM inscripciones i " +
                     "INNER JOIN estudiantes e ON i.estudiante_id = e.id " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas pp ON prof.persona_id = pp.id " +
                     "WHERE i.estado = ? " +
                     "ORDER BY i.fecha_inscripcion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, estado);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InscripcionDetalle detalle = new InscripcionDetalle();
                    detalle.id = rs.getInt("id");
                    detalle.fechaInscripcion = rs.getTimestamp("fecha_inscripcion").toLocalDateTime();
                    detalle.estado = EstadoInscripcion.valueOf(rs.getString("estado"));
                    
                    Double nota = rs.getDouble("nota_final");
                    detalle.notaFinal = rs.wasNull() ? null : nota;
                    
                    Date fechaAprob = rs.getDate("fecha_aprobacion");
                    detalle.fechaAprobacion = rs.wasNull() ? null : fechaAprob;
                    
                    detalle.estudianteId = rs.getInt("estudiante_id");
                    detalle.matricula = rs.getString("matricula");
                    detalle.estudianteNombre = rs.getString("estudiante_nombre") + " " + rs.getString("estudiante_apellido");
                    detalle.estudianteEmail = rs.getString("email");
                    detalle.cursoId = rs.getInt("curso_id");
                    detalle.cursoNombre = rs.getString("curso_nombre");
                    detalle.codigoCurso = rs.getString("codigo_curso");
                    detalle.creditos = rs.getInt("creditos");
                    detalle.aula = rs.getString("aula");
                    detalle.horario = rs.getString("horario");
                    detalle.profesorNombre = rs.getString("profesor_nombre") + " " + rs.getString("profesor_apellido");
                    
                    inscripciones.add(detalle);
                }
            }
        }
        return inscripciones;
    }

    /**
     * Verifica si un estudiante ya está inscrito en un curso.
     */
    public boolean existeInscripcion(Integer estudianteId, Integer cursoOfrecidoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM inscripciones " +
                     "WHERE estudiante_id = ? AND curso_ofrecido_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            ps.setInt(2, cursoOfrecidoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }

    /**
     * Obtiene el curso_id desde un curso_ofrecido_id.
     */
    public int obtenerCursoIdDesdeOfrecido(Integer cursoOfrecidoId) throws SQLException {
        String sql = "SELECT curso_id FROM cursos_ofrecidos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoOfrecidoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("curso_id");
                }
            }
        }
        return 0;
    }

    /**
     * Cuenta el total de inscripciones.
     */
    public int contarTotal() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM inscripciones";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
    
    /**
     * Calcula los créditos actuales de un estudiante en un período específico.
     * Solo cuenta inscripciones en estado CURSANDO o posteriores (no PENDIENTE).
     */
    public int calcularCreditosActuales(Integer estudianteId, Integer periodoId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(c.creditos), 0) as total_creditos " +
                     "FROM inscripciones i " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "WHERE i.estudiante_id = ? AND co.periodo_id = ? " +
                     "AND i.estado IN ('CURSANDO', 'APROBADO', 'REPROBADO')";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            ps.setInt(2, periodoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_creditos");
                }
            }
        }
        return 0;
    }

    /**
     * Mapea un ResultSet a un objeto Inscripcion.
     */
    private Inscripcion mapearInscripcion(ResultSet rs) throws SQLException {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(rs.getInt("id"));
        inscripcion.setEstudianteId(rs.getInt("estudiante_id"));
        inscripcion.setCursoOfrecidoId(rs.getInt("curso_ofrecido_id"));
        inscripcion.setFechaInscripcion(rs.getTimestamp("fecha_inscripcion").toLocalDateTime());
        inscripcion.setEstado(EstadoInscripcion.valueOf(rs.getString("estado")));
        
        Double notaFinal = rs.getDouble("nota_final");
        if (!rs.wasNull()) {
            inscripcion.setNotaFinal(notaFinal);
        }
        
        // fecha_aprobacion es opcional
        Date fechaAprobacion = rs.getDate("fecha_aprobacion");
        if (!rs.wasNull()) {
            inscripcion.setFechaAprobacion(fechaAprobacion.toLocalDate());
        }
        
        return inscripcion;
    }

    /**
     * Clase interna para inscripciones con detalles completos.
     */
    public static class InscripcionDetalle {
        public Integer id;
        public LocalDateTime fechaInscripcion;
        public EstadoInscripcion estado;
        public Double notaFinal;
        public Date fechaAprobacion;
        public Integer estudianteId;
        public String matricula;
        public String estudianteNombre;
        public String estudianteEmail;
        public Integer cursoId;
        public String cursoNombre;
        public String codigoCurso;
        public Integer creditos;
        public String aula;
        public String horario;
        public String profesorNombre;
    }
}

