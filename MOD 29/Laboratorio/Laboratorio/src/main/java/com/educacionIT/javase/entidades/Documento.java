package com.educacionIT.javase.entidades;

import com.educacionIT.javase.enumerados.TiposDocumento;

public final class Documento {
	private TiposDocumento tipo;
	private Integer numero;

	/**
	 * 
	 */
	public Documento() {
		super();
	}

	/**
	 * @param tipo
	 * @param numero
	 */
	public Documento(TiposDocumento tipo, Integer numero) {
		super();
		this.tipo = tipo;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Documento [tipo=" + tipo.getDescripcion() + ", numero=" + numero + "]";
	}

	public TiposDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TiposDocumento tipo) {
		this.tipo = tipo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	

}
