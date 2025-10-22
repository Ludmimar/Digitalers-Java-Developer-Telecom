package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.dao.DAO;
import com.educacionit.sistemaeducativo.entidades.PeriodoAcademico;
import com.educacionit.sistemaeducativo.entidades.PeriodoAcademico.EstadoPeriodo;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón DAO para la entidad PeriodoAcademico.
 * 
 * @author Ludmila Martos
 */
public class PeriodoAcademicoDAOImpl implements DAO<Integer, PeriodoAcademico> {

    @Override
    public PeriodoAcademico buscarPorID(Integer id) throws SQLException {
        String sql = "SELECT * FROM periodos_academicos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPeriodo(rs);
                }
            }
        }
        return null;
    }

    @Override
    public boolean insertar(PeriodoAcademico periodo) throws SQLException {
        String sql = "INSERT INTO periodos_academicos (nombre, anio, semestre, activo, " +
                     "fecha_inicio_inscripciones, fecha_fin_inscripciones, " +
                     "fecha_inicio_clases, fecha_fin_clases, descripcion, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, periodo.getNombre());
            ps.setInt(2, periodo.getAnio());
            ps.setString(3, periodo.getSemestre());
            ps.setBoolean(4, periodo.getActivo());
            
            if (periodo.getFechaInicioInscripciones() != null) {
                ps.setDate(5, Date.valueOf(periodo.getFechaInicioInscripciones()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            
            if (periodo.getFechaFinInscripciones() != null) {
                ps.setDate(6, Date.valueOf(periodo.getFechaFinInscripciones()));
            } else {
                ps.setNull(6, Types.DATE);
            }
            
            if (periodo.getFechaInicioClases() != null) {
                ps.setDate(7, Date.valueOf(periodo.getFechaInicioClases()));
            } else {
                ps.setNull(7, Types.DATE);
            }
            
            if (periodo.getFechaFinClases() != null) {
                ps.setDate(8, Date.valueOf(periodo.getFechaFinClases()));
            } else {
                ps.setNull(8, Types.DATE);
            }
            
            ps.setString(9, periodo.getDescripcion());
            ps.setString(10, periodo.getEstado().name());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        periodo.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizar(PeriodoAcademico periodo) throws SQLException {
        String sql = "UPDATE periodos_academicos SET nombre = ?, anio = ?, semestre = ?, activo = ?, " +
                     "fecha_inicio_inscripciones = ?, fecha_fin_inscripciones = ?, " +
                     "fecha_inicio_clases = ?, fecha_fin_clases = ?, descripcion = ?, estado = ? " +
                     "WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, periodo.getNombre());
            ps.setInt(2, periodo.getAnio());
            ps.setString(3, periodo.getSemestre());
            ps.setBoolean(4, periodo.getActivo());
            
            if (periodo.getFechaInicioInscripciones() != null) {
                ps.setDate(5, Date.valueOf(periodo.getFechaInicioInscripciones()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            
            if (periodo.getFechaFinInscripciones() != null) {
                ps.setDate(6, Date.valueOf(periodo.getFechaFinInscripciones()));
            } else {
                ps.setNull(6, Types.DATE);
            }
            
            if (periodo.getFechaInicioClases() != null) {
                ps.setDate(7, Date.valueOf(periodo.getFechaInicioClases()));
            } else {
                ps.setNull(7, Types.DATE);
            }
            
            if (periodo.getFechaFinClases() != null) {
                ps.setDate(8, Date.valueOf(periodo.getFechaFinClases()));
            } else {
                ps.setNull(8, Types.DATE);
            }
            
            ps.setString(9, periodo.getDescripcion());
            ps.setString(10, periodo.getEstado().name());
            ps.setInt(11, periodo.getId());
            
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(PeriodoAcademico periodo) throws SQLException {
        String sql = "DELETE FROM periodos_academicos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, periodo.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<PeriodoAcademico> listar() throws SQLException {
        List<PeriodoAcademico> periodos = new ArrayList<PeriodoAcademico>();
        String sql = "SELECT * FROM periodos_academicos ORDER BY anio DESC, semestre DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                periodos.add(mapearPeriodo(rs));
            }
        }
        return periodos;
    }
    
    /**
     * Obtiene el período activo actual.
     */
    public PeriodoAcademico obtenerPeriodoActivo() throws SQLException {
        String sql = "SELECT * FROM periodos_academicos WHERE activo = TRUE LIMIT 1";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return mapearPeriodo(rs);
            }
        }
        return null;
    }
    
    /**
     * Activa un período y desactiva todos los demás.
     */
    public boolean activarPeriodo(Integer periodoId) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionDB.getConexion();
            conn.setAutoCommit(false);
            
            // Desactivar todos los períodos
            String sqlDesactivar = "UPDATE periodos_academicos SET activo = FALSE WHERE id > 0";
            try (PreparedStatement ps = conn.prepareStatement(sqlDesactivar)) {
                ps.executeUpdate();
            }
            
            // Activar el período seleccionado
            String sqlActivar = "UPDATE periodos_academicos SET activo = TRUE WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlActivar)) {
                ps.setInt(1, periodoId);
                ps.executeUpdate();
            }
            
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    
    /**
     * Verifica si un período tiene cursos ofrecidos.
     */
    public boolean tieneCursosOfrecidos(Integer periodoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE periodo_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, periodoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Lista períodos por año.
     */
    public List<PeriodoAcademico> listarPorAnio(Integer anio) throws SQLException {
        List<PeriodoAcademico> periodos = new ArrayList<PeriodoAcademico>();
        String sql = "SELECT * FROM periodos_academicos WHERE anio = ? ORDER BY semestre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, anio);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    periodos.add(mapearPeriodo(rs));
                }
            }
        }
        return periodos;
    }
    
    /**
     * Mapea un ResultSet a un objeto PeriodoAcademico.
     */
    private PeriodoAcademico mapearPeriodo(ResultSet rs) throws SQLException {
        PeriodoAcademico periodo = new PeriodoAcademico();
        periodo.setId(rs.getInt("id"));
        periodo.setNombre(rs.getString("nombre"));
        periodo.setAnio(rs.getInt("anio"));
        periodo.setSemestre(rs.getString("semestre"));
        periodo.setActivo(rs.getBoolean("activo"));
        
        Date fechaInicioInsc = rs.getDate("fecha_inicio_inscripciones");
        if (fechaInicioInsc != null) {
            periodo.setFechaInicioInscripciones(fechaInicioInsc.toLocalDate());
        }
        
        Date fechaFinInsc = rs.getDate("fecha_fin_inscripciones");
        if (fechaFinInsc != null) {
            periodo.setFechaFinInscripciones(fechaFinInsc.toLocalDate());
        }
        
        Date fechaInicioClases = rs.getDate("fecha_inicio_clases");
        if (fechaInicioClases != null) {
            periodo.setFechaInicioClases(fechaInicioClases.toLocalDate());
        }
        
        Date fechaFinClases = rs.getDate("fecha_fin_clases");
        if (fechaFinClases != null) {
            periodo.setFechaFinClases(fechaFinClases.toLocalDate());
        }
        
        periodo.setDescripcion(rs.getString("descripcion"));
        
        String estadoStr = rs.getString("estado");
        if (estadoStr != null) {
            periodo.setEstado(EstadoPeriodo.valueOf(estadoStr));
        }
        
        return periodo;
    }
}

