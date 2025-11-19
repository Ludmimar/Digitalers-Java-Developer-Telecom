package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.example.demo.model.Producto;

public class ProductoRepository {
	
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	public List<Producto> findAll() {
        return productos;
    }

    public Producto findById(Long id) {
    	for (Producto producto : productos) {
			if (id == producto.getId()) {
				return producto;
			}
		}
        return null;
    }

    public Producto save(Producto producto) {
    	productos.add(producto);
        return producto;
    }

    public void deleteById(Long id) {
    	Producto producto = findById(id);
    	if (producto != null) {
    		productos.remove(producto);
		}
    }

	public Producto getByNombre(String nombre) {
		for (Producto producto : productos) {
			if (nombre == producto.getNombre()) {
				return producto;
			}
		}
        return null;
	}

}
