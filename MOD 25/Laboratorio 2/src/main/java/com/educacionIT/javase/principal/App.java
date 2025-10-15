package com.educacionIT.javase.principal;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import com.educacionIT.javase.entidades.Administrativo;
import com.educacionIT.javase.entidades.Documento;
import com.educacionIT.javase.entidades.Empleado;
import com.educacionIT.javase.enumerados.TiposDocumento;
import com.educacionIT.javase.implementaciones.mariaDB.AdministrativoImpl;
import com.educacionIT.javase.interfaces.UtilidadesFecha;

/**
 * Hello world!
 *
 */
public class App {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		// mostramos por pantalla
		System.out.println("Ingreso de Datos de Administrador Laboratorio01");
		// mostramos un salto de linea para diferenciar el titulo
		System.out.print("\n");
		// declaramos las variables
		String nombre;
		String apellido;
		TiposDocumento tipo;
		Integer numero;
		Date fechaNacimiento;
		Date fechaCargo;
		Float sueldo;

		// objeto auxiliar
		Empleado empleado = null;

		// pedimos los atributos que son comunes a todos los objetos
		System.out.print("Ingrese el Nombre de la Persona : ");
		nombre = teclado.next();
		System.out.print("Ingrese el Apellido de la Persona : ");
		apellido = teclado.next();

		while (true) {
			System.out.print("Ingrese el Tipo de Documento de la Persona : ");

			try {
				tipo = TiposDocumento.valueOf(teclado.next().toUpperCase());
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		System.out.print("Ingrese el Numero de Documento de la Persona : ");
		numero = teclado.nextInt();
		System.out.println("Ingrese la Fecha de Nacimiento de la Persona : ");
		fechaNacimiento = obtenerFecha();

		System.out.println("Ingrese la Fecha de inicio del Cargo de la Persona : ");
		fechaCargo = obtenerFecha();

		System.out.print("Ingrese el Sueldo de la Persona : ");
		sueldo = teclado.nextFloat();

		empleado = new Administrativo(nombre, apellido, new Documento(tipo, numero), fechaNacimiento, fechaCargo,
				sueldo);

		// insertamos al empleado

		AdministrativoImpl administrativoImpl = new AdministrativoImpl();
		administrativoImpl.insertar((Administrativo) empleado);

		teclado.close();

	}

	/*
	 * Metodo para obtener la fecha
	 */
	private static Date obtenerFecha() {

		String fechaUsuario = "";
		Date fecha = null;
		while (true) {
			try {
				System.out.print("Formato Fecha[dd/mm/aaaa]: ");
				fechaUsuario = teclado.next();
				// el metodo parse recibe un string y retorna un objeto de tipo DATE
				fecha = UtilidadesFecha.getStringAFecha(fechaUsuario);
				break;
			} catch (ParseException e) {
				System.err.println("debe ingresar un dato valido: " + e.getMessage());
			}
		}

		return fecha;
	}

}
