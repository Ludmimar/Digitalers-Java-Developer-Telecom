package com.educacionIT.javase.excepciones;

import java.util.Arrays;

import com.educacionIT.javase.interfaces.Constantes;

/**
 * Excepción personalizada para manejar errores específicos del sistema de personas.
 * 
 * Esta clase extiende de Exception y proporciona un mecanismo para manejar
 * errores específicos relacionados con la gestión de personas en el sistema educativo.
 * Permite asociar códigos de error con mensajes descriptivos para facilitar
 * el diagnóstico y la corrección de problemas.
 * 
 * Características principales:
 * - Extiende de Exception (excepción verificada)
 * - Maneja códigos de error personalizados
 * - Proporciona mensajes descriptivos según el código
 * - Integra con las constantes del sistema
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public class ExcepcionPersona extends Exception {
	
	// Número de versión para la serialización
	private static final long serialVersionUID = 1L;
	
	// Código de error asociado a la excepción
	private Integer codigo;

	/**
	 * Constructor que acepta un código de error.
	 * 
	 * @param codigo Código numérico que identifica el tipo de error
	 */
	public ExcepcionPersona(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Constructor que acepta un mensaje personalizado.
	 * 
	 * @param message Mensaje descriptivo del error
	 */
	public ExcepcionPersona(String message) {
		super(message);
	}

	/**
	 * Sobrescribe el método getMessage() para proporcionar mensajes
	 * específicos según el código de error.
	 * 
	 * Este método permite que la excepción proporcione información
	 * contextual sobre el error, incluyendo los tipos de documento
	 * válidos cuando sea apropiado.
	 * 
	 * @return String con el mensaje de error correspondiente al código
	 */
	@Override
	public String getMessage() {
		// Evalúa el código de error y retorna el mensaje apropiado
		switch (codigo) {
		case 1:
			// Código 1: Error de tipo de documento inválido
			// Retorna los tipos de documento válidos del sistema
			return "Los Documentos validos son: " + Arrays.toString(Constantes.tipoDocumento);
		default:
			// Para cualquier otro código, retorna el mensaje por defecto
			return super.getMessage();
		}
	}

}
