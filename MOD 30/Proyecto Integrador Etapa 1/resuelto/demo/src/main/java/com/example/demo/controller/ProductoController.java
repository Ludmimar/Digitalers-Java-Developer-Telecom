package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase ProductoController - Controlador REST para gestionar Productos
 * Esta clase expone una API REST completa para operaciones CRUD de productos
 * Todas las rutas comienzan con /productos
 * Maneja las peticiones HTTP del cliente y coordina con el servicio
 */
@RestController  // Marca esta clase como controlador REST que retorna JSON
@RequestMapping("/productos")  // Prefijo base para todas las rutas: /productos
public class ProductoController {

	// Obtiene la instancia única del servicio de productos (Singleton)
    private ProductoService productoService = ProductoService.getInstance();

    /**
     * Endpoint GET /productos - Lista todos los productos del catálogo
     * Método HTTP: GET
     * URL completa: http://localhost:8080/productos
     * @return List<Producto> - Lista completa de productos en formato JSON
     */
    @GetMapping  // Mapea peticiones GET sin parámetros
    public List<Producto> getAllProductos() {
        // Delega al servicio la obtención de todos los productos
        return productoService.findAll();
    }

    /**
     * Endpoint GET /productos/{id} - Obtiene un producto específico
     * Método HTTP: GET
     * URL ejemplo: http://localhost:8080/productos/5
     * @param id - Identificador del producto extraído de la URL
     * @return ResponseEntity<Producto> - Producto con código HTTP 200
     */
    @GetMapping("/{id}")  // {id} es un placeholder que se extrae de la URL
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        // @PathVariable vincula {id} de la URL al parámetro del método
        Producto producto = productoService.findById(id);
        // Envuelve el producto en ResponseEntity con código 200 OK
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    /**
     * Endpoint POST /productos - Crea un nuevo producto
     * Método HTTP: POST
     * URL: http://localhost:8080/productos
     * Body: JSON con los datos del nuevo producto
     * Ejemplo: {"nombre":"Laptop","precio":999.99,"descripcion":"...","urlFoto":"..."}
     * @param producto - Objeto Producto creado del JSON del body
     * @return ResponseEntity<Producto> - Producto creado con código 201
     */
    @PostMapping  // Mapea peticiones POST (usadas para crear recursos)
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        // @RequestBody deserializa el JSON del body a un objeto Producto
        Producto savedProducto = productoService.save(producto);
        // Retorna el producto guardado con código 201 CREATED
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    /**
     * Endpoint PUT /productos/{id} - Actualiza un producto existente
     * Método HTTP: PUT
     * URL ejemplo: http://localhost:8080/productos/5
     * Body: JSON con los datos actualizados
     * @param id - ID del producto a actualizar
     * @param productoDetails - Objeto con los nuevos valores
     * @return ResponseEntity<Producto> - Producto actualizado o 404
     */
    @PutMapping("/{id}")  // Mapea peticiones PUT (usadas para actualizar)
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        // Busca el producto existente por su ID
        Producto existingProducto = productoService.findById(id);
        
        // Verifica si el producto existe en el catálogo
        if (existingProducto != null) {
            // Actualiza todos los campos del producto existente
            existingProducto.setNombre(productoDetails.getNombre());
            existingProducto.setDescripcion(productoDetails.getDescripcion());
            existingProducto.setId(productoDetails.getId());
            existingProducto.setPrecio(productoDetails.getPrecio());
            existingProducto.setUrlFoto(productoDetails.getUrlFoto());
            // Retorna el producto actualizado con código 200 OK
            return ResponseEntity.ok(existingProducto);
        } else {
            // Si el producto no existe, retorna 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint DELETE /productos/{id} - Elimina un producto del catálogo
     * Método HTTP: DELETE
     * URL ejemplo: http://localhost:8080/productos/5
     * @param id - ID del producto a eliminar
     * @return ResponseEntity<Void> - 204 si se eliminó, 404 si no existe
     */
    @DeleteMapping("/{id}")  // Mapea peticiones DELETE (para eliminar recursos)
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        // Verifica primero si el producto existe
        if (productoService.findById(id) != null) {
            // Si existe, procede a eliminarlo
            productoService.deleteById(id);
            // Retorna 204 NO CONTENT (eliminación exitosa, sin cuerpo de respuesta)
            return ResponseEntity.noContent().build();
        } else {
            // Si no existe, retorna 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }
}
