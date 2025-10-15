package com.educacionit.desafio01.interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Archivo {

	static List<String> leer(String directorio) {
		List<String> texto = new ArrayList<>();
		File archivoLectura = new File(directorio);

		try (BufferedReader leerFichero = new BufferedReader(new FileReader(archivoLectura))) {

			String mensaje = null;

			if (leerFichero.ready()) {
				while ((mensaje = leerFichero.readLine()) != null) {
					texto.add(mensaje);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texto;
	}

	static boolean escribir(String directorio, List<String> texto) {

		File archivoEscritura = new File(directorio);

		try (BufferedWriter escribirFichero = new BufferedWriter(new FileWriter(archivoEscritura, false))) {

			for (String linea : texto) {
				escribirFichero.write(linea);
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
}
