package com.educacionit.principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.entidades.Usuario;

public class Principal {
	public static void main(String[] args) {
		LocalDate fechaMinima = LocalDate.parse("2020-01-01");

		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("user1@educacionit.com", "1234", LocalDate.parse("2018-05-15"), true));
		usuarios.add(new Usuario("user2@educacionit.com", "9875", LocalDate.parse("2020-01-02"), true));
		usuarios.add(new Usuario("user3@educacionit.com", "5698", LocalDate.parse("2021-07-16"), false));
		usuarios.add(new Usuario("user4@educacionit.com", "8523", LocalDate.parse("2020-08-30"), true));
		usuarios.add(new Usuario("user5@educacionit.com", "7532", LocalDate.parse("2021-02-28"), false));
		usuarios.add(new Usuario("user6@educacionit.com", "9876", LocalDate.parse("2000-01-01"), true));
		usuarios.add(new Usuario("user7@educacionit.com", "2569", LocalDate.parse("2013-12-01"), true));
		usuarios.add(new Usuario("user8@educacionit.com", "1111", LocalDate.parse("2025-11-19"), true));
		usuarios.add(new Usuario("user9@educacionit.com", "9872", LocalDate.parse("2022-04-03"), false));

		// isBefore metodo para verificar si la instancia de fecha esta antes de la
		// enviada como argumento
		String correos = usuarios.stream().filter(u -> fechaMinima.isBefore(u.getFechaCreacion()) && u.getActivo())
				.map(u -> u.getCorreo().toUpperCase())
				.reduce("Correos que estan activos y la fecha de creacion es mayor a " + fechaMinima + ":",
						(c1, c2) -> c1.concat("\n\t ").concat(c2));
		System.out.println(correos);

	}
}
