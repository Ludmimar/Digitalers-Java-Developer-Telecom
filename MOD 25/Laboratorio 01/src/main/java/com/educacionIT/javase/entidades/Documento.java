package com.educacionIT.javase.entidades;

import com.educacionIT.javase.enumerados.TiposDocumento;

/**
 * Clase final que representa un documento de identidad.
 * 
 * Esta clase encapsula la información de un documento de identidad,
 * incluyendo el tipo de documento (DNI, Pasaporte, etc.) y el número
 * correspondiente. Es una clase final que no puede ser extendida,
 * garantizando la integridad de la representación del documento.
 * 
 * Características principales:
 * - Clase final (no puede ser heredada)
 * - Utiliza composición con el enum TiposDocumento
 * - Implementa equals() y hashCode() para comparaciones
 * - Proporciona representación en cadena formateada
 * 
 * @author Ludmila Martos
 * @version 1.0
 * @since 2025
 */
public final class Documento {
	
	// Atributos de la clase Documento
	private TiposDocumento tipo;    // Tipo de documento (enum)
	private Integer numero;          // Número del documento

	/**
	 * Constructor por defecto de Documento.
	 * Inicializa todos los atributos con valores por defecto (null).
	 */
	public Documento() {
		super();
	}

	/**
	 * Constructor parametrizado de Documento.
	 * 
	 * @param tipo Tipo de documento (DNI, PAS, LE, CI)
	 * @param numero Número del documento
	 */
	public Documento(TiposDocumento tipo, Integer numero) {
		super();
		this.tipo = tipo;
		this.numero = numero;
	}

	/**
	 * Representación en cadena del objeto Documento.
	 * 
	 * Muestra el tipo de documento con su descripción completa
	 * y el número del documento de forma legible.
	 * 
	 * @return String con la información del documento formateada
	 */
	@Override
	public String toString() {
		return "Documento [tipo=" + tipo.getDescripcion() + ", numero=" + numero + "]";
	}

	// Métodos getter y setter para el atributo tipo
	public TiposDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TiposDocumento tipo) {
		this.tipo = tipo;
	}

	// Métodos getter y setter para el atributo numero
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Calcula el código hash del objeto basándose en tipo y número.
	 * 
	 * Dos documentos con el mismo tipo y número tendrán el mismo hash code,
	 * lo que es útil para colecciones como HashSet o HashMap.
	 * 
	 * @return int código hash del objeto
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	/**
	 * Compara dos objetos Documento para determinar si son iguales.
	 * 
	 * Dos documentos se consideran iguales si tienen el mismo tipo
	 * y el mismo número. Esto es importante para evitar duplicados
	 * y para la identificación única de personas.
	 * 
	 * @param obj Objeto a comparar
	 * @return true si los documentos son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		// Verifica si es el mismo objeto en memoria
		if (this == obj)
			return true;
		
		// Verifica si el objeto es null
		if (obj == null)
			return false;
		
		// Verifica si son de la misma clase
		if (getClass() != obj.getClass())
			return false;
		
		// Convierte el objeto a Documento para comparar
		Documento other = (Documento) obj;
		
		// Compara el número del documento
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		
		// Compara el tipo de documento
		if (tipo != other.tipo)
			return false;
		
		return true;
	}
	
	

}
