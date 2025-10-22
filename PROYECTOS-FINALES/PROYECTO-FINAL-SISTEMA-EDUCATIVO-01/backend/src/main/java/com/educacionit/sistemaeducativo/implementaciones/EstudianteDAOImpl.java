package com.educacionit.sistemaeducativo.implementaciones;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.sistemaeducativo.dao.DAO;
import com.educacionit.sistemaeducativo.entidades.Estudiante;
import com.educacionit.sistemaeducativo.enumerados.EstadoAcademico;
import com.educacionit.sistemaeducativo.enumerados.TipoDocumento;
import com.educacionit.sistemaeducativo.utilidades.ConexionDB;

/**
 * Implementación del patrón DAO para la entidad Estudiante.
 * Maneja todas las operaciones CRUD con la base de datos.
 * 
 * @author Ludmila Martos
 */
public class EstudianteDAOImpl implements DAO<Integer, Estudiante> {
    
    private PreparedStatement psInsertar;
    private PreparedStatement psBuscar;
    private PreparedStatement psActualizar;
    private PreparedStatement psEliminar;
    private PreparedStatement psListar;

    @Override
    public Estudiante buscarPorID(Integer id) throws SQLException {
        Estudiante estudiante = null;
        String sql = "SELECT e.*, p.* FROM estudiantes e " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "WHERE e.id = ?";

        try (Connection conexion = ConexionDB.getConexion()) {
            if (psBuscar == null || psBuscar.isClosed()) {
                psBuscar = conexion.prepareStatement(sql);
            }
            
            psBuscar.setInt(1, id);
            ResultSet rs = psBuscar.executeQuery();

            if (rs.next()) {
                estudiante = mapearEstudiante(rs);
            }
        }
        return estudiante;
    }

    @Override
    public boolean insertar(Estudiante estudiante) throws SQLException {
        // Validaciones antes de insertar
        if (existeMatricula(estudiante.getMatricula())) {
            throw new SQLException("Ya existe un estudiante con la matrícula: " + estudiante.getMatricula());
        }
        
        if (existeEmail(estudiante.getEmail())) {
            throw new SQLException("Ya existe un estudiante con el email: " + estudiante.getEmail());
        }
        
        // Primero insertar en tabla personas
        String sqlPersona = "INSERT INTO personas (tipo_documento, numero_documento, nombre, " +
                           "apellido, fecha_nacimiento, email, telefono, direccion) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        String sqlEstudiante = "INSERT INTO estudiantes (persona_id, matricula, " +
                              "fecha_ingreso, estado_academico) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConexion()) {
            conexion.setAutoCommit(false);  // Iniciar transacción
            
            try {
                // Insertar persona
                PreparedStatement psPersona = conexion.prepareStatement(sqlPersona, 
                                                Statement.RETURN_GENERATED_KEYS);
                psPersona.setString(1, estudiante.getTipoDocumento().name());
                psPersona.setString(2, estudiante.getNumeroDocumento());
                psPersona.setString(3, estudiante.getNombre());
                psPersona.setString(4, estudiante.getApellido());
                psPersona.setDate(5, Date.valueOf(estudiante.getFechaNacimiento()));
                psPersona.setString(6, estudiante.getEmail());
                psPersona.setString(7, estudiante.getTelefono());
                psPersona.setString(8, estudiante.getDireccion());
                
                psPersona.executeUpdate();
                
                // Obtener ID generado
                ResultSet rs = psPersona.getGeneratedKeys();
                if (rs.next()) {
                    int personaId = rs.getInt(1);
                    estudiante.setPersonaId(personaId);
                    
                    // Insertar estudiante
                    PreparedStatement psEst = conexion.prepareStatement(sqlEstudiante,
                                                Statement.RETURN_GENERATED_KEYS);
                    psEst.setInt(1, personaId);
                    psEst.setString(2, estudiante.getMatricula());
                    psEst.setDate(3, Date.valueOf(estudiante.getFechaIngreso()));
                    psEst.setString(4, estudiante.getEstadoAcademico().name());
                    
                    psEst.executeUpdate();
                    
                    // Obtener ID del estudiante
                    ResultSet rsEst = psEst.getGeneratedKeys();
                    if (rsEst.next()) {
                        estudiante.setId(rsEst.getInt(1));
                    }
                }
                
                conexion.commit();  // Confirmar transacción
                System.out.println("✅ Estudiante insertado correctamente: " + estudiante.getMatricula());
                return true;
                
            } catch (SQLException e) {
                conexion.rollback();  // Revertir transacción
                System.err.println("❌ Error al insertar estudiante");
                throw e;
            }
        }
    }

    @Override
    public boolean actualizar(Estudiante estudiante) throws SQLException {
        String sqlPersona = "UPDATE personas SET nombre=?, apellido=?, email=?, " +
                           "telefono=?, direccion=? WHERE id=?";
        
        String sqlEstudiante = "UPDATE estudiantes SET matricula=?, promedio_general=?, " +
                              "creditos_cursados=?, estado_academico=? WHERE persona_id=?";

        try (Connection conexion = ConexionDB.getConexion()) {
            conexion.setAutoCommit(false);
            
            try {
                // Actualizar persona
                PreparedStatement psPersona = conexion.prepareStatement(sqlPersona);
                psPersona.setString(1, estudiante.getNombre());
                psPersona.setString(2, estudiante.getApellido());
                psPersona.setString(3, estudiante.getEmail());
                psPersona.setString(4, estudiante.getTelefono());
                psPersona.setString(5, estudiante.getDireccion());
                psPersona.setInt(6, estudiante.getPersonaId());
                psPersona.executeUpdate();
                
                // Actualizar estudiante
                PreparedStatement psEst = conexion.prepareStatement(sqlEstudiante);
                psEst.setString(1, estudiante.getMatricula());
                psEst.setDouble(2, estudiante.getPromedioGeneral());
                psEst.setInt(3, estudiante.getCreditosCursados());
                psEst.setString(4, estudiante.getEstadoAcademico().name());
                psEst.setInt(5, estudiante.getPersonaId());
                psEst.executeUpdate();
                
                conexion.commit();
                System.out.println("✅ Estudiante actualizado correctamente");
                return true;
                
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

    @Override
    public boolean eliminar(Estudiante estudiante) throws SQLException {
        String sql = "DELETE FROM personas WHERE id = ?";  // Cascade delete

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setInt(1, estudiante.getPersonaId());
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("✅ Estudiante eliminado correctamente");
                return true;
            }
            return false;
        }
    }

    @Override
    public List<Estudiante> listar() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM vista_estudiantes_completos ORDER BY apellido, nombre";

        try (Connection conexion = ConexionDB.getConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                estudiantes.add(mapearEstudianteVista(rs));
            }
        }
        return estudiantes;
    }

    /**
     * Busca un estudiante por su matrícula.
     * 
     * @param matricula Matrícula del estudiante
     * @return Estudiante encontrado o null
     * @throws SQLException si hay error en la consulta
     */
    public Estudiante buscarPorMatricula(String matricula) throws SQLException {
        Estudiante estudiante = null;
        String sql = "SELECT * FROM vista_estudiantes_completos WHERE matricula = ?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estudiante = mapearEstudianteVista(rs);
            }
        }
        return estudiante;
    }
    
    /**
     * Busca estudiantes por estado académico.
     * 
     * @param estado Estado académico (ACTIVO, INACTIVO, GRADUADO, SUSPENDIDO)
     * @return Lista de estudiantes con ese estado
     * @throws SQLException si hay error en la consulta
     */
    public List<Estudiante> buscarPorEstado(String estado) throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM vista_estudiantes_completos WHERE estado_academico = ? ORDER BY apellido, nombre";
        
        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, estado);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(mapearEstudianteVista(rs));
                }
            }
        }
        return estudiantes;
    }
    
    /**
     * Busca estudiantes por nombre o apellido (búsqueda parcial).
     * 
     * @param termino Término de búsqueda
     * @return Lista de estudiantes que coinciden
     * @throws SQLException si hay error en la consulta
     */
    public List<Estudiante> buscarPorNombre(String termino) throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM vista_estudiantes_completos " +
                     "WHERE nombre LIKE ? OR apellido LIKE ? OR nombre_completo LIKE ? " +
                     "ORDER BY apellido, nombre";
        
        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            String patron = "%" + termino + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            ps.setString(3, patron);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(mapearEstudianteVista(rs));
                }
            }
        }
        return estudiantes;
    }

    /**
     * Mapea un ResultSet a un objeto Estudiante.
     * 
     * @param rs ResultSet con los datos
     * @return Objeto Estudiante mapeado
     * @throws SQLException si hay error al leer los datos
     */
    private Estudiante mapearEstudiante(ResultSet rs) throws SQLException {
        Estudiante estudiante = new Estudiante();
        
        // Datos de estudiante
        estudiante.setId(rs.getInt("e.id"));
        estudiante.setPersonaId(rs.getInt("e.persona_id"));
        estudiante.setMatricula(rs.getString("e.matricula"));
        estudiante.setFechaIngreso(rs.getDate("e.fecha_ingreso").toLocalDate());
        estudiante.setPromedioGeneral(rs.getDouble("e.promedio_general"));
        estudiante.setCreditosCursados(rs.getInt("e.creditos_cursados"));
        estudiante.setEstadoAcademico(EstadoAcademico.valueOf(rs.getString("e.estado_academico")));
        
        // Datos de persona
        estudiante.setTipoDocumento(TipoDocumento.valueOf(rs.getString("p.tipo_documento")));
        estudiante.setNumeroDocumento(rs.getString("p.numero_documento"));
        estudiante.setNombre(rs.getString("p.nombre"));
        estudiante.setApellido(rs.getString("p.apellido"));
        estudiante.setFechaNacimiento(rs.getDate("p.fecha_nacimiento").toLocalDate());
        estudiante.setEmail(rs.getString("p.email"));
        estudiante.setTelefono(rs.getString("p.telefono"));
        estudiante.setDireccion(rs.getString("p.direccion"));
        estudiante.setActivo(rs.getBoolean("p.activo"));
        
        return estudiante;
    }

    /**
     * Mapea un ResultSet de la vista a un objeto Estudiante.
     * 
     * @param rs ResultSet de vista_estudiantes_completos
     * @return Objeto Estudiante mapeado
     * @throws SQLException si hay error al leer los datos
     */
    private Estudiante mapearEstudianteVista(ResultSet rs) throws SQLException {
        Estudiante estudiante = new Estudiante();
        
        estudiante.setId(rs.getInt("id"));
        estudiante.setMatricula(rs.getString("matricula"));
        estudiante.setTipoDocumento(TipoDocumento.valueOf(rs.getString("tipo_documento")));
        estudiante.setNumeroDocumento(rs.getString("numero_documento"));
        estudiante.setNombre(rs.getString("nombre"));
        estudiante.setApellido(rs.getString("apellido"));
        estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        estudiante.setEmail(rs.getString("email"));
        estudiante.setTelefono(rs.getString("telefono"));
        estudiante.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
        estudiante.setPromedioGeneral(rs.getDouble("promedio_general"));
        estudiante.setCreditosCursados(rs.getInt("creditos_cursados"));
        estudiante.setEstadoAcademico(EstadoAcademico.valueOf(rs.getString("estado_academico")));
        
        return estudiante;
    }

    /**
     * Verifica si existe un estudiante con la matrícula dada.
     */
    public boolean existeMatricula(String matricula) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM estudiantes WHERE matricula = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, matricula);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }

    /**
     * Verifica si existe una persona con el email dado.
     */
    public boolean existeEmail(String email) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM personas WHERE email = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si existe una matrícula/legajo dado (excluyendo un ID específico para edición).
     */
    public boolean existeMatricula(String matricula, Integer excludeId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM estudiantes WHERE matricula = ?";
        if (excludeId != null) {
            sql += " AND id != ?";
        }
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, matricula);
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
     * Verifica si existe un DNI dado (excluyendo un ID de persona específico para edición).
     */
    public boolean existeDNI(String dni, Integer excludePersonaId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM personas WHERE numero_documento = ?";
        if (excludePersonaId != null) {
            sql += " AND id != ?";
        }
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, dni);
            if (excludePersonaId != null) {
                ps.setInt(2, excludePersonaId);
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
     * Verifica si existe un email (excluyendo un ID de persona específico para edición).
     */
    public boolean existeEmail(String email, Integer excludePersonaId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM personas WHERE email = ?";
        if (excludePersonaId != null) {
            sql += " AND id != ?";
        }
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            if (excludePersonaId != null) {
                ps.setInt(2, excludePersonaId);
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
     * Verifica si un estudiante tiene inscripciones.
     */
    public boolean tieneInscripciones(Integer estudianteId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM inscripciones WHERE estudiante_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * Cuenta la cantidad de inscripciones de un estudiante.
     */
    public int contarInscripciones(Integer estudianteId) throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM inscripciones WHERE estudiante_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, estudianteId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
}


