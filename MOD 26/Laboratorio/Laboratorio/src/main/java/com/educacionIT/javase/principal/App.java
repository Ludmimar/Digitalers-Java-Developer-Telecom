package com.educacionIT.javase.principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Inicio del Programa");
		System.out.println("Escriba el texto que desea guardar y al finalizar escriba una linea con la palabra 'fin':");

		Scanner teclado = new Scanner(System.in);
		List<String> texto = new ArrayList<>();
		do {
			String linea = teclado.nextLine();
			if (linea.equalsIgnoreCase("fin")) {
				break;
			}
			texto.add(linea);

		} while (true);
		escribir("MiArchivo.txt", texto);
		leer("MiArchivo.txt");

		teclado.close();
		System.out.println("Fin del Programa");
	}

	static boolean escribir(String directorio, List<String> texto) {
		// se declaran e instancias los ficheros
		File archivoEscritura = new File(directorio);
		// declaramos e instanciamos el objeto que escribira el archivo aprovechando el
		// try-with-resources
		try (BufferedWriter escribirFichero = new BufferedWriter(new FileWriter(archivoEscritura, true))) {

			for (String linea : texto) {
				escribirFichero.write(linea);
				// No todas las plataformas usan el carácter de nueva línea ('\n') para
				// terminar líneas. Por lo tanto, se prefiere llamar a este método para terminar
				// cada línea de salida a escribir un carácter de nueva línea directamente.
				escribirFichero.newLine();
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	static void leer(String directorio) {
		// se declaran e instancias los ficheros
		File archivoLectura = new File(directorio);
		// declaramos e instanciamos el objeto que leera el archivo aprovechando el
		// try-with-resources

		// el constructor espera un objeto Reader
		try (BufferedReader leerFichero = new BufferedReader(new FileReader(archivoLectura))) {
			// al tratar de leer el archivo el metodo read retornara la cadena de caracteres
			// correspondientes de lo contrario retornara null si llego al final de la
			// lectura
			String mensaje = null;

			// preguntamos si el archivo esta listo para la lectura
			if (leerFichero.ready()) {
				while ((mensaje = leerFichero.readLine()) != null) {
					System.out.println(mensaje);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
