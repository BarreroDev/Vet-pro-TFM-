package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.back.model.Veterinario;

public class VeterinarioServiceImpl implements VeterinarioService {

	@Override
	public List<Veterinario> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Veterinario> obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Veterinario> obtenerPorNumeroColegiado(String numeroColegiado) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Veterinario guardar(Veterinario veterinario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Veterinario actualizar(Long id, Veterinario veterinarioActializado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarPorId(Long id) {
		// TODO Auto-generated method stub

	}

}
