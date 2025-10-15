package com.educacionit.desafio01.entidades;

import java.util.List;
import java.util.Objects;

public class Pais {
	private int id;
	private String descripcion;
	private List<Ciudad> ciudades;

	public Pais() {
		super();
	}

	public Pais(int id, String descripcion, List<Ciudad> ciudades) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.ciudades = ciudades;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", descripcion=" + descripcion + ", ciudades=" + ciudades + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
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
		Pais other = (Pais) obj;
		return id == other.id;
	}
	

}
