package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.RandomUserPhone;
import com.example.demo.service.RandomUserService;

/**
 * Clase RandomUserController - Controlador REST para obtener usuarios aleatorios
 * Este controlador expone un endpoint que consume la API de RandomUser
 * y retorna únicamente los números de teléfono (phone y cell)
 */
@RestController
@RequestMapping("/random-user")
public class RandomUserController {
	
	// Inyecta el servicio de RandomUser
	@Autowired
	private RandomUserService randomUserService;
	
	/**
	 * Endpoint GET /random-user/phone - Obtiene teléfonos de un usuario aleatorio
	 * Método HTTP: GET
	 * URL completa: http://localhost:8080/random-user/phone
	 * Consume la API externa https://randomuser.me/api/
	 * @return ResponseEntity<RandomUserPhone> con phone y cell
	 */
	@GetMapping("/phone")
	public ResponseEntity<?> getRandomUserPhone() {
		try {
			// Llama al servicio para obtener los teléfonos
			RandomUserPhone userPhone = randomUserService.getRandomUserPhone();
			
			// Retorna los teléfonos con código 200 OK
			return new ResponseEntity<>(userPhone, HttpStatus.OK);
			
		} catch (Exception e) {
			// Si hay error, retorna un mensaje de error con código 500
			return new ResponseEntity<>(
				"Error al obtener usuario aleatorio: " + e.getMessage(), 
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
}


