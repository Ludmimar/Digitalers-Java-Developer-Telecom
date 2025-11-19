package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.model.Producto;

/**
 * Clase ProductoRepository - Capa de acceso a datos para Productos
 * Esta clase simula una base de datos almacenando productos en memoria
 * Proporciona operaciones CRUD completas para gestionar el catálogo
 * NOTA: Los datos NO son persistentes, se pierden al reiniciar la aplicación
 */
public class ProductoRepository {
	
	// ArrayList que actúa como almacenamiento en memoria para productos
	// Simula una tabla de base de datos pero los datos se pierden al cerrar la app
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	// Contador para generar IDs autoincrementales
	// Comienza en 1 y se incrementa cada vez que se guarda un nuevo producto
	private Long currentId = 1L;
	
	/**
	 * Método findAll - Obtiene todos los productos del catálogo
	 * @return List<Producto> con todos los productos disponibles
	 */
	public List<Producto> findAll() {
        return productos;  // Retorna la lista completa de productos
    }

	/**
	 * Método findById - Busca un producto específico por su ID
	 * @param id - identificador único del producto a buscar
	 * @return Producto encontrado, o null si no existe
	 */
    public Producto findById(Long id) {
    	// Itera sobre todos los productos en la lista
    	for (Producto producto : productos) {
    		// Compara el ID buscado con cada producto usando equals()
			if (id != null && id.equals(producto.getId())) {
				return producto;  // Si coincide, retorna el producto
			}
		}
        return null;  // Si no encuentra ninguno, retorna null
    }

    /**
     * Método save - Agrega un nuevo producto al catálogo
     * Si el producto no tiene ID (es null), se le asigna uno automáticamente
     * Si ya tiene ID, se considera una actualización
     * @param producto - objeto Producto a almacenar
     * @return Producto guardado con su ID asignado
     */
    public Producto save(Producto producto) {
    	// Si el ID es null, es un producto nuevo y se le asigna un ID autoincremental
    	if (producto.getId() == null) {
    		producto.setId(currentId);  // Asigna el ID actual
    		currentId++;  // Incrementa el contador para el siguiente producto
    		productos.add(producto);  // Añade el producto al ArrayList
    	} else {
    		// Si ya tiene ID, busca si existe para actualizar o agregar
    		Producto existente = findById(producto.getId());
    		if (existente == null) {
    			// Si no existe, lo agrega como nuevo
    			productos.add(producto);
    		}
    		// Si existe, los cambios ya se reflejan por referencia (no hace nada)
    	}
        return producto;  // Retorna el producto guardado con su ID
    }

    /**
     * Método deleteById - Elimina un producto del catálogo por su ID
     * @param id - identificador del producto a eliminar
     */
    public void deleteById(Long id) {
    	// Busca primero el producto por ID
    	Producto producto = findById(id);
    	// Verifica si el producto existe antes de eliminar
    	if (producto != null) {
    		productos.remove(producto);  // Elimina el producto de la lista
		}
		// Si no existe, no hace nada (operación segura)
    }

}
