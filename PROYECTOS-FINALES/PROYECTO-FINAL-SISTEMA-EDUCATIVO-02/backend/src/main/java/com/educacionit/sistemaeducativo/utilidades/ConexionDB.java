package com.educacionit.sistemaeducativo.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para gestionar conexiones a la base de datos MySQL/MariaDB.
 * Implementa el patrón Singleton para reutilizar conexiones.
 * 
 * PROPÓSITO:
 * - Centraliza la gestión de conexiones a la base de datos
 * - Implementa patrón Singleton para evitar múltiples conexiones
 * - Demuestra manejo de recursos y conexiones de BD
 * - Proporciona configuración centralizada de BD
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Patrón Singleton: una sola instancia de conexión
 * - Manejo de recursos: gestión adecuada de conexiones
 * - Configuración centralizada: constantes para configuración
 * - Manejo de excepciones: SQLException y ClassNotFoundException
 * - Logging básico: mensajes de éxito y error
 * 
 * @author Ludmila Martos
 */
public class ConexionDB {
    
    // CONFIGURACIÓN DE LA BASE DE DATOS
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";  // Driver MySQL 8+
    // private static final String DRIVER = "org.mariadb.jdbc.Driver";  // Driver MariaDB (alternativa)
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_educativo";  // URL de conexión
    private static final String USUARIO = "root";                    // Usuario de la BD
    private static final String CLAVE = "Boticaria89#";              // Contraseña (cambiar según configuración)
    
    // Variable estática para mantener la única instancia de conexión (Singleton)
    private static Connection conexion = null;

    // CONSTRUCTOR PRIVADO (SINGLETON)
    /**
     * Constructor privado para evitar instanciación directa
     * Demuestra implementación del patrón Singleton
     */
    private ConexionDB() {
        // Constructor privado para evitar instanciación externa
    }

    // MÉTODO PRINCIPAL (SINGLETON)
    /**
     * Obtiene una conexión a la base de datos.
     * Implementa patrón Singleton: si no existe conexión, la crea; si existe, la reutiliza.
     * Demuestra gestión eficiente de recursos de BD.
     * 
     * @return Connection objeto de conexión a la base de datos
     * @throws SQLException si hay error en la conexión o driver no encontrado
     */
    public static Connection getConexion() throws SQLException {
        try {
            // Verificar si la conexión existe y está abierta
            if (conexion == null || conexion.isClosed()) {
                // Cargar el driver de MySQL/MariaDB
                Class.forName(DRIVER);
                
                // Establecer conexión con la base de datos
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                
                // Log de éxito
                System.out.println("✅ Conexión exitosa a la base de datos");
            }
        } catch (ClassNotFoundException e) {
            // Error: driver no encontrado en el classpath
            System.err.println("❌ Error: Driver no encontrado");
            e.printStackTrace();
            throw new SQLException("Driver de base de datos no encontrado", e);
        } catch (SQLException e) {
            // Error: problemas de conexión (servidor, credenciales, etc.)
            System.err.println("❌ Error al conectar con la base de datos");
            e.printStackTrace();
            throw e;
        }
        return conexion;  // Retornar la conexión (nueva o existente)
    }

    // MÉTODO DE LIMPIEZA DE RECURSOS
    /**
     * Cierra la conexión a la base de datos si está abierta.
     * Demuestra gestión adecuada de recursos y limpieza.
     * Importante para liberar recursos del sistema.
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();  // Cerrar la conexión
                System.out.println("✅ Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            // Error al cerrar la conexión
            System.err.println("❌ Error al cerrar la conexión");
            e.printStackTrace();
        }
    }

    // MÉTODO DE VERIFICACIÓN DE ESTADO
    /**
     * Verifica si la conexión está activa y disponible.
     * Útil para validar el estado de la conexión antes de usarla.
     * 
     * @return true si la conexión está activa, false en caso contrario
     */
    public static boolean isConectado() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            // Si hay error al verificar, asumir que no está conectado
            return false;
        }
    }
}


