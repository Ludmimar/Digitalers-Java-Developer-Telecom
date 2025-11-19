package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.model.Persona;

public class PersonaRepository {
	
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	
	public List<Persona> findAll() {
        return personas;
    }

    public Persona findById(Long id) {
    	for (Persona persona : personas) {
			if (id == persona.getId()) {
				return persona;
			}
		}
        return null;
    }

    public Persona save(Persona persona) {
    	personas.add(persona);
        return persona;
    }

    public void deleteById(Long id) {
    	Persona persona = findById(id);
    	if (persona != null) {
    		personas.remove(persona);
		}
    }

}
