package com.educacionit.sistemaeducativo.implementaciones;

import com.educacionit.sistemaeducativo.dao.DAO;
import com.educacionit.sistemaeducativo.entidades.Curso;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón DAO para la entidad Curso.
 * Gestiona las operaciones CRUD en la base de datos MySQL.
 * 
 * PROPÓSITO:
 * - Implementa el patrón DAO para gestión de cursos académicos
 * - Maneja operaciones CRUD básicas para la entidad Curso
 * - Implementa validaciones de unicidad para códigos de curso
 * - Demuestra consultas SQL estándar con PreparedStatement
 * - Gestiona estados de cursos (activo/inactivo)
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Patrón DAO: encapsulación de acceso a datos
 * - Validaciones de negocio: unicidad de códigos
 * - Consultas SQL básicas: SELECT, INSERT, UPDATE, DELETE
 * - Mapeo objeto-relacional: ResultSet a objetos Java
 * - Manejo de recursos: try-with-resources
 * - PreparedStatement: consultas parametrizadas seguras
 * 
 * @author Ludmila Martos
 */
public class CursoDAOImpl implements DAO<Integer, Curso> {

    // IMPLEMENTACIÓN DE MÉTODO DE BÚSQUEDA POR ID
    /**
     * Busca un curso por su ID único.
     * Demuestra consulta básica con mapeo de resultados.
     */
    @Override
    public Curso buscarPorID(Integer id) throws SQLException {
        String sql = "SELECT * FROM cursos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCurso(rs);  // Convertir ResultSet a objeto Curso
                }
            }
        }
        return null;
    }

    // IMPLEMENTACIÓN DE MÉTODO DE INSERCIÓN CON VALIDACIONES
    /**
     * Inserta un nuevo curso con validación de unicidad.
     * Demuestra validaciones de negocio antes de persistir.
     */
    @Override
    public boolean insertar(Curso curso) throws SQLException {
        // VALIDACIÓN DE UNICIDAD
        // Demuestra validación de reglas de negocio críticas
        if (buscarPorCodigo(curso.getCodigoCurso()) != null) {
            throw new SQLException("Ya existe un curso con el código: " + curso.getCodigoCurso());
        }
        
        // CONSULTA SQL DE INSERCIÓN
        String sql = "INSERT INTO cursos (codigo_curso, nombre, descripcion, creditos, " +
                     "horas_semanales, cupo_maximo, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Establecer parámetros de la consulta
            ps.setString(1, curso.getCodigoCurso());
            ps.setString(2, curso.getNombre());
            ps.setString(3, curso.getDescripcion());
            ps.setInt(4, curso.getCreditos());
            ps.setInt(5, curso.getHorasSemanales());
            ps.setInt(6, curso.getCupoMaximo());
            ps.setString(7, curso.getEstado());
            
            int filasAfectadas = ps.executeUpdate();
            
            // Obtener el ID generado
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        curso.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Actualiza un curso existente en la base de datos.
     */
    @Override
    public boolean actualizar(Curso curso) throws SQLException {
        String sql = "UPDATE cursos SET nombre = ?, descripcion = ?, creditos = ?, " +
                     "horas_semanales = ?, cupo_maximo = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, curso.getNombre());
            ps.setString(2, curso.getDescripcion());
            ps.setInt(3, curso.getCreditos());
            ps.setInt(4, curso.getHorasSemanales());
            ps.setInt(5, curso.getCupoMaximo());
            ps.setString(6, curso.getEstado());
            ps.setInt(7, curso.getId());
            
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un curso de la base de datos.
     */
    @Override
    public boolean eliminar(Curso curso) throws SQLException {
        String sql = "DELETE FROM cursos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, curso.getId());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Lista todos los cursos de la base de datos.
     */
    @Override
    public List<Curso> listar() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos ORDER BY nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                cursos.add(mapearCurso(rs));
            }
        }
        return cursos;
    }

    /**
     * Busca un curso por su código.
     */
    public Curso buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM cursos WHERE codigo_curso = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCurso(rs);
                }
            }
        }
        return null;
    }

    /**
     * Lista cursos por estado (ACTIVO, INACTIVO, FINALIZADO).
     */
    public List<Curso> listarPorEstado(String estado) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE estado = ? ORDER BY nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, estado);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cursos.add(mapearCurso(rs));
                }
            }
        }
        return cursos;
    }

    /**
     * Busca cursos por nombre (búsqueda parcial).
     */
    public List<Curso> buscarPorNombre(String nombre) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE nombre LIKE ? ORDER BY nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + nombre + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cursos.add(mapearCurso(rs));
                }
            }
        }
        return cursos;
    }

    /**
     * Obtiene la cantidad de estudiantes inscritos en un curso.
     * Se cuentan todas las inscripciones a través de cursos_ofrecidos.
     */
    public int contarEstudiantesInscritos(Integer cursoId) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT i.estudiante_id) as total " +
                     "FROM inscripciones i " +
                     "INNER JOIN cursos_ofrecidos co ON i.curso_ofrecido_id = co.id " +
                     "WHERE co.curso_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }

    /**
     * Verifica si un curso tiene cupos disponibles.
     */
    public boolean tieneCuposDisponibles(Integer cursoId) throws SQLException {
        Curso curso = buscarPorID(cursoId);
        if (curso == null) return false;
        
        int inscritos = contarEstudiantesInscritos(cursoId);
        return inscritos < curso.getCupoMaximo();
    }

    /**
     * Verifica si existe un curso con el código dado.
     */
    public boolean existeCodigoCurso(String codigoCurso) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos WHERE codigo_curso = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigoCurso);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si existe un curso con el código dado (excluyendo un ID específico para edición).
     */
    public boolean existeCodigoCurso(String codigoCurso, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos WHERE codigo_curso = ?";
        if (excludeId != null) {
            sql += " AND id != ?";
        }
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigoCurso);
            if (excludeId != null) {
                ps.setInt(2, excludeId);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si un curso tiene períodos asignados (cursos_ofrecidos).
     * Retorna true si tiene períodos asignados y NO se puede eliminar.
     */
    public boolean tienePeriodosAsignados(Integer cursoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE curso_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Obtiene la cantidad de períodos asignados para un curso.
     */
    public int contarPeriodosAsignados(Integer cursoId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE curso_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, cursoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }

    /**
     * Mapea un ResultSet a un objeto Curso.
     */
    private Curso mapearCurso(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setId(rs.getInt("id"));
        curso.setCodigoCurso(rs.getString("codigo_curso"));
        curso.setNombre(rs.getString("nombre"));
        curso.setDescripcion(rs.getString("descripcion"));
        curso.setCreditos(rs.getInt("creditos"));
        curso.setHorasSemanales(rs.getInt("horas_semanales"));
        curso.setCupoMaximo(rs.getInt("cupo_maximo"));
        curso.setEstado(rs.getString("estado"));
        return curso;
    }
}

