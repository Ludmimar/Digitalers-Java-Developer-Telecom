package com.educacionit.sistemaeducativo.implementaciones;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.sistemaeducativo.dao.DAO;
import com.educacionit.sistemaeducativo.entidades.Profesor;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

/**
 * Implementación del DAO para la entidad Profesor.
 * Gestiona todas las operaciones CRUD en la base de datos.
 * 
 * PROPÓSITO:
 * - Implementa el patrón DAO para gestión de profesores
 * - Maneja transacciones complejas con múltiples tablas
 * - Implementa validaciones de unicidad para códigos de profesor
 * - Demuestra inserción en tablas relacionadas (personas + profesores)
 * - Gestiona información académica y laboral de profesores
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Patrón DAO: encapsulación de acceso a datos complejos
 * - Transacciones: operaciones atómicas con rollback manual
 * - Validaciones de negocio: unicidad de códigos de profesor
 * - Manejo de recursos: gestión manual de conexiones y statements
 * - Mapeo objeto-relacional: conversión de ResultSet a objetos
 * - PreparedStatement: consultas parametrizadas seguras
 * 
 * @author Ludmila Martos
 */
public class ProfesorDAOImpl implements DAO<Integer, Profesor> {

    // IMPLEMENTACIÓN DE MÉTODO DE INSERCIÓN CON TRANSACCIONES
    /**
     * Inserta un nuevo profesor con validaciones y transacciones.
     * Demuestra operaciones atómicas con múltiples tablas relacionadas.
     */
    @Override
    public boolean insertar(Profesor profesor) {
        // VALIDACIONES PREVIAS
        // Demuestra validaciones de negocio antes de persistir
        try {
            if (existeCodigoProfesor(profesor.getCodigoProfesor())) {
                throw new SQLException("Ya existe un profesor con el código: " + profesor.getCodigoProfesor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        // CONSULTAS SQL PARA INSERCIÓN EN MÚLTIPLES TABLAS
        // Primero insertar en tabla personas (tabla padre)
        String sqlPersona = "INSERT INTO personas (tipo_documento, numero_documento, nombre, apellido, fecha_nacimiento, email, telefono, direccion) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        // Luego insertar en tabla profesores (tabla hija)
        String sqlProfesor = "INSERT INTO profesores (persona_id, codigo_profesor, fecha_contratacion, sueldo, especialidad, grado_academico, estado_laboral) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // VARIABLES PARA GESTIÓN MANUAL DE RECURSOS
        // Demuestra gestión manual de conexiones y statements
        Connection conn = null;
        PreparedStatement psPersona = null;
        PreparedStatement psProfesor = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.getConexion();
            conn.setAutoCommit(false);  // Iniciar transacción manual
            
            // PASO 1: Insertar en tabla personas
            psPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);
            psPersona.setString(1, profesor.getTipoDocumento().toString());
            psPersona.setString(2, profesor.getNumeroDocumento());
            psPersona.setString(3, profesor.getNombre());
            psPersona.setString(4, profesor.getApellido());
            psPersona.setDate(5, Date.valueOf(profesor.getFechaNacimiento()));
            psPersona.setString(6, profesor.getEmail());
            psPersona.setString(7, profesor.getTelefono());
            psPersona.setString(8, profesor.getDireccion());
            
            psPersona.executeUpdate();
            
            // Obtener el ID generado para la persona
            rs = psPersona.getGeneratedKeys();
            if (rs.next()) {
                int personaId = rs.getInt(1);
                
                // Insertar en tabla profesores
                psProfesor = conn.prepareStatement(sqlProfesor);
                psProfesor.setInt(1, personaId);
                psProfesor.setString(2, profesor.getCodigoProfesor());
                psProfesor.setDate(3, Date.valueOf(profesor.getFechaContratacion()));
                psProfesor.setDouble(4, profesor.getSueldo());
                psProfesor.setString(5, profesor.getEspecialidad());
                psProfesor.setString(6, profesor.getGradoAcademico());
                psProfesor.setString(7, profesor.getEstadoLaboral());
                
                psProfesor.executeUpdate();
                
                conn.commit();
                return true;
            }
            
            conn.rollback();
            return false;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (psPersona != null) psPersona.close();
                if (psProfesor != null) psProfesor.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean actualizar(Profesor profesor) {
        String sqlPersona = "UPDATE personas SET nombre = ?, apellido = ?, email = ?, telefono = ?, direccion = ? WHERE id = ?";
        String sqlProfesor = "UPDATE profesores SET sueldo = ?, especialidad = ?, grado_academico = ?, estado_laboral = ? WHERE persona_id = ?";
        
        Connection conn = null;
        PreparedStatement psPersona = null;
        PreparedStatement psProfesor = null;
        
        try {
            conn = ConexionDB.getConexion();
            conn.setAutoCommit(false);
            
            // Actualizar tabla personas
            psPersona = conn.prepareStatement(sqlPersona);
            psPersona.setString(1, profesor.getNombre());
            psPersona.setString(2, profesor.getApellido());
            psPersona.setString(3, profesor.getEmail());
            psPersona.setString(4, profesor.getTelefono());
            psPersona.setString(5, profesor.getDireccion());
            psPersona.setInt(6, profesor.getPersonaId());
            
            psPersona.executeUpdate();
            
            // Actualizar tabla profesores
            psProfesor = conn.prepareStatement(sqlProfesor);
            psProfesor.setDouble(1, profesor.getSueldo());
            psProfesor.setString(2, profesor.getEspecialidad());
            psProfesor.setString(3, profesor.getGradoAcademico());
            psProfesor.setString(4, profesor.getEstadoLaboral());
            psProfesor.setInt(5, profesor.getPersonaId());
            
            psProfesor.executeUpdate();
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (psPersona != null) psPersona.close();
                if (psProfesor != null) psProfesor.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean eliminar(Profesor profesor) {
        // El CASCADE en la BD se encargará de eliminar el registro en profesores
        String sql = "DELETE FROM personas WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, profesor.getPersonaId());
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Profesor buscarPorID(Integer id) {
        String sql = "SELECT p.id as persona_id, p.tipo_documento, p.numero_documento, p.nombre, p.apellido, " +
                    "p.fecha_nacimiento, p.email, p.telefono, p.direccion, " +
                    "pr.id as profesor_id, pr.codigo_profesor, pr.fecha_contratacion, " +
                    "pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral " +
                    "FROM personas p " +
                    "INNER JOIN profesores pr ON p.id = pr.persona_id " +
                    "WHERE pr.id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return mapearProfesor(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Busca profesores por estado laboral.
     * 
     * @param estado Estado laboral (ACTIVO, INACTIVO, LICENCIA, JUBILADO)
     * @return Lista de profesores con ese estado
     */
    public List<Profesor> buscarPorEstado(String estado) {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT p.id as persona_id, p.tipo_documento, p.numero_documento, p.nombre, p.apellido, " +
                    "p.fecha_nacimiento, p.email, p.telefono, p.direccion, " +
                    "pr.id as profesor_id, pr.codigo_profesor, pr.fecha_contratacion, " +
                    "pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral " +
                    "FROM personas p " +
                    "INNER JOIN profesores pr ON p.id = pr.persona_id " +
                    "WHERE pr.estado_laboral = ? " +
                    "ORDER BY p.apellido, p.nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                profesores.add(mapearProfesor(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profesores;
    }
    
    /**
     * Busca profesores por nombre o apellido (búsqueda parcial).
     * 
     * @param termino Término de búsqueda
     * @return Lista de profesores que coinciden
     */
    public List<Profesor> buscarPorNombre(String termino) {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT p.id as persona_id, p.tipo_documento, p.numero_documento, p.nombre, p.apellido, " +
                    "p.fecha_nacimiento, p.email, p.telefono, p.direccion, " +
                    "pr.id as profesor_id, pr.codigo_profesor, pr.fecha_contratacion, " +
                    "pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral " +
                    "FROM personas p " +
                    "INNER JOIN profesores pr ON p.id = pr.persona_id " +
                    "WHERE p.nombre LIKE ? OR p.apellido LIKE ? OR CONCAT(p.nombre, ' ', p.apellido) LIKE ? " +
                    "ORDER BY p.apellido, p.nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String patron = "%" + termino + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            ps.setString(3, patron);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                profesores.add(mapearProfesor(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profesores;
    }

    @Override
    public List<Profesor> listar() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT p.id as persona_id, p.tipo_documento, p.numero_documento, p.nombre, p.apellido, " +
                    "p.fecha_nacimiento, p.email, p.telefono, p.direccion, " +
                    "pr.id as profesor_id, pr.codigo_profesor, pr.fecha_contratacion, " +
                    "pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral " +
                    "FROM personas p " +
                    "INNER JOIN profesores pr ON p.id = pr.persona_id " +
                    "ORDER BY p.apellido, p.nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                profesores.add(mapearProfesor(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profesores;
    }

    /**
     * Busca un profesor por su código único.
     */
    public Profesor buscarPorCodigo(String codigo) {
        String sql = "SELECT p.id as persona_id, p.tipo_documento, p.numero_documento, p.nombre, p.apellido, " +
                    "p.fecha_nacimiento, p.email, p.telefono, p.direccion, " +
                    "pr.id as profesor_id, pr.codigo_profesor, pr.fecha_contratacion, " +
                    "pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral " +
                    "FROM personas p " +
                    "INNER JOIN profesores pr ON p.id = pr.persona_id " +
                    "WHERE pr.codigo_profesor = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return mapearProfesor(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Lista profesores por especialidad.
     */
    public List<Profesor> listarPorEspecialidad(String especialidad) {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT p.id as persona_id, p.tipo_documento, p.numero_documento, p.nombre, p.apellido, " +
                    "p.fecha_nacimiento, p.email, p.telefono, p.direccion, " +
                    "pr.id as profesor_id, pr.codigo_profesor, pr.fecha_contratacion, " +
                    "pr.sueldo, pr.especialidad, pr.grado_academico, pr.estado_laboral " +
                    "FROM personas p " +
                    "INNER JOIN profesores pr ON p.id = pr.persona_id " +
                    "WHERE pr.especialidad = ? " +
                    "ORDER BY p.apellido, p.nombre";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, especialidad);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                profesores.add(mapearProfesor(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return profesores;
    }

    /**
     * Mapea un ResultSet a un objeto Profesor.
     */
    private Profesor mapearProfesor(ResultSet rs) throws SQLException {
        Profesor profesor = new Profesor();
        
        // Datos de Persona
        profesor.setId(rs.getInt("profesor_id")); // ID de la tabla profesores
        profesor.setPersonaId(rs.getInt("persona_id")); // ID de la tabla personas
        profesor.setTipoDocumento(TipoDocumento.valueOf(rs.getString("tipo_documento")));
        profesor.setNumeroDocumento(rs.getString("numero_documento"));
        profesor.setNombre(rs.getString("nombre"));
        profesor.setApellido(rs.getString("apellido"));
        profesor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        profesor.setEmail(rs.getString("email"));
        profesor.setTelefono(rs.getString("telefono"));
        profesor.setDireccion(rs.getString("direccion"));
        
        // Datos de Profesor
        profesor.setCodigoProfesor(rs.getString("codigo_profesor"));
        profesor.setFechaContratacion(rs.getDate("fecha_contratacion").toLocalDate());
        profesor.setSueldo(rs.getDouble("sueldo"));
        profesor.setEspecialidad(rs.getString("especialidad"));
        profesor.setGradoAcademico(rs.getString("grado_academico"));
        profesor.setEstadoLaboral(rs.getString("estado_laboral"));
        
        return profesor;
    }

    /**
     * Verifica si existe un profesor con el código dado.
     */
    public boolean existeCodigoProfesor(String codigoProfesor) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM profesores WHERE codigo_profesor = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigoProfesor);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si existe un profesor con el código dado (excluyendo un ID específico para edición).
     */
    public boolean existeCodigoProfesor(String codigoProfesor, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM profesores WHERE codigo_profesor = ?";
        if (excludeId != null) {
            sql += " AND id != ?";
        }
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigoProfesor);
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
     * Verifica si un profesor tiene cursos asignados.
     */
    public boolean tieneCursosAsignados(Integer profesorId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE profesor_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, profesorId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Cuenta la cantidad de cursos asignados a un profesor.
     */
    public int contarCursosAsignados(Integer profesorId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM cursos_ofrecidos WHERE profesor_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, profesorId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
}

