package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    private ProductoService productoService = ProductoService.getInstance();

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoService.save(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            existingProducto.setNombre(productoDetails.getNombre());
            existingProducto.setDescripcion(productoDetails.getDescripcion());
            existingProducto.setId(productoDetails.getId());
            existingProducto.setPrecio(productoDetails.getPrecio());
            existingProducto.setUrlFoto(productoDetails.getUrlFoto());
            return ResponseEntity.ok(existingProducto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.findById(id) != null) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable String nombre) {
        Producto producto = productoService.getByNombre(nombre);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
    
    @GetMapping("/urlFoto/{urlFoto}")
    public ResponseEntity<Producto> getByUrlFoto(@PathVariable String urlFoto) {
        Producto producto = productoService.getByUrlFoto(urlFoto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
}
