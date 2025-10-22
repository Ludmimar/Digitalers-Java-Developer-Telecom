package com.educacionIT.javase.interfaces;

import java.util.HashMap;
import java.util.Map;

public interface Constantes {
	String[] tipoDocumento = { "DNI", "PAS", "LE", "CI" };
	
	
	static Map<Integer, String> getCursos() {
		Map<Integer, String> cursos = new HashMap<Integer, String>();
		Integer id = 1;
		
		cursos.put(id++, "JAVA");
		cursos.put(id++, "PYTHON");
		cursos.put(id++, "SQL");
		cursos.put(id++, "JAVASCRIPT");
		cursos.put(id++, "REACT");
		
		return cursos;
	}
}
