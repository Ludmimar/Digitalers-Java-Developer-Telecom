package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO simplificado para Cursos Ofrecidos.
 * 
 * @author Ludmila Martos
 */
public class CursoOfrecidoDAOImpl {

    /**
     * Lista todos los cursos ofrecidos con información completa.
     */
    public List<CursoOfrecidoDetalle> listarConDetalles() throws SQLException {
        List<CursoOfrecidoDetalle> cursosOfrecidos = new ArrayList<>();
        String sql = "SELECT " +
                     "co.id as co_id, co.aula, co.horario, co.cupos_disponibles, " +
                     "c.id as curso_id, c.codigo_curso, c.nombre as curso_nombre, c.creditos, " +
                     "prof.id as profesor_id, prof.codigo_profesor, " +
                     "COALESCE(CONCAT(p.nombre, ' ', p.apellido), 'Sin asignar') as profesor_nombre " +
                     "FROM cursos_ofrecidos co " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "LEFT JOIN profesores prof ON co.profesor_id = prof.id " +
                     "LEFT JOIN personas p ON prof.persona_id = p.id " +
                     "WHERE c.estado = 'ACTIVO' " +
                     "ORDER BY c.nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                CursoOfrecidoDetalle detalle = new CursoOfrecidoDetalle();
                detalle.id = rs.getInt("co_id");
                detalle.aula = rs.getString("aula");
                detalle.horario = rs.getString("horario");
                detalle.cuposDisponibles = rs.getInt("cupos_disponibles");
                detalle.cursoId = rs.getInt("curso_id");
                detalle.codigoCurso = rs.getString("codigo_curso");
                detalle.cursoNombre = rs.getString("curso_nombre");
                detalle.creditos = rs.getInt("creditos");
                
                Integer profId = rs.getInt("profesor_id");
                detalle.profesorId = rs.wasNull() ? null : profId;
                detalle.profesorNombre = rs.getString("profesor_nombre");
                
                cursosOfrecidos.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cursos ofrecidos: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return cursosOfrecidos;
    }

    /**
     * Lista los cursos ofrecidos de un profesor específico.
     */
    public List<CursoOfrecidoDetalle> listarPorProfesor(Integer profesorId) throws SQLException {
        List<CursoOfrecidoDetalle> cursosOfrecidos = new ArrayList<>();
        String sql = "SELECT " +
                     "co.id as co_id, co.aula, co.horario, co.cupos_disponibles, " +
                     "c.id as curso_id, c.codigo_curso, c.nombre as curso_nombre, c.creditos, " +
                     "prof.id as profesor_id, prof.codigo_profesor, " +
                     "CONCAT(p.nombre, ' ', p.apellido) as profesor_nombre, " +
                     "pa.nombre as periodo_nombre, pa.anio, pa.semestre " +
                     "FROM cursos_ofrecidos co " +
                     "INNER JOIN cursos c ON co.curso_id = c.id " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas p ON prof.persona_id = p.id " +
                     "LEFT JOIN periodos_academicos pa ON co.periodo_id = pa.id " +
                     "WHERE prof.id = ? " +
                     "ORDER BY pa.anio DESC, pa.semestre DESC, c.nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, profesorId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CursoOfrecidoDetalle detalle = new CursoOfrecidoDetalle();
                    detalle.id = rs.getInt("co_id");
                    detalle.aula = rs.getString("aula");
                    detalle.horario = rs.getString("horario");
                    detalle.cuposDisponibles = rs.getInt("cupos_disponibles");
                    detalle.cursoId = rs.getInt("curso_id");
                    detalle.codigoCurso = rs.getString("codigo_curso");
                    detalle.cursoNombre = rs.getString("curso_nombre");
                    detalle.creditos = rs.getInt("creditos");
                    detalle.profesorId = rs.getInt("profesor_id");
                    detalle.profesorNombre = rs.getString("profesor_nombre");
                    
                    // Info del período
                    detalle.periodoNombre = rs.getString("periodo_nombre");
                    detalle.anio = rs.getInt("anio");
                    detalle.semestre = rs.getString("semestre");
                    
                    cursosOfrecidos.add(detalle);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cursos por profesor: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return cursosOfrecidos;
    }

    /**
     * Verifica si un curso ya está asignado a un período académico específico.
     * Retorna true si ya existe la asignación.
     */
    public boolean yaEstaAsignado(Integer cursoId, Integer periodoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos " +
                     "WHERE curso_id = ? AND periodo_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            ps.setInt(2, periodoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Obtiene el detalle de la asignación existente de un curso en un período.
     */
    public AsignacionExistente obtenerAsignacionExistente(Integer cursoId, Integer periodoId) throws SQLException {
        String sql = "SELECT " +
                     "co.id, co.aula, co.horario, " +
                     "CONCAT(p.nombre, ' ', p.apellido) as profesor_nombre, " +
                     "pa.nombre as periodo_nombre " +
                     "FROM cursos_ofrecidos co " +
                     "INNER JOIN profesores prof ON co.profesor_id = prof.id " +
                     "INNER JOIN personas p ON prof.persona_id = p.id " +
                     "INNER JOIN periodos_academicos pa ON co.periodo_id = pa.id " +
                     "WHERE co.curso_id = ? AND co.periodo_id = ? " +
                     "LIMIT 1";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            ps.setInt(2, periodoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AsignacionExistente asignacion = new AsignacionExistente();
                    asignacion.id = rs.getInt("id");
                    asignacion.aula = rs.getString("aula");
                    asignacion.horario = rs.getString("horario");
                    asignacion.profesorNombre = rs.getString("profesor_nombre");
                    asignacion.periodoNombre = rs.getString("periodo_nombre");
                    return asignacion;
                }
            }
        }
        return null;
    }

    /**
     * Clase interna para cursos ofrecidos con detalles.
     */
    public static class CursoOfrecidoDetalle {
        public Integer id;
        public String aula;
        public String horario;
        public Integer cuposDisponibles;
        public Integer cursoId;
        public String codigoCurso;
        public String cursoNombre;
        public Integer creditos;
        public Integer profesorId;
        public String profesorNombre;
        // Info del período
        public String periodoNombre;
        public Integer anio;
        public String semestre;
    }
    
    /**
     * Clase interna para asignaciones existentes.
     */
    public static class AsignacionExistente {
        public Integer id;
        public String aula;
        public String horario;
        public String profesorNombre;
        public String periodoNombre;
    }
}

