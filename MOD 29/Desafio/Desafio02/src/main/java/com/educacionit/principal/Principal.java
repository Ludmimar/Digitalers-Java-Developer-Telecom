package com.educacionit.principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.educacionit.entidades.Usuario;
import com.educacionit.interfaces.ValidarPatron;

public class Principal {
	public static void main(String[] args) {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Usuario> usuariosNoSonEducacionIT = new ArrayList<Usuario>();
		usuarios.add(new Usuario("user1@educacionit.com", "Abcd1234", LocalDate.parse("2018-05-15"), true));
		usuarios.add(new Usuario("user2@educacionit.com", "Abcd891234", LocalDate.parse("2020-01-02"), true));
		usuarios.add(new Usuario("user3@educacionit.com", "Abcd1234", LocalDate.parse("2021-07-16"), false));
		usuarios.add(new Usuario("user4@gmail.com", "8523", LocalDate.parse("2020-08-30"), true));
		usuarios.add(new Usuario("user5@hotmail.com", "7532", LocalDate.parse("2021-02-28"), false));
		usuarios.add(new Usuario("user6@yahoo.es", "9876", LocalDate.parse("2000-01-01"), true));
		usuarios.add(new Usuario("user7@educacionit.com", "Abcd14", LocalDate.parse("2013-12-01"), true));
		usuarios.add(new Usuario("user8@educacionit.com", "1234", LocalDate.parse("2025-11-19"), true));
		usuarios.add(
				new Usuario("user9@educacionit.com", "Abcd198743321613221616", LocalDate.parse("2022-04-03"), false));

		List<Usuario> usuariosEducacionIT = usuarios.stream().filter(u -> ValidarPatron.esCorreo(u.getCorreo()))
				.collect(Collectors.toList());

		usuariosNoSonEducacionIT.addAll(usuarios);
		usuariosNoSonEducacionIT.removeAll(usuariosEducacionIT);

		System.out.println(
				"Los siguientes correos no cumplen con la contraseña valida  (Debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula. NO puede tener otros símbolos):");
		usuariosEducacionIT.stream().filter(u -> ValidarPatron.esClave(u.getClave()))
				.forEach(u -> System.out.println("\t" + u.getCorreo()));

	}
}
