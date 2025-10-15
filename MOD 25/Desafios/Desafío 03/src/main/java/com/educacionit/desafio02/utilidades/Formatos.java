package com.educacionit.desafio02.utilidades;

public class Formatos {
	public static final String FORMATO_CUENTA;
	public static final String FORMATO_CBU;
	public static final String FORMATO_TC;
	public static final String FORMATO_BANCO_SUCURSAL;
	public static final String FORMATO_CODIGO_SEGURIDAD_TC;
	static {
		FORMATO_CUENTA = "###-#-#####/#";
		FORMATO_CBU = "######## ##############";
		FORMATO_TC = "#### #### #### ####";
		FORMATO_BANCO_SUCURSAL = "####";
		FORMATO_CODIGO_SEGURIDAD_TC = "###";
	}

	public static String getFormato(Integer numero, String tipo) {
		String formato = "";
		switch (tipo.toUpperCase()) {
		case "CC":
		case "CA":
			formato = getNumero(numero, FORMATO_CUENTA);
			break;
		case "CBU":
			formato = getNumero(numero, FORMATO_CBU);
			break;
		case "TC":
			formato = getNumero(numero, FORMATO_TC);
			break;
		case "BANCO":
		case "SUCURSAL":
			formato = getNumero(numero, FORMATO_BANCO_SUCURSAL);
			break;
		case "CS":
			formato = getNumero(numero, FORMATO_CODIGO_SEGURIDAD_TC);
			break;
		default:
			break;
		}
		return formato;
	}

	private static String getNumero(Integer numero, String formato) {
		// Verificamos la longitud que debe tener nuestro numero
		int cantidadCeros = contarCaracteres(formato);

		// completamos con ceros segun la longitud obtenida
		String numeroCadena = String.format("%0" + cantidadCeros + "d", numero);
		String numeroFinal = "";
		int indiceNumeroCadena = 0;

		// ciclamos el formato para poder sustituir los valores
		for (int i = 0; i < formato.length(); i++) {
			numeroFinal += formato.charAt(i);
			// solo reemplazamos los numerales
			if (formato.charAt(i) == '#') {
				numeroFinal = numeroFinal.replace("#", String.valueOf(numeroCadena.charAt(indiceNumeroCadena)));
				// esto nos garantiza que si el formato tiene un caracter especial no nos
				// movamos en el numero original y nos de error
				indiceNumeroCadena++;
			}
		}

		return numeroFinal;
	}

	private static int contarCaracteres(String formato) {
		int cantidad = 0;
		for (int i = 0; i < formato.length(); i++) {
			if (formato.charAt(i) == '#') {
				cantidad++;
			}
		}
		return cantidad;
	}

}
