package com.educacionit.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ValidarPatron {

	static boolean esCorreo(String correo) {
		Pattern patron = Pattern.compile("([a-z0-9]+(.?[a-z0-9])*)+@(([a-z]+).([a-z]+))");

		Matcher comparar = patron.matcher(correo.trim().toLowerCase());

		boolean esCorreo = comparar.find();

		if (esCorreo && !correo.trim().toLowerCase().endsWith("educacionit.com")) {
			return false;
		}

		return esCorreo;
	}

	static boolean esClave(String clave) {
		Pattern patron = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");

		Matcher comparar = patron.matcher(clave);

		return comparar.find();
	}

}
