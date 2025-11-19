package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.model.Persona;

/**
 * Clase PersonaRepository - Capa de acceso a datos para Personas
 * Esta clase simula una base de datos almacenando datos en memoria (ArrayList)
 * Implementa operaciones CRUD básicas (Create, Read, Update, Delete)
 * NOTA: Los datos NO son persistentes, se pierden al reiniciar la aplicación
 */
public class PersonaRepository {
	
	// ArrayList que funciona como "base de datos" en memoria para almacenar personas
	// Cada vez que se reinicia la app, esta lista se vacía
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	
	// Contador para generar IDs autoincrementales
	// Comienza en 1 y se incrementa cada vez que se guarda una nueva persona
	private Long currentId = 1L;
	
	/**
	 * Método findAll - Obtiene todas las personas almacenadas
	 * @return List<Persona> con todas las personas en el sistema
	 */
	public List<Persona> findAll() {
        return personas;  // Retorna la lista completa
    }

	/**
	 * Método findById - Busca una persona específica por su ID
	 * @param id - identificador único de la persona a buscar
	 * @return Persona encontrada, o null si no existe
	 */
    public Persona findById(Long id) {
    	// Recorre toda la lista de personas
    	for (Persona persona : personas) {
    		// Compara el ID buscado con el ID de cada persona usando equals()
			if (id != null && id.equals(persona.getId())) {
				return persona;  // Si lo encuentra, retorna la persona
			}
		}
        return null;  // Si no encuentra ninguna, retorna null
    }

    /**
     * Método save - Guarda una nueva persona en la lista
     * Si la persona no tiene ID (es null), se le asigna uno automáticamente
     * Si ya tiene ID, se considera una actualización
     * @param persona - objeto Persona a guardar
     * @return Persona guardada con su ID asignado
     */
    public Persona save(Persona persona) {
    	// Si el ID es null, es una persona nueva y se le asigna un ID autoincremental
    	if (persona.getId() == null) {
    		persona.setId(currentId);  // Asigna el ID actual
    		currentId++;  // Incrementa el contador para el siguiente registro
    		personas.add(persona);  // Agrega la persona al ArrayList
    	} else {
    		// Si ya tiene ID, busca si existe para actualizar o agregar
    		Persona existente = findById(persona.getId());
    		if (existente == null) {
    			// Si no existe, la agrega como nueva
    			personas.add(persona);
    		}
    		// Si existe, los cambios ya se reflejan por referencia (no hace nada)
    	}
        return persona;  // Retorna la persona guardada con su ID
    }

    /**
     * Método deleteById - Elimina una persona por su ID
     * @param id - identificador de la persona a eliminar
     */
    public void deleteById(Long id) {
    	// Primero busca la persona por ID
    	Persona persona = findById(id);
    	// Si la persona existe (no es null)
    	if (persona != null) {
    		personas.remove(persona);  // La elimina de la lista
		}
		// Si no existe, no hace nada
    }

}
