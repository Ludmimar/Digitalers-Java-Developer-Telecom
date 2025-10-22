package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.entidades.Correlatividad;
import com.educacionit.sistemaeducativo.entidades.Correlatividad.TipoCorrelatividad;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar correlatividades entre cursos.
 * 
 * @author Ludmila Martos
 */
public class CorrelatividadDAOImpl {
    
    /**
     * Obtiene todas las correlatividades (cursos prerequisito) de un curso.
     */
    public List<Correlatividad> obtenerCorrelativas(Integer cursoId) throws SQLException {
        List<Correlatividad> correlatividades = new ArrayList<Correlatividad>();
        String sql = "SELECT cor.*, c.nombre as correlativa_nombre " +
                     "FROM correlatividades cor " +
                     "INNER JOIN cursos c ON cor.correlativa_id = c.id " +
                     "WHERE cor.curso_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Correlatividad corr = new Correlatividad();
                    corr.setId(rs.getInt("id"));
                    corr.setCursoId(rs.getInt("curso_id"));
                    corr.setCorrelativaId(rs.getInt("correlativa_id"));
                    corr.setTipo(TipoCorrelatividad.valueOf(rs.getString("tipo")));
                    corr.setCorrelativaNombre(rs.getString("correlativa_nombre"));
                    correlatividades.add(corr);
                }
            }
        }
        return correlatividades;
    }
    
    /**
     * Obtiene todos los cursos que requieren este curso como correlativa.
     */
    public List<Correlatividad> obtenerDependientes(Integer cursoId) throws SQLException {
        List<Correlatividad> dependientes = new ArrayList<Correlatividad>();
        String sql = "SELECT cor.*, c.nombre as curso_nombre " +
                     "FROM correlatividades cor " +
                     "INNER JOIN cursos c ON cor.curso_id = c.id " +
                     "WHERE cor.correlativa_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Correlatividad corr = new Correlatividad();
                    corr.setId(rs.getInt("id"));
                    corr.setCursoId(rs.getInt("curso_id"));
                    corr.setCorrelativaId(rs.getInt("correlativa_id"));
                    corr.setTipo(TipoCorrelatividad.valueOf(rs.getString("tipo")));
                    corr.setCursoNombre(rs.getString("curso_nombre"));
                    dependientes.add(corr);
                }
            }
        }
        return dependientes;
    }
    
    /**
     * Verifica si un estudiante cumple con las correlatividades de un curso.
     * Retorna true si cumple TODAS las correlatividades.
     */
    public boolean cumpleCorrelativas(Integer estudianteId, Integer cursoId) throws SQLException {
        List<Correlatividad> correlativas = obtenerCorrelativas(cursoId);
        
        if (correlativas.isEmpty()) {
            return true; // No tiene correlativas, cumple automáticamente
        }
        
        for (Correlatividad corr : correlativas) {
            if (corr.getTipo() == TipoCorrelatividad.APROBADA) {
                // Verificar que aprobó la correlativa
                if (!aproboCorrelativa(estudianteId, corr.getCorrelativaId())) {
                    return false;
                }
            } else if (corr.getTipo() == TipoCorrelatividad.REGULAR) {
                // Solo verificar que la cursó (cualquier estado menos PENDIENTE)
                if (!cursoCorrelativa(estudianteId, corr.getCorrelativaId())) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Obtiene las correlatividades que NO cumple un estudiante para un curso.
     */
    public List<Correlatividad> obtenerCorrelativasFaltantes(Integer estudianteId, Integer cursoId) throws SQLException {
        List<Correlatividad> faltantes = new ArrayList<Correlatividad>();
        List<Correlatividad> correlativas = obtenerCorrelativas(cursoId);
        
        for (Correlatividad corr : correlativas) {
            boolean cumple = false;
            
            if (corr.getTipo() == TipoCorrelatividad.APROBADA) {
                cumple = aproboCorrelativa(estudianteId, corr.getCorrelativaId());
            } else if (corr.getTipo() == TipoCorrelatividad.REGULAR) {
                cumple = cursoCorrelativa(estudianteId, corr.getCorrelativaId());
            }
            
            if (!cumple) {
                faltantes.add(corr);
            }
        }
        
        return faltantes;
    }
    
    /**
     * Verifica si un estudiante aprobó un curso específico.
     */
    private boolean aproboCorrelativa(Integer estudianteId, Integer cursoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM inscripciones i " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "WHERE i.estudiante_id = ? AND co.curso_id = ? " +
                     "AND i.estado = 'APROBADO'";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            ps.setInt(2, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si un estudiante cursó un curso específico (cualquier estado excepto PENDIENTE).
     */
    private boolean cursoCorrelativa(Integer estudianteId, Integer cursoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM inscripciones i " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "WHERE i.estudiante_id = ? AND co.curso_id = ? " +
                     "AND i.estado != 'PENDIENTE'";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            ps.setInt(2, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Inserta una nueva correlatividad.
     */
    public boolean insertar(Correlatividad correlatividad) throws SQLException {
        String sql = "INSERT INTO correlatividades (curso_id, correlativa_id, tipo) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, correlatividad.getCursoId());
            ps.setInt(2, correlatividad.getCorrelativaId());
            ps.setString(3, correlatividad.getTipo().name());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        correlatividad.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Elimina una correlatividad.
     */
    public boolean eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM correlatividades WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}


