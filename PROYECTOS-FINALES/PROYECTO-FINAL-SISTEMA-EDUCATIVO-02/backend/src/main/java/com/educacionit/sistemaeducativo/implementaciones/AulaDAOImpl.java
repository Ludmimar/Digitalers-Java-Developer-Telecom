package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.dao.DAO;
import com.educacionit.sistemaeducativo.entidades.Aula;
import com.educacionit.sistemaeducativo.entidades.Aula.EstadoAula;
import com.educacionit.sistemaeducativo.entidades.Aula.TipoAula;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón DAO para la entidad Aula.
 * 
 * @author Ludmila Martos
 */
public class AulaDAOImpl implements DAO<Integer, Aula> {

    @Override
    public Aula buscarPorID(Integer id) throws SQLException {
        String sql = "SELECT * FROM aulas WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearAula(rs);
                }
            }
        }
        return null;
    }

    @Override
    public boolean insertar(Aula aula) throws SQLException {
        // Validar código único
        if (buscarPorCodigo(aula.getCodigo()) != null) {
            throw new SQLException("Ya existe un aula con el código: " + aula.getCodigo());
        }
        
        String sql = "INSERT INTO aulas (codigo, nombre, capacidad, edificio, piso, tipo, equipamiento, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, aula.getCodigo());
            ps.setString(2, aula.getNombre());
            ps.setInt(3, aula.getCapacidad());
            ps.setString(4, aula.getEdificio());
            
            if (aula.getPiso() != null) {
                ps.setInt(5, aula.getPiso());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            
            ps.setString(6, aula.getTipo().name());
            ps.setString(7, aula.getEquipamiento());
            ps.setString(8, aula.getEstado().name());
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        aula.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizar(Aula aula) throws SQLException {
        String sql = "UPDATE aulas SET nombre = ?, capacidad = ?, edificio = ?, piso = ?, " +
                     "tipo = ?, equipamiento = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, aula.getNombre());
            ps.setInt(2, aula.getCapacidad());
            ps.setString(3, aula.getEdificio());
            
            if (aula.getPiso() != null) {
                ps.setInt(4, aula.getPiso());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            
            ps.setString(5, aula.getTipo().name());
            ps.setString(6, aula.getEquipamiento());
            ps.setString(7, aula.getEstado().name());
            ps.setInt(8, aula.getId());
            
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(Aula aula) throws SQLException {
        String sql = "DELETE FROM aulas WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, aula.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Aula> listar() throws SQLException {
        List<Aula> aulas = new ArrayList<Aula>();
        String sql = "SELECT * FROM aulas ORDER BY codigo";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                aulas.add(mapearAula(rs));
            }
        }
        return aulas;
    }
    
    /**
     * Busca un aula por su código.
     */
    public Aula buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM aulas WHERE codigo = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearAula(rs);
                }
            }
        }
        return null;
    }
    
    /**
     * Lista aulas por estado.
     */
    public List<Aula> listarPorEstado(EstadoAula estado) throws SQLException {
        List<Aula> aulas = new ArrayList<Aula>();
        String sql = "SELECT * FROM aulas WHERE estado = ? ORDER BY codigo";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, estado.name());
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aulas.add(mapearAula(rs));
                }
            }
        }
        return aulas;
    }
    
    /**
     * Lista aulas por tipo.
     */
    public List<Aula> listarPorTipo(TipoAula tipo) throws SQLException {
        List<Aula> aulas = new ArrayList<Aula>();
        String sql = "SELECT * FROM aulas WHERE tipo = ? ORDER BY codigo";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tipo.name());
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aulas.add(mapearAula(rs));
                }
            }
        }
        return aulas;
    }
    
    /**
     * Lista aulas disponibles con capacidad mínima.
     */
    public List<Aula> listarDisponibles(Integer capacidadMinima) throws SQLException {
        List<Aula> aulas = new ArrayList<Aula>();
        String sql = "SELECT * FROM aulas WHERE estado = 'DISPONIBLE' AND capacidad >= ? ORDER BY capacidad";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, capacidadMinima);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aulas.add(mapearAula(rs));
                }
            }
        }
        return aulas;
    }
    
    /**
     * Verifica si un aula tiene cursos asignados.
     */
    public boolean tieneCursosAsignados(Integer aulaId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE aula_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, aulaId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Cuenta la cantidad de cursos asignados a un aula.
     */
    public int contarCursosAsignados(Integer aulaId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE aula_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, aulaId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
    
    /**
     * Verifica si existe un aula con el código dado.
     */
    public boolean existeCodigo(String codigo) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM aulas WHERE codigo = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si existe un aula con el código dado (excluyendo un ID).
     */
    public boolean existeCodigo(String codigo, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM aulas WHERE codigo = ? AND id != ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            ps.setInt(2, excludeId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Mapea un ResultSet a un objeto Aula.
     */
    private Aula mapearAula(ResultSet rs) throws SQLException {
        Aula aula = new Aula();
        aula.setId(rs.getInt("id"));
        aula.setCodigo(rs.getString("codigo"));
        aula.setNombre(rs.getString("nombre"));
        aula.setCapacidad(rs.getInt("capacidad"));
        aula.setEdificio(rs.getString("edificio"));
        
        int piso = rs.getInt("piso");
        if (!rs.wasNull()) {
            aula.setPiso(piso);
        }
        
        String tipoStr = rs.getString("tipo");
        if (tipoStr != null) {
            aula.setTipo(TipoAula.valueOf(tipoStr));
        }
        
        aula.setEquipamiento(rs.getString("equipamiento"));
        
        String estadoStr = rs.getString("estado");
        if (estadoStr != null) {
            aula.setEstado(EstadoAula.valueOf(estadoStr));
        }
        
        Timestamp createdTs = rs.getTimestamp("created_at");
        if (createdTs != null) {
            aula.setCreatedAt(createdTs.toLocalDateTime());
        }
        
        Timestamp updatedTs = rs.getTimestamp("updated_at");
        if (updatedTs != null) {
            aula.setUpdatedAt(updatedTs.toLocalDateTime());
        }
        
        return aula;
    }
}


