package com.veterinaria.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.back.model.Veterinario;
import java.util.List;

/**
 * Interfaz de acceso a datos para la entidad Dueno.
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

	/**
	 * Buscar aun veterinario por su nombre.
	 * @param nombre del veterinario.
	 * @return entidad ya existente.
	 */
	Optional<Veterinario> findByNombre(String nombre);
	
	/**
	 * Buscar a un veterinario por su numero de cologiado.
	 * @param numeroColegiado de profesional.
	 * @return entidad ya existente.
	 */
	Optional<Veterinario> findByNumeroColegiado(String numeroColegiado);
	
	/**
	 * Comprueba si existe un veterinario con el mismo numero.
	 * @param numeroColegiado número a verificar.
	 * @return true si ya está registrado, false en caso contrario.
	 */
	boolean existsByNumeroColegiado(String numeroColegiado);
}
