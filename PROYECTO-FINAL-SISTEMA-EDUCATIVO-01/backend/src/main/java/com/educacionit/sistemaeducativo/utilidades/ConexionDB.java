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
    
    // Detectar si estamos en producción (Render usa DATABASE_URL)
    private static final String DATABASE_URL = System.getenv("DATABASE_URL");
    private static final boolean IS_PRODUCTION = DATABASE_URL != null;
    
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
    
    // URL y credenciales
    private static final String URL = IS_PRODUCTION
        ? DATABASE_URL  // Render proporciona la URL completa
        : "jdbc:mysql://" + DB_HOST_LOCAL + ":" + DB_PORT_LOCAL + "/" + DB_NAME_LOCAL + 
          "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    
    // Usuario y clave (en producción se extraen de DATABASE_URL, así que no se usan estas variables)
    private static final String USUARIO = USUARIO_LOCAL;
    private static final String CLAVE = CLAVE_LOCAL;
    
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
                
                // Establecer conexión
                if (IS_PRODUCTION) {
                    // En producción, DATABASE_URL ya incluye usuario y contraseña
                    conexion = DriverManager.getConnection(URL);
                    System.out.println("✅ Conexión exitosa a PostgreSQL (Producción)");
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
            System.err.println("URL: " + (IS_PRODUCTION ? "DATABASE_URL (oculta)" : URL));
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


