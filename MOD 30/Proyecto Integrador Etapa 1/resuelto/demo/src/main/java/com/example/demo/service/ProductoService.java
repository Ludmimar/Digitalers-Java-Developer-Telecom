package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

/**
 * Clase ProductoService - Capa de lógica de negocio para Productos
 * Implementa el patrón Singleton para tener una sola instancia
 * Gestiona las operaciones de negocio relacionadas con productos
 * Actúa como puente entre los Controllers y el Repository
 */
public class ProductoService {
	// Instancia única y estática del servicio (patrón Singleton)
	// Se inicializa una sola vez cuando se carga la clase
	private static ProductoService instance = new ProductoService();
	
	// Repositorio para acceder a los datos de productos
	private ProductoRepository productoRepository;
	
	/**
	 * Constructor privado - Implementación del patrón Singleton
	 * Al ser privado, impide que se creen instancias con "new" desde fuera
	 * Solo se puede acceder a través de getInstance()
	 */
	private ProductoService() {
		// Inicializa el repositorio cuando se crea el servicio
		productoRepository = new ProductoRepository();
	};
	
	/**
	 * Método getInstance - Proporciona acceso a la única instancia
	 * Es el método público para obtener el servicio (patrón Singleton)
	 * @return ProductoService - la única instancia del servicio
	 */
	public static ProductoService getInstance() {
		return instance;
	}

	/**
	 * Método findAll - Obtiene todos los productos del catálogo
	 * Delega la operación al repositorio
	 * @return List<Producto> con todos los productos disponibles
	 */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    /**
     * Método findById - Busca un producto específico por ID
     * Aquí se puede agregar lógica adicional como caché, logging, etc.
     * @param id - identificador único del producto
     * @return Producto encontrado o null si no existe
     */
    public Producto findById(Long id) {
        return productoRepository.findById(id);
    }

    /**
     * Método save - Guarda un nuevo producto en el catálogo
     * Aquí se pueden agregar validaciones de negocio:
     * - Verificar que el precio sea positivo
     * - Validar que el nombre no esté vacío
     * - Comprobar duplicados, etc.
     * @param producto - objeto Producto a guardar
     * @return Producto guardado
     */
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Método deleteById - Elimina un producto del catálogo
     * Se podría agregar lógica de seguridad o auditoría
     * @param id - identificador del producto a eliminar
     */
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

}
