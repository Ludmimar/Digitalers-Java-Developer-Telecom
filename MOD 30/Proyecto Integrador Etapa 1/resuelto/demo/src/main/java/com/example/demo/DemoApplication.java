package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot
 * Esta es la clase de arranque que inicia toda la aplicación
 */
@SpringBootApplication  // Anotación que combina: @Configuration, @EnableAutoConfiguration y @ComponentScan
public class DemoApplication {

	/**
	 * Método main - Punto de entrada de la aplicación
	 * @param args - Argumentos de línea de comandos (opcional)
	 */
	public static void main(String[] args) {
		// Inicia la aplicación Spring Boot y levanta el servidor web embebido (Tomcat por defecto)
		SpringApplication.run(DemoApplication.class, args);
	}

}
