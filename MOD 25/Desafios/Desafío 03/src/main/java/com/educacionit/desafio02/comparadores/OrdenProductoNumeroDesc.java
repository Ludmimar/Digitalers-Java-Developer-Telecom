package com.educacionit.desafio02.comparadores;

import java.util.Comparator;

import com.educacionit.desafio02.entidades.Producto;

public class OrdenProductoNumeroDesc implements Comparator<Producto> {

	@Override
	public int compare(Producto producto1, Producto producto2) {
		try {
			Integer primerProducto = producto1.getBanco() + producto1.getSucursal() + producto1.getNumero();
			Integer segundoProducto = producto2.getBanco() + producto2.getSucursal() + producto2.getNumero();
			return segundoProducto - primerProducto;
		} catch (Exception e) {
			return 0;
		}
	}
}
