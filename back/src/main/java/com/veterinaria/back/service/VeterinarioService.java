package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.back.model.Veterinario;
import com.veterinaria.back.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired VeterinarioRepository veterinarioRepository;
	
	//Método para guardar un nuevo veterinario.
	public Veterinario nuevoVeterinario(Veterinario veterinario) {
		return veterinarioRepository.save(veterinario);
	}
	
	//Método para listar veterinarios.
	public List<Veterinario> obtenerTodos(){
		return veterinarioRepository.findAll();
	}
	
	// Buscar un veterinario por su número de colegiado
	public Optional<Veterinario> obtenerPorNumeroColegiado(String numeroColegiado) {
	    return veterinarioRepository.findByNumeroColegiado(numeroColegiado);
	}
	
	//Método para eliminar veterio.
	public void eliminarVeterinario(Long id) {
        veterinarioRepository.deleteById(id);
    }
	
	
}
