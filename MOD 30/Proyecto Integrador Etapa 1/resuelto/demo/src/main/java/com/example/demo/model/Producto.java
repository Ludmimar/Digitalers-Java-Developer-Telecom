package com.example.demo.model;

/**
 * Clase Producto - Modelo que representa un producto en el catálogo
 * Esta clase es un POJO que almacena toda la información de un producto
 * Incluye datos básicos, precio, descripción y URL de imagen
 */
public class Producto {
	// ID único del producto (tipo Long para identificadores grandes)
    private Long id;
    
    // Nombre del producto
    private String nombre;
    
    // Precio del producto (Double permite decimales para centavos)
    private Double precio;
    
    // Descripción detallada del producto
    private String descripcion;
    
    // URL de la imagen del producto (ruta o enlace a la foto)
    private String urlFoto;
    
    /**
     * Getter - Obtiene el ID del producto
     * @return Long con el identificador único
     */
	public Long getId() {
		return id;
	}
	
	/**
	 * Setter - Establece el ID del producto
	 * @param id - identificador único a asignar
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Getter - Obtiene el nombre del producto
	 * @return String con el nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter - Establece el nombre del producto
	 * @param nombre - nombre del producto a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter - Obtiene el precio del producto
	 * @return Double con el precio (permite decimales)
	 */
	public Double getPrecio() {
		return precio;
	}
	
	/**
	 * Setter - Establece el precio del producto
	 * @param precio - precio a asignar (ej: 99.99)
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	/**
	 * Getter - Obtiene la descripción del producto
	 * @return String con la descripción completa
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Setter - Establece la descripción del producto
	 * @param descripcion - texto descriptivo a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Getter - Obtiene la URL de la foto del producto
	 * @return String con la ruta o URL de la imagen
	 */
	public String getUrlFoto() {
		return urlFoto;
	}
	
	/**
	 * Setter - Establece la URL de la foto
	 * @param urlFoto - ruta o URL de la imagen a asignar
	 */
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
}
