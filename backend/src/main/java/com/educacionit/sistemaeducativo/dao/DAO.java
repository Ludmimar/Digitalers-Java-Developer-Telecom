package com.educacionit.sistemaeducativo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica que define las operaciones CRUD estándar.
 * Utiliza genéricos para ser reutilizable con cualquier entidad.
 * 
 * @param <K> Tipo de la clave primaria (generalmente Integer)
 * @param <V> Tipo de la entidad (Estudiante, Profesor, etc.)
 * 
 * @author Ludmila Martos
 */
public interface DAO<K, V> {
    
    /**
     * Busca una entidad por su ID.
     * 
     * @param id Identificador único de la entidad
     * @return La entidad encontrada o null si no existe
     * @throws SQLException si hay error en la consulta
     */
    V buscarPorID(K id) throws SQLException;
    
    /**
     * Inserta una nueva entidad en la base de datos.
     * 
     * @param entidad Objeto a insertar
     * @return true si la inserción fue exitosa
     * @throws SQLException si hay error en la inserción
     */
    boolean insertar(V entidad) throws SQLException;
    
    /**
     * Actualiza una entidad existente en la base de datos.
     * 
     * @param entidad Objeto con los datos actualizados
     * @return true si la actualización fue exitosa
     * @throws SQLException si hay error en la actualización
     */
    boolean actualizar(V entidad) throws SQLException;
    
    /**
     * Elimina una entidad de la base de datos.
     * 
     * @param entidad Objeto a eliminar
     * @return true si la eliminación fue exitosa
     * @throws SQLException si hay error en la eliminación
     */
    boolean eliminar(V entidad) throws SQLException;
    
    /**
     * Lista todas las entidades de este tipo.
     * 
     * @return Lista con todas las entidades
     * @throws SQLException si hay error en la consulta
     */
    List<V> listar() throws SQLException;
}


