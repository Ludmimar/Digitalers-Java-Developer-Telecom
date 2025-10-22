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
 * PROPÓSITO:
 * - Implementa el patrón DAO para acceso a datos de estudiantes
 * - Encapsula toda la lógica de acceso a la base de datos
 * - Demuestra transacciones complejas con múltiples tablas
 * - Implementa validaciones de unicidad y integridad
 * - Maneja relaciones entre tablas personas y estudiantes
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Patrón DAO: separación de lógica de acceso a datos
 * - Transacciones: operaciones atómicas con rollback
 * - PreparedStatement: consultas parametrizadas seguras
 * - Mapeo objeto-relacional: convertir ResultSet a objetos Java
 * - Validaciones de negocio: unicidad de datos críticos
 * - Manejo de recursos: try-with-resources para conexiones
 * 
 * @author Ludmila Martos
 */
public class EstudianteDAOImpl implements DAO<Integer, Estudiante> {

    // IMPLEMENTACIÓN DE MÉTODO DE BÚSQUEDA
    /**
     * Busca un estudiante por su ID único.
     * Demuestra JOIN entre tablas relacionadas y mapeo de resultados.
     */
    @Override
    public Estudiante buscarPorID(Integer id) throws SQLException {
        Estudiante estudiante = null;
        // Consulta SQL con JOIN para obtener datos completos del estudiante
        String sql = "SELECT e.*, p.* FROM estudiantes e " +
                     "INNER JOIN personas p ON e.persona_id = p.id " +
                     "WHERE e.id = ?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setInt(1, id);  // Establecer parámetro de la consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estudiante = mapearEstudiante(rs);  // Convertir ResultSet a objeto Estudiante
            }
        }
        return estudiante;
    }

    // IMPLEMENTACIÓN DE MÉTODO DE INSERCIÓN CON TRANSACCIONES
    /**
     * Inserta un nuevo estudiante con validaciones y transacciones.
     * Demuestra operaciones atómicas con múltiples tablas.
     */
    @Override
    public boolean insertar(Estudiante estudiante) throws SQLException {
        // VALIDACIONES PREVIAS
        // Demuestra validaciones de negocio antes de persistir
        if (existeMatricula(estudiante.getMatricula())) {
            throw new SQLException("Ya existe un estudiante con la matrícula: " + estudiante.getMatricula());
        }
        
        if (existeEmail(estudiante.getEmail())) {
            throw new SQLException("Ya existe un estudiante con el email: " + estudiante.getEmail());
        }
        
        // CONSULTAS SQL PARA INSERCIÓN EN MÚLTIPLES TABLAS
        // Primero insertar en tabla personas (tabla padre)
        String sqlPersona = "INSERT INTO personas (tipo_documento, numero_documento, nombre, " +
                           "apellido, fecha_nacimiento, email, telefono, direccion) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Luego insertar en tabla estudiantes (tabla hija)
        String sqlEstudiante = "INSERT INTO estudiantes (persona_id, matricula, " +
                              "fecha_ingreso, estado_academico) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConexion()) {
            conexion.setAutoCommit(false);  // Iniciar transacción manual
            
            try {
                // PASO 1: Insertar en tabla personas
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
                
                // OBTENER ID GENERADO DE LA PERSONA
                // Demuestra uso de claves generadas automáticamente
                ResultSet rs = psPersona.getGeneratedKeys();
                if (rs.next()) {
                    int personaId = rs.getInt(1);
                    estudiante.setPersonaId(personaId);
                    
                    // PASO 2: Insertar en tabla estudiantes usando el persona_id generado
                    PreparedStatement psEst = conexion.prepareStatement(sqlEstudiante,
                                                Statement.RETURN_GENERATED_KEYS);
                    psEst.setInt(1, personaId);  // Referencia a la persona creada
                    psEst.setString(2, estudiante.getMatricula());
                    psEst.setDate(3, Date.valueOf(estudiante.getFechaIngreso()));
                    psEst.setString(4, estudiante.getEstadoAcademico().name());
                    
                    psEst.executeUpdate();
                    
                    // OBTENER ID DEL ESTUDIANTE
                    ResultSet rsEst = psEst.getGeneratedKeys();
                    if (rsEst.next()) {
                        estudiante.setId(rsEst.getInt(1));
                    }
                }
                
                // CONFIRMAR TRANSACCIÓN
                // Todas las operaciones se completaron exitosamente
                conexion.commit();
                System.out.println("✅ Estudiante insertado correctamente: " + estudiante.getMatricula());
                return true;
                
            } catch (SQLException e) {
                // REVERTIR TRANSACCIÓN EN CASO DE ERROR
                // Demuestra manejo de errores con rollback automático
                conexion.rollback();
                System.err.println("❌ Error al insertar estudiante");
                throw e;
            }
        }
    }

    // IMPLEMENTACIÓN DE MÉTODO DE ACTUALIZACIÓN CON TRANSACCIONES
    /**
     * Actualiza los datos de un estudiante existente.
     * Demuestra actualización coordinada en múltiples tablas relacionadas.
     */
    @Override
    public boolean actualizar(Estudiante estudiante) throws SQLException {
        // CONSULTAS SQL PARA ACTUALIZACIÓN EN MÚLTIPLES TABLAS
        // Primero actualizar tabla personas
        String sqlPersona = "UPDATE personas SET nombre=?, apellido=?, email=?, " +
                           "telefono=?, direccion=? WHERE id=?";
        
        // Luego actualizar tabla estudiantes
        String sqlEstudiante = "UPDATE estudiantes SET matricula=?, promedio_general=?, " +
                              "creditos_cursados=?, estado_academico=? WHERE persona_id=?";

        try (Connection conexion = ConexionDB.getConexion()) {
            conexion.setAutoCommit(false);  // Iniciar transacción
            
            try {
                // PASO 1: Actualizar datos de persona
                PreparedStatement psPersona = conexion.prepareStatement(sqlPersona);
                psPersona.setString(1, estudiante.getNombre());
                psPersona.setString(2, estudiante.getApellido());
                psPersona.setString(3, estudiante.getEmail());
                psPersona.setString(4, estudiante.getTelefono());
                psPersona.setString(5, estudiante.getDireccion());
                psPersona.setInt(6, estudiante.getPersonaId());
                psPersona.executeUpdate();
                
                // PASO 2: Actualizar datos específicos de estudiante
                PreparedStatement psEst = conexion.prepareStatement(sqlEstudiante);
                psEst.setString(1, estudiante.getMatricula());
                psEst.setDouble(2, estudiante.getPromedioGeneral());
                psEst.setInt(3, estudiante.getCreditosCursados());
                psEst.setString(4, estudiante.getEstadoAcademico().name());
                psEst.setInt(5, estudiante.getPersonaId());
                psEst.executeUpdate();
                
                // CONFIRMAR TRANSACCIÓN
                conexion.commit();
                System.out.println("✅ Estudiante actualizado correctamente");
                return true;
                
            } catch (SQLException e) {
                // REVERTIR CAMBIOS EN CASO DE ERROR
                conexion.rollback();
                throw e;
            }
        }
    }

    // IMPLEMENTACIÓN DE MÉTODO DE ELIMINACIÓN
    /**
     * Elimina un estudiante de la base de datos.
     * Demuestra eliminación en cascada mediante DELETE en tabla padre.
     */
    @Override
    public boolean eliminar(Estudiante estudiante) throws SQLException {
        // ELIMINACIÓN EN CASCADA
        // Al eliminar la persona, se eliminan automáticamente los estudiantes relacionados
        // Esto se configura en la base de datos con ON DELETE CASCADE
        String sql = "DELETE FROM personas WHERE id = ?";

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

    // IMPLEMENTACIÓN DE MÉTODO DE LISTADO
    /**
     * Lista todos los estudiantes de la base de datos.
     * Demuestra uso de vistas de base de datos para simplificar consultas complejas.
     */
    @Override
    public List<Estudiante> listar() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        // USO DE VISTA DE BASE DE DATOS
        // La vista vista_estudiantes_completos combina datos de personas y estudiantes
        // Demuestra optimización mediante vistas pre-calculadas
        String sql = "SELECT * FROM vista_estudiantes_completos ORDER BY apellido, nombre";

        try (Connection conexion = ConexionDB.getConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // CONSTRUCCIÓN DE LISTA
            // Itera sobre todos los resultados y mapea cada uno a un objeto Estudiante
            while (rs.next()) {
                estudiantes.add(mapearEstudianteVista(rs));
            }
        }
        return estudiantes;
    }

    // MÉTODOS DE BÚSQUEDA PERSONALIZADOS
    
    /**
     * Busca un estudiante por su matrícula.
     * Demuestra consulta parametrizada con criterio único.
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
     * Demuestra filtrado por enumerado y consultas dinámicas.
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
     * Demuestra búsqueda flexible con operador LIKE y múltiples campos.
     * 
     * @param termino Término de búsqueda
     * @return Lista de estudiantes que coinciden
     * @throws SQLException si hay error en la consulta
     */
    public List<Estudiante> buscarPorNombre(String termino) throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        // BÚSQUEDA CON OPERADOR LIKE
        // Demuestra búsqueda parcial en múltiples campos
        String sql = "SELECT * FROM vista_estudiantes_completos " +
                     "WHERE nombre LIKE ? OR apellido LIKE ? OR nombre_completo LIKE ? " +
                     "ORDER BY apellido, nombre";
        
        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            // CONSTRUCCIÓN DE PATRÓN LIKE
            // % indica cualquier cantidad de caracteres antes y después
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

    // MÉTODOS DE MAPEO (MAPPERS)
    // Convierten ResultSet de base de datos a objetos Java
    
    /**
     * Mapea un ResultSet con JOIN a un objeto Estudiante.
     * Demuestra mapeo objeto-relacional con datos de múltiples tablas.
     * 
     * @param rs ResultSet con datos de JOIN entre estudiantes y personas
     * @return Objeto Estudiante completo con todos sus datos
     * @throws SQLException si hay error al leer los datos
     */
    private Estudiante mapearEstudiante(ResultSet rs) throws SQLException {
        Estudiante estudiante = new Estudiante();
        
        // MAPEO DE DATOS DE ESTUDIANTE (tabla estudiantes)
        // Prefijo "e." indica que viene de la tabla estudiantes en el JOIN
        estudiante.setId(rs.getInt("e.id"));
        estudiante.setPersonaId(rs.getInt("e.persona_id"));
        estudiante.setMatricula(rs.getString("e.matricula"));
        estudiante.setFechaIngreso(rs.getDate("e.fecha_ingreso").toLocalDate());
        estudiante.setPromedioGeneral(rs.getDouble("e.promedio_general"));
        estudiante.setCreditosCursados(rs.getInt("e.creditos_cursados"));
        estudiante.setEstadoAcademico(EstadoAcademico.valueOf(rs.getString("e.estado_academico")));
        
        // MAPEO DE DATOS DE PERSONA (tabla personas)
        // Prefijo "p." indica que viene de la tabla personas en el JOIN
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
     * Mapea un ResultSet de vista a un objeto Estudiante.
     * Demuestra uso de vistas de base de datos para simplificar mapeos.
     * 
     * @param rs ResultSet de vista_estudiantes_completos (vista SQL)
     * @return Objeto Estudiante mapeado
     * @throws SQLException si hay error al leer los datos
     */
    private Estudiante mapearEstudianteVista(ResultSet rs) throws SQLException {
        Estudiante estudiante = new Estudiante();
        
        // MAPEO DESDE VISTA
        // La vista ya tiene los campos combinados sin prefijos de tabla
        // Demuestra simplificación de consultas con vistas
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

    // MÉTODOS DE VALIDACIÓN Y VERIFICACIÓN
    // Validan unicidad e integridad de datos antes de operaciones críticas
    
    /**
     * Verifica si existe un estudiante con la matrícula dada.
     * Demuestra validación de unicidad para evitar duplicados.
     */
    public boolean existeMatricula(String matricula) throws SQLException {
        // CONSULTA DE CONTEO
        // Usa COUNT(*) para verificar existencia sin cargar datos completos
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
     * Demuestra validación de unicidad en tabla relacionada.
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
     * Verifica si existe una matrícula (excluyendo un ID específico para edición).
     * Demuestra validación de unicidad en actualizaciones sin conflicto con el mismo registro.
     */
    public boolean existeMatricula(String matricula, Integer excludeId) throws SQLException {
        // CONSULTA DINÁMICA CON EXCLUSIÓN
        // Permite validar unicidad al editar sin considerar el propio registro
        String sql = "SELECT COUNT(*) as total FROM estudiantes WHERE matricula = ?";
        if (excludeId != null) {
            sql += " AND id != ?";  // Excluir el ID que se está editando
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
     * Verifica si existe un DNI (excluyendo un ID de persona específico para edición).
     * Demuestra validación de unicidad con exclusión de registro actual.
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
     * Demuestra validación flexible para inserción y actualización.
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
     * Demuestra validación de integridad referencial antes de eliminación.
     */
    public boolean tieneInscripciones(Integer estudianteId) throws SQLException {
        // VERIFICACIÓN DE RELACIONES
        // Previene eliminación de registros con dependencias
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
     * Demuestra consulta agregada para estadísticas y reportes.
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


