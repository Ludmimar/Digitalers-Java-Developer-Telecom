package com.educacionit.sistemaeducativo.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para gestionar conexiones a la base de datos MySQL/MariaDB.
 * Implementa el patrón Singleton para reutilizar conexiones.
 * 
 * @author Ludmila Martos
 */
public class ConexionDB {
    
    // Detectar si estamos en producción (Render)
    private static final String DATABASE_URL = System.getenv("DATABASE_URL");
    private static final String RENDER_SERVICE_NAME = System.getenv("RENDER_SERVICE_NAME");
    private static final String RENDER_SERVICE_ID = System.getenv("RENDER_SERVICE_ID");
    
    // Múltiples formas de detectar producción
    private static final boolean IS_PRODUCTION = DATABASE_URL != null || 
                                                 RENDER_SERVICE_NAME != null || 
                                                 RENDER_SERVICE_ID != null ||
                                                 System.getenv("PORT") != null;
    
    // Driver según entorno
    private static final String DRIVER = IS_PRODUCTION 
        ? "org.postgresql.Driver"       // PostgreSQL en Render
        : "com.mysql.cj.jdbc.Driver";   // MySQL en desarrollo local
    
    // Variables para desarrollo local
    private static final String DB_HOST_LOCAL = "localhost";
    private static final String DB_PORT_LOCAL = "3306";
    private static final String DB_NAME_LOCAL = "sistema_educativo";
    private static final String USUARIO_LOCAL = "root";
    private static final String CLAVE_LOCAL = "Boticaria89#";
    
    // Variables de entorno para producción (si DATABASE_URL no está disponible)
    private static final String DB_HOST_PROD = System.getenv("DB_HOST");
    private static final String DB_PORT_PROD = System.getenv("DB_PORT");
    private static final String DB_NAME_PROD = System.getenv("DB_NAME");
    private static final String USUARIO_PROD = System.getenv("DB_USER");
    private static final String CLAVE_PROD = System.getenv("DB_PASSWORD");
    
    // URL y credenciales - SIMPLIFICADO
    private static final String URL = IS_PRODUCTION
        ? (DATABASE_URL != null 
            ? DATABASE_URL  // Usar DATABASE_URL directamente (Render ya incluye el prefijo correcto)
            : "jdbc:postgresql://" + DB_HOST_PROD + ":" + DB_PORT_PROD + "/" + DB_NAME_PROD + "?sslmode=require")
        : "jdbc:mysql://" + DB_HOST_LOCAL + ":" + DB_PORT_LOCAL + "/" + DB_NAME_LOCAL + 
          "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    
    // Usuario y clave
    private static final String USUARIO = IS_PRODUCTION 
        ? (DATABASE_URL != null ? null : USUARIO_PROD)  // Si DATABASE_URL existe, no usar credenciales separadas
        : USUARIO_LOCAL;
    private static final String CLAVE = IS_PRODUCTION 
        ? (DATABASE_URL != null ? null : CLAVE_PROD)    // Si DATABASE_URL existe, no usar credenciales separadas
        : CLAVE_LOCAL;
    
    private static Connection conexion = null;

    // Constructor privado (Singleton)
    private ConexionDB() {
    }

    /**
     * Obtiene una conexión a la base de datos.
     * Si no existe, crea una nueva conexión.
     * 
     * @return Connection objeto de conexión a la base de datos
     * @throws SQLException si hay error en la conexión
     */
    public static Connection getConexion() throws SQLException {
        try {
            if (conexion == null || conexion.isClosed()) {
                // Cargar el driver
                Class.forName(DRIVER);
                
                // Debug: mostrar información del entorno
                System.out.println("🔍 Entorno detectado: " + (IS_PRODUCTION ? "PRODUCCIÓN" : "DESARROLLO"));
                System.out.println("🔍 Driver: " + DRIVER);
                System.out.println("🔍 DATABASE_URL disponible: " + (DATABASE_URL != null ? "SÍ" : "NO"));
                if (DATABASE_URL != null) {
                    System.out.println("🔍 DATABASE_URL: " + DATABASE_URL.substring(0, Math.min(50, DATABASE_URL.length())) + "...");
                }
                System.out.println("🔍 URL final: " + URL.substring(0, Math.min(50, URL.length())) + "...");
                
                // Establecer conexión
                if (IS_PRODUCTION) {
                    if (DATABASE_URL != null) {
                        // Caso 1: DATABASE_URL disponible (método preferido de Render)
                        conexion = DriverManager.getConnection(URL);
                        System.out.println("✅ Conexión exitosa a PostgreSQL (DATABASE_URL)");
                    } else {
                        // Caso 2: Variables separadas (fallback)
                        conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                        System.out.println("✅ Conexión exitosa a PostgreSQL (Variables separadas)");
                    }
                } else {
                    // En desarrollo local, usar credenciales separadas
                    conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                    System.out.println("✅ Conexión exitosa a MySQL (Desarrollo)");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver no encontrado - " + DRIVER);
            e.printStackTrace();
            throw new SQLException("Driver de base de datos no encontrado", e);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos");
            System.err.println("🔍 Entorno: " + (IS_PRODUCTION ? "PRODUCCIÓN" : "DESARROLLO"));
            System.err.println("🔍 URL: " + (IS_PRODUCTION && DATABASE_URL != null ? "DATABASE_URL (oculta)" : URL));
            e.printStackTrace();
            throw e;
        }
        return conexion;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✅ Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión");
            e.printStackTrace();
        }
    }

    /**
     * Verifica si la conexión está activa.
     * 
     * @return true si la conexión está activa, false en caso contrario
     */
    public static boolean isConectado() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}


