package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

/**
 * Clase PersonaService - Capa de lógica de negocio para Personas
 * Esta clase implementa el patrón Singleton (una sola instancia en toda la app)
 * Actúa como intermediario entre el Controller y el Repository
 * Aquí se puede agregar validaciones, reglas de negocio, y procesamiento adicional
 */
public class PersonaService {
	// Instancia única del servicio (patrón Singleton)
	// Se crea solo una vez cuando se carga la clase
	private static PersonaService instance = new PersonaService();
	
	// Repositorio para acceder a los datos de personas
	private PersonaRepository personaRepository;
	
	/**
	 * Constructor privado - Parte del patrón Singleton
	 * Al ser privado, nadie puede crear instancias con "new"
	 * Solo se puede acceder a través del método getInstance()
	 */
	private PersonaService() {
		// Inicializa el repositorio al crear el servicio
		personaRepository = new PersonaRepository();
	};
	
	/**
	 * Método getInstance - Retorna la única instancia del servicio
	 * Este es el punto de acceso global al servicio (patrón Singleton)
	 * @return PersonaService - la instancia única
	 */
	public static PersonaService getInstance() {
		return instance;
	}

	/**
	 * Método findAll - Obtiene todas las personas
	 * Delega la operación al repositorio
	 * @return List<Persona> con todas las personas
	 */
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    /**
     * Método findBy - Busca una persona por ID
     * Aquí se podría agregar lógica adicional (ej: logging, validaciones)
     * @param id - identificador de la persona
     * @return Persona encontrada o null
     */
    public Persona findBy(Long id) {
        return personaRepository.findById(id);
    }

    /**
     * Método save - Guarda una nueva persona
     * Aquí se pueden agregar validaciones antes de guardar
     * (ej: verificar que el email sea válido, que no exista ya, etc.)
     * @param persona - objeto Persona a guardar
     * @return Persona guardada
     */
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    /**
     * Método deleteBy - Elimina una persona por ID
     * Se podría agregar lógica de seguridad o verificaciones
     * @param id - identificador de la persona a eliminar
     */
    public void deleteBy(Long id) {
        personaRepository.deleteById(id);
    }

}
