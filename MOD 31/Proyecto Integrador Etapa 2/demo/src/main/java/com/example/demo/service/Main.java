package com.example.demo.service;

import com.example.demo.model.Usuario;

public class Main {

	public static void main(String[] args) {
		Usuario u = new UsuarioService().getRandomUser();
		System.out.println(u.getNombre());
		System.out.println(u.getApellido());
		System.out.println(u.getEmail());

	}

}
