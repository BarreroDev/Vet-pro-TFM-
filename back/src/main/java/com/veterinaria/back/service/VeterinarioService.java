package com.veterinaria.back.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

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
	
	Veterinario guardarFoto(Veterinario veterinario, MultipartFile file) throws IOException;

}