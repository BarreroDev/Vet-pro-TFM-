package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.back.model.Dueno;


/**
 * Interfaz que define los servicios de lógica de negocio para la gestión de dueños.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

public interface DuenosService {

	List<Dueno> obtenerTodos();
	
	Optional<Dueno> obtenerPorId(Long id);
	
	Optional<Dueno> obtenerPorDni(String dni);
	
	Dueno guardar(Dueno dueno);
	
	Dueno actualizar(Long id, Dueno duenoActualizado);
	
	void eliminiarporId(Long id);
	
	
}
