package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.back.model.Dueno;
import com.veterinaria.back.repository.DuenoRepository;

import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de la lógica de negocio para los dueños.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Service
public class DuenosServiceImpl implements DuenosService {

	private final DuenoRepository duenoRepository;
	
	public DuenosServiceImpl(DuenoRepository duenoRepository) {
		this.duenoRepository = duenoRepository;
	}
	
	/**
	 * Método para listar todos los dueños.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Dueno> obtenerTodos() {
		// TODO Auto-generated method stub
		return duenoRepository.findAll();
	}

	/**
	 * Método para filtrar por Id.
	 * Asegurando solo la lectura.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Dueno> obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return duenoRepository.findById(id);
	}

	/**
	 * Método para filtrar por Dni.
	 * Aseguramos solo la lectura.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Dueno> obtenerPorDni(String dni) {
		// TODO Auto-generated method stub
		return duenoRepository.findByDni(dni);
	}

	/**
	 * Método para guardar un nuevo dueño.
	 * si no existe lo inserta y en caso contrario frena y no genera datos duplicados o corruptos.
	 */
	@Override
	@Transactional
	public Dueno guardar(Dueno dueno)  {
		if(duenoRepository.existsByDni(dueno.getDni())) {
		   throw new IllegalArgumentException("Ya existe un dueño registrado con el DNI: " + dueno.getDni());	
		}
		return duenoRepository.save(dueno);
	}

	/**
	 * Método para modificar datos de un dueño.
	 * En el caso de no existir nos avisa.
	 */
	@Override
	@Transactional
	public Dueno actualizar(Long id, Dueno duenoActualizado) {
	    return duenoRepository.findById(id)
	            .map(duenoExistente -> {
	                duenoExistente.setNombre(duenoActualizado.getNombre());
	                duenoExistente.setApellidos(duenoActualizado.getApellidos());
	                duenoExistente.setTelefono(duenoActualizado.getTelefono());
	                duenoExistente.setEmail(duenoActualizado.getEmail());
	                duenoExistente.setFechaNacimiento(duenoActualizado.getFechaNacimiento());
	                duenoExistente.setFotoUrl(duenoActualizado.getFotoUrl());
	                return duenoRepository.save(duenoExistente);
	            })
	            .orElseThrow(() -> new RuntimeException("Dueño no encontrado con ID: " + id));
	}

	/**
	 * Método para eliminar usuario.
	 * 
	 */
	@Override
	@Transactional
	public void eliminiarporId(Long id) {
		if(!duenoRepository.existsById(id)) {
			throw new RuntimeException("No se puede eliminar. Dueño no encontrado con ID: " + id);
        }
		duenoRepository.deleteById(id);
	}

}
