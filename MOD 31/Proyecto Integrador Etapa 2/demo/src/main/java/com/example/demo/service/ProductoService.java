package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

public class ProductoService {
	private static ProductoService instance = new ProductoService();
	private ProductoRepository productoRepository;
	
	private ProductoService() {
		productoRepository = new ProductoRepository();
	};
	
	public static ProductoService getInstance() {
		return instance;
	}

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

	public Producto getByNombre(String nombre) {
		return productoRepository.getByNombre(nombre);
	}

}
