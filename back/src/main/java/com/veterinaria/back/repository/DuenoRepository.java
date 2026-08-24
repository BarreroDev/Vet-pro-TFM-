package com.veterinaria.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.back.model.Dueno;
import java.util.List;
import java.util.Optional;


/**
 * Interfaz de acceso a datos para la entidad Dueno.
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {

	/**
	 * Busca a un dueño por su Dni.
	 * @param dni del dueño.
	 * @return Contenedor con el dueño si existe 
	 */
	Optional<Dueno> findByDni(String dni);
	
	/**
	 * Comprueba si el dueño ya existe.
	 * @param dni a verificar
	 * @return true si existe, false en caso contrario.
	 */
	boolean existsByDni(String dni);
}
