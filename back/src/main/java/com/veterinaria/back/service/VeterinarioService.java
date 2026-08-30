package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.back.model.Veterinario;

/**
 * Interfaz que define los servicios de lógica de negocio para la gestión de veterinarios.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
public interface VeterinarioService {
	
	List<Veterinario> obtenerTodos();
	
	Optional<Veterinario> obtenerPorId(Long id);
	
	Optional<Veterinario> obtenerPorNumeroColegiado(String numeroColegiado);
	
	Veterinario guardar(Veterinario veterinario);
	
	Veterinario actualizar(Long id, Veterinario veterinarioActializado);
	
	void eliminarPorId(Long id);

}