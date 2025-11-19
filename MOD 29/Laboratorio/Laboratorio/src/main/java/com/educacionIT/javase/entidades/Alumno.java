package com.educacionIT.javase.entidades;

import java.util.Arrays;
import java.util.Date;

public class Alumno extends Persona {
	private int[] cursos;

	/**
	 * 
	 */
	public Alumno() {
		super();
	}

	/**
	 * @param nombre
	 * @param apellido
	 * @param documento
	 * @param fechaNacimiento
	 * @param cursos
	 */
	public Alumno(String nombre, String apellido, Documento documento, Date fechaNacimiento, int[] cursos) {
		super(nombre, apellido, documento, fechaNacimiento);
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		return "Alumno [toString()=" + super.toString() + ", cursos=" + Arrays.toString(cursos) + "]";
	}

	public int[] getCursos() {
		return cursos;
	}

	public void setCursos(int[] cursos) {
		this.cursos = cursos;
	}

	@Override
	public String mostrarTipoPersona() {
		return getNombre() + " " + getApellido() + " es Alumno";
	}
}