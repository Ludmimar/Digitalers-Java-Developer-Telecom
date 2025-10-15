package com.educacionit.desafio01.entidades;

import java.util.Objects;

public class Ciudad {
	private int id;
	private int id_pais;
	private String descripcion;
	private String categoria;

	public Ciudad() {
		super();
	}

	public Ciudad(int id, int id_pais, String descripcion, String categoria) {
		super();
		this.id = id;
		this.id_pais = id_pais;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", id_pais=" + id_pais + ", descripcion=" + descripcion + ", categoria=" + categoria
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_pais() {
		return id_pais;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		return id == other.id;
	}
	
	

}
