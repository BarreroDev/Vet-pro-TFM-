package com.veterinaria.back.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.back.model.Consulta;
/**
 * Interfaz de acceso a datos para la entidad Dueno.
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	/**
     * Obtiene todas las consultas programadas para una mascota concreta.
     * 
     * @param mascotaId ID de la mascota.
     * @return Lista de citas de esa mascota.
     */
    List<Consulta> findByMascotaId(Long mascotaId);

    /**
     * Obtiene las citas asignadas a un veterinario concreto.
     * 
     * @param veterinarioId ID del veterinario.
     * @return Lista de citas del veterinario.
     */
    List<Consulta> findByVeterinarioId(Long veterinarioId);

    /**
     * Busca citas programadas dentro de un rango de fechas (útil para la vista mensual/semanal de FullCalendar).
     * 
     * @param inicio Fecha/hora de inicio del rango.
     * @param fin Fecha/hora de fin del rango.
     * @return Lista de citas comprendidas entre ambas fechas.
     */
    List<Consulta> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
