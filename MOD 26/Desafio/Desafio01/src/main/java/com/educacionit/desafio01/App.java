package com.educacionit.desafio01;

import java.util.List;
import java.util.Scanner;

import com.educacionit.desafio01.interfaces.Archivo;

public class App {
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		String archivo = "Java.txt";
		String palabra;
		boolean existe = false;

		System.out.print("Indique la palabra a buscar: ");
		palabra = teclado.next();

		List<String> texto = Archivo.leer(archivo);

		for (int i = 0; i < texto.size(); i++) {
			String linea = texto.get(i);

			if (linea.contains(palabra)) {
				String nuevaPalabra = "<<" + palabra + ">>";
				System.out.println("La palabra " + nuevaPalabra + " se encuentra en la fila " + (i + 1)
						+ " y comienza en la columna " + linea.indexOf(palabra));

				texto.set(i, linea.replace(palabra, nuevaPalabra));
				existe = true;
			}
		}

		if (!existe) {
			System.out.println("La palabra indicada no existe en el archivo");
		}

		Archivo.escribir(archivo, texto);

		teclado.close();
	}

}
