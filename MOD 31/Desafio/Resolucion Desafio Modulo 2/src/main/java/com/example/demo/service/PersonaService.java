package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

public class PersonaService {
	private static PersonaService instance = new PersonaService();
	private PersonaRepository personaRepository;
	
	private PersonaService() {
		personaRepository = new PersonaRepository();
	};
	
	public static PersonaService getInstance() {
		return instance;
	}

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Persona findById(Long id) {
        return personaRepository.findById(id);
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }

}
