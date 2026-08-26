package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import com.veterinaria.back.model.Mascota;

/**
 * Interfaz que define los servicios de lógica de negocio para la gestión de mascotas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

public interface MascotasService {

	List<Mascota> obtenerTodas();
	
	Optional<Mascota> obtenerPorId(Long id);
	
	List<Mascota> obtenerPorDniDueno(String dni);
	
	Mascota guardar(Mascota mascota, String dniDueno);
	
	Mascota actualizar(Long id, Mascota mascotaActualizada);
	
	void eliminarPorId(Long id);
}
