package com.example.demo.controller;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase PersonaController - Controlador REST para gestionar Personas
 * Esta clase expone endpoints HTTP para operaciones CRUD sobre personas
 * Todas las rutas comienzan con /personas
 * Maneja las peticiones HTTP y delega la lógica de negocio al servicio
 */
@RestController  // Indica que esta clase es un controlador REST (retorna JSON automáticamente)
@RequestMapping("/personas")  // Todas las rutas de este controller empiezan con /personas
public class PersonaController {

	// Obtiene la instancia única del servicio (patrón Singleton)
    private PersonaService personaService = PersonaService.getInstance();

    /**
     * Endpoint GET /personas - Obtiene todas las personas
     * Método HTTP: GET
     * URL completa: http://localhost:8080/personas
     * @return List<Persona> - Lista con todas las personas en formato JSON
     */
    @GetMapping  // Mapea peticiones GET a /personas
    public List<Persona> getAllPersonas() {
        // Llama al servicio para obtener todas las personas
        return personaService.findAll();
    }

    /**
     * Endpoint GET /personas/{id} - Obtiene una persona específica por ID
     * Método HTTP: GET
     * URL ejemplo: http://localhost:8080/personas/1
     * @param id - ID de la persona extraído de la URL
     * @return ResponseEntity<Persona> - Persona encontrada con código HTTP 200
     */
    @GetMapping("/{id}")  // {id} es una variable de ruta que se mapea al parámetro
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        // @PathVariable extrae el {id} de la URL y lo convierte a Long
        Persona persona = personaService.findBy(id);
        // Retorna la persona con código de estado 200 OK
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    /**
     * Endpoint POST /personas - Crea una nueva persona
     * Método HTTP: POST
     * URL: http://localhost:8080/personas
     * Body: JSON con los datos de la persona
     * @param persona - Objeto Persona creado automáticamente del JSON recibido
     * @return ResponseEntity<Persona> - Persona creada con código HTTP 201
     */
    @PostMapping  // Mapea peticiones POST (para crear recursos)
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        // @RequestBody convierte el JSON recibido en un objeto Persona
        Persona savedPersona = personaService.save(persona);
        // Retorna la persona guardada con código 201 CREATED
        return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);
    }

    /**
     * Endpoint PUT /personas/{id} - Actualiza una persona existente
     * Método HTTP: PUT
     * URL ejemplo: http://localhost:8080/personas/1
     * Body: JSON con los nuevos datos
     * @param id - ID de la persona a actualizar
     * @param personaDetails - Objeto con los nuevos datos
     * @return ResponseEntity<Persona> - Persona actualizada o 404 si no existe
     */
    @PutMapping("/{id}")  // Mapea peticiones PUT (para actualizar)
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        // Busca la persona existente por ID
        Persona existingPersona = personaService.findBy(id);
        
        // Verifica si la persona existe
        if (existingPersona != null) {
            // Actualiza todos los campos con los nuevos valores
            existingPersona.setNombre(personaDetails.getNombre());
            existingPersona.setEdad(personaDetails.getEdad());
            existingPersona.setDireccion(personaDetails.getDireccion());
            existingPersona.setTelefono(personaDetails.getTelefono());
            existingPersona.setEmail(personaDetails.getEmail());
            // Retorna la persona actualizada con código 200 OK
            return ResponseEntity.ok(existingPersona);
        } else {
            // Si no existe, retorna código 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint DELETE /personas/{id} - Elimina una persona
     * Método HTTP: DELETE
     * URL ejemplo: http://localhost:8080/personas/1
     * @param id - ID de la persona a eliminar
     * @return ResponseEntity<Void> - Código 204 si se eliminó, 404 si no existe
     */
    @DeleteMapping("/{id}")  // Mapea peticiones DELETE (para eliminar)
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        // Verifica si la persona existe antes de eliminar
        if (personaService.findBy(id) != null) {
            // Si existe, la elimina
            personaService.deleteBy(id);
            // Retorna código 204 NO CONTENT (eliminación exitosa, sin contenido que retornar)
            return ResponseEntity.noContent().build();
        } else {
            // Si no existe, retorna código 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }
}
