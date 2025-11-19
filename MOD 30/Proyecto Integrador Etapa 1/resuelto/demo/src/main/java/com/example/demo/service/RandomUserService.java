package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.RandomUserPhone;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase RandomUserService - Servicio para consumir la API de RandomUser
 * Este servicio se conecta a https://randomuser.me/api/ para obtener datos aleatorios
 * Extrae únicamente los campos phone y cell de la respuesta
 */
@Service
public class RandomUserService {
	
	// URL de la API externa de RandomUser
	private static final String RANDOM_USER_API = "https://randomuser.me/api/";
	
	// RestTemplate para hacer peticiones HTTP
	private final RestTemplate restTemplate;
	
	// ObjectMapper para parsear JSON
	private final ObjectMapper objectMapper;
	
	/**
	 * Constructor - Inicializa RestTemplate y ObjectMapper
	 */
	public RandomUserService() {
		this.restTemplate = new RestTemplate();
		this.objectMapper = new ObjectMapper();
	}
	
	/**
	 * Método getRandomUserPhone - Obtiene teléfonos de un usuario aleatorio
	 * Consume la API de RandomUser y extrae solo phone y cell
	 * @return RandomUserPhone con los números de teléfono
	 * @throws Exception si hay error en la petición o parseo
	 */
	public RandomUserPhone getRandomUserPhone() throws Exception {
		try {
			// Hace la petición GET a la API externa
			System.out.println("DEBUG: Llamando a la API de RandomUser...");
			String response = restTemplate.getForObject(RANDOM_USER_API, String.class);
			
			// Parsea el JSON de respuesta
			JsonNode root = objectMapper.readTree(response);
			
			// Navega por el JSON para extraer phone y cell
			// La estructura es: {"results": [{"phone": "...", "cell": "..."}]}
			JsonNode results = root.path("results");
			
			if (results.isArray() && results.size() > 0) {
				JsonNode user = results.get(0); // Toma el primer usuario
				String phone = user.path("phone").asText();
				String cell = user.path("cell").asText();
				
				System.out.println("DEBUG: Phone obtenido: " + phone);
				System.out.println("DEBUG: Cell obtenido: " + cell);
				
				// Crea y retorna el objeto con los teléfonos
				return new RandomUserPhone(phone, cell);
			} else {
				throw new Exception("No se encontraron resultados en la API");
			}
			
		} catch (Exception e) {
			System.err.println("ERROR: Error al consumir la API de RandomUser: " + e.getMessage());
			throw new Exception("Error al obtener usuario aleatorio: " + e.getMessage());
		}
	}
}


