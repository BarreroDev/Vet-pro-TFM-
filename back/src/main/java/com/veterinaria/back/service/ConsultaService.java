package com.veterinaria.back.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import com.veterinaria.back.model.Consulta;

/**
 * Interfaz que define los servicios de lógica de negocio para la gestión de citas y consultas médicas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
public interface ConsultaService {
	
	List<Consulta> obtenerTodas();
	
	Optional<Consulta> obtenerPorId(Long Id);
	
	List<Consulta> obtenerPorMascota(Long mascotaId);
	
	List<Consulta> obtenerPorRangoFechas(LocalDateTime incio, LocalDateTime fin);
	
	Consulta guardar(Consulta consulta, Long mascotaId, Long veterinarioId);
	
	Consulta actualizarEstado(Long id, String nuevoEstado, String observaciones);
	
	void eliminarPorId(Long id);

}
