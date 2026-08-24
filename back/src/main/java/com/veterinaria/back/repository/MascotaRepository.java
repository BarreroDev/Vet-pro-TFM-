package com.veterinaria.back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinaria.back.model.Mascota;
/**
 * Interfaz de acceso a datos para la entidad Dueno.
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
	
	/**
	 * Obtine la lista de mascotas que tiene un dueño filtrando por su dni.
	 * @param dni del dueño asociado.
	 * @return lista de mascotas del dueño.
	 */
	List<Mascota> findByDuenoDni(String dni);
	
	/**
     * Obtiene la lista de mascotas asociadas a un Id de dueño.
     * 
     * @param duenoId Identificador primario del dueño.
     * @return Lista de mascotas.
     */
    List<Mascota> findByDuenoId(Long duenoId);

}
