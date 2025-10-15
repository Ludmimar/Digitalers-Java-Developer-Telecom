package com.educacionIT.javase.interfaces;

/**
 * Interfaz que define las operaciones básicas de persistencia (CRUD).
 * 
 * Esta interfaz establece el contrato que deben cumplir todas las clases
 * que necesiten operaciones de almacenamiento y gestión de datos. Define
 * los métodos fundamentales para crear, leer, actualizar y eliminar
 * entidades en el sistema de persistencia.
 * 
 * Características principales:
 * - Define operaciones CRUD básicas
 * - Contrato que deben implementar las entidades persistentes
 * - Facilita la consistencia en el manejo de datos
 * - Permite diferentes implementaciones de persistencia
 * 
 * Operaciones definidas:
 * - guardar(): Crear o insertar una nueva entidad
 * - eliminar(): Eliminar una entidad existente
 * - modificar(): Actualizar una entidad existente
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public interface Persistencia {

	/**
	 * Guarda una entidad en el sistema de persistencia.
	 * 
	 * Este método debe implementar la lógica para crear o insertar
	 * una nueva entidad en el sistema de almacenamiento (base de datos,
	 * archivo, etc.). En una implementación real, aquí se realizaría
	 * la inserción en la base de datos.
	 */
	void guardar();

	/**
	 * Elimina una entidad del sistema de persistencia.
	 * 
	 * Este método debe implementar la lógica para eliminar
	 * una entidad existente del sistema de almacenamiento.
	 * En una implementación real, aquí se realizaría
	 * la eliminación de la base de datos.
	 */
	void eliminar();

	/**
	 * Modifica una entidad existente en el sistema de persistencia.
	 * 
	 * Este método debe implementar la lógica para actualizar
	 * una entidad existente en el sistema de almacenamiento.
	 * En una implementación real, aquí se realizaría
	 * la actualización en la base de datos.
	 */
	void modificar();

}
