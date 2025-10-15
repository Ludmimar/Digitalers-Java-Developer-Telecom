package com.educacionit.desafio02.entidades;

import java.util.Objects;

import com.educacionit.desafio02.interfaces.NumeroProducto;

public abstract class Producto implements NumeroProducto, Comparable<Producto> {
	private Integer banco;
	private Integer sucursal;
	private Integer numero;
	public static Integer NUMERO_CC = 1;
	public static Integer NUMERO_CA = 1;
	public static Integer NUMERO_TC = 1;

	public Producto() {

	}

	public Producto(Integer banco, Integer sucursal) {
		super();
		this.banco = banco;
		this.sucursal = sucursal;

	}

	@Override
	public String toString() {
		return "Producto [banco=" + banco + ", sucursal=" + sucursal + ", numero=" + numero + "]";
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(banco, numero, sucursal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(banco, other.banco) && Objects.equals(numero, other.numero)
				&& Objects.equals(sucursal, other.sucursal);
	}

	@Override
	public int compareTo(Producto productoAComparar) {
		try {
			Integer producto1 = this.banco + this.sucursal + this.numero;
			Integer producto2 = productoAComparar.banco + productoAComparar.sucursal + productoAComparar.numero;
			return producto1 - producto2;
		} catch (Exception e) {
			return 0;
		}

	}

}
