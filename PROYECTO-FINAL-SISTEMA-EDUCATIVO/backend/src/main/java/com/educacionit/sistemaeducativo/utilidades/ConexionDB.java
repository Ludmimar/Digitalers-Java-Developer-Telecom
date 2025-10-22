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
    
    // Configuración de la base de datos
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";  // MySQL
    // private static final String DRIVER = "org.mariadb.jdbc.Driver";  // MariaDB
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_educativo";
    private static final String USUARIO = "root";
    private static final String CLAVE = "Boticaria89#";  // Cambiar según tu configuración
    
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
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                
                System.out.println("✅ Conexión exitosa a la base de datos");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver no encontrado");
            e.printStackTrace();
            throw new SQLException("Driver de base de datos no encontrado", e);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos");
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



