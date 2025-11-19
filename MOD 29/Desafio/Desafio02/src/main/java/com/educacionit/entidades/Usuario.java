package com.educacionit.entidades;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Usuario {
	private Integer id;
	private String correo;
	private String clave;
	private LocalDate fechaCreacion;
	private Boolean activo;
	private static AtomicInteger contador = new AtomicInteger(1);

	public Usuario() {
		this.id = contador.getAndIncrement();
	}

	public Usuario(String correo, String clave, LocalDate fechaCreacion, Boolean activo) {
		super();
		this.id = contador.getAndIncrement();
		this.correo = correo;
		this.clave = clave;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
	}

	public Usuario(Integer id, String correo, String clave, LocalDate fechaCreacion, Boolean activo) {
		super();
		this.id = id == null ? this.id = contador.getAndIncrement() : id;
		this.correo = correo;
		this.clave = clave;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Usuario [ID=" + id + ", correo=" + correo + ", clave=" + clave + ", fechaCreacion=" + fechaCreacion
				+ ", activo=" + activo + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
