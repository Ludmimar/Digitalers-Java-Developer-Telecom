package com.educacionit.desafio02.entidades;

import java.time.LocalDateTime;

public class Log {
	private Integer id;
	private LocalDateTime fecha;
	private String clase;
	private String objeto;
	private String error;

	public Log() {
		super();
	}

	public Log(Integer id, LocalDateTime fecha, String clase, String objeto, String error) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.clase = clase;
		this.objeto = objeto;
		this.error = error;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", fecha=" + fecha + ", clase=" + clase + ", objeto=" + objeto + ", error=" + error
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
