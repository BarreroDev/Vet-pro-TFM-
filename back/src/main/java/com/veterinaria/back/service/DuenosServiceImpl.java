package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.veterinaria.back.model.Dueno;

/**
 * Implementación de la lógica de negocio para los dueños.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Service
public class DuenosServiceImpl implements DuenosService {

	@Override
	public List<Dueno> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Dueno> obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Dueno> obtenerPorDni(String dni) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Dueno guardar(Dueno dueno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dueno actualizar(Long id, Dueno duenoActualizado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminiarporId(Long id) {
		// TODO Auto-generated method stub

	}

}
