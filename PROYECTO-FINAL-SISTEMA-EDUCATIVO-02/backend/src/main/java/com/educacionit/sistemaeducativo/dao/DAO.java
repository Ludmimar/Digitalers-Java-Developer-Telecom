package com.educacionit.sistemaeducativo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica que define las operaciones CRUD estándar.
 * Utiliza genéricos para ser reutilizable con cualquier entidad.
 * 
 * PROPÓSITO:
 * - Define contrato estándar para acceso a datos (patrón DAO)
 * - Utiliza genéricos para reutilización con cualquier entidad
 * - Demuestra polimorfismo e interfaces en Java
 * - Establece operaciones CRUD básicas para todas las entidades
 * 
 * CONCEPTOS DEMOSTRADOS:
 * - Interfaces: define contrato sin implementación
 * - Genéricos: <K, V> para flexibilidad de tipos
 * - Polimorfismo: diferentes implementaciones para cada entidad
 * - Patrón DAO: separación de lógica de acceso a datos
 * - Manejo de excepciones: SQLException para errores de BD
 * 
 * @param <K> Tipo de la clave primaria (generalmente Integer)
 * @param <V> Tipo de la entidad (Estudiante, Profesor, etc.)
 * 
 * @author Ludmila Martos
 */
public interface DAO<K, V> {
    
    /**
     * Busca una entidad por su ID único.
     * Demuestra operación de lectura específica por clave primaria.
     * 
     * @param id Identificador único de la entidad
     * @return La entidad encontrada o null si no existe
     * @throws SQLException si hay error en la consulta SQL
     */
    V buscarPorID(K id) throws SQLException;
    
    /**
     * Inserta una nueva entidad en la base de datos.
     * Demuestra operación de creación (CREATE del CRUD).
     * 
     * @param entidad Objeto a insertar con todos sus datos
     * @return true si la inserción fue exitosa, false en caso contrario
     * @throws SQLException si hay error en la inserción SQL
     */
    boolean insertar(V entidad) throws SQLException;
    
    /**
     * Actualiza una entidad existente en la base de datos.
     * Demuestra operación de actualización (UPDATE del CRUD).
     * 
     * @param entidad Objeto con los datos actualizados (debe tener ID válido)
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws SQLException si hay error en la actualización SQL
     */
    boolean actualizar(V entidad) throws SQLException;
    
    /**
     * Elimina una entidad de la base de datos.
     * Demuestra operación de eliminación (DELETE del CRUD).
     * 
     * @param entidad Objeto a eliminar (debe tener ID válido)
     * @return true si la eliminación fue exitosa, false en caso contrario
     * @throws SQLException si hay error en la eliminación SQL
     */
    boolean eliminar(V entidad) throws SQLException;
    
    /**
     * Lista todas las entidades de este tipo.
     * Demuestra operación de lectura masiva (READ del CRUD).
     * 
     * @return Lista con todas las entidades encontradas
     * @throws SQLException si hay error en la consulta SQL
     */
    List<V> listar() throws SQLException;
}


