package com.veterinaria.back.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaria.back.model.Consulta;
import com.veterinaria.back.model.Mascota;
import com.veterinaria.back.model.Veterinario;
import com.veterinaria.back.repository.ConsultaRepository;
import com.veterinaria.back.repository.MascotaRepository;
import com.veterinaria.back.repository.VeterinarioRepository;

/**
 * Implementación de la lógica de negocio para la gestión de citas/consultas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@Service
public class ConsultaServiceImpl implements ConsultaService {
	
	private ConsultaRepository consultaRepository;
	private MascotaRepository mascotaRepository;
	private VeterinarioRepository veterinarioRepository;
	
	public ConsultaServiceImpl(ConsultaRepository consultaRepository, 
			MascotaRepository mascotaRepository,
			VeterinarioRepository veterinarioRepository) {
		
		this.consultaRepository = consultaRepository;
		this.mascotaRepository = mascotaRepository;
		this.veterinarioRepository = veterinarioRepository;
		
	}

	/**
	 * Métedo para listar consultas.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Consulta> obtenerTodas() {
		return consultaRepository.findAll();
	}

	/**
	 * Método por si quisiera filtrar por Id.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Consulta> obtenerPorId(Long Id) {
		return consultaRepository.findById(Id);
	}

	/*
	 * Método que lista mascotas por Id dentro de consultas.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Consulta> obtenerPorMascota(Long mascotaId) {
		return consultaRepository.findByMascotaId(mascotaId);
	}

	/**
	 * Método para filtrar por fecha de incio y de fin cuantas consultas se hicieron.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Consulta> obtenerPorRangoFechas(LocalDateTime incio, LocalDateTime fin) {
		return consultaRepository.findByFechaHoraBetween(incio, fin);
	}

	/**
	 * Método para agendar nuevas citas.
	 */
	@Override
	@Transactional
	public Consulta guardar(Consulta consulta, Long mascotaId, Long veterinarioId) {
		
		Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + mascotaId));

        Veterinario vet = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con ID: " + veterinarioId));

        consulta.setMascota(mascota);
        consulta.setVeterinario(vet);
        
        if (consulta.getEstado() == null || consulta.getEstado().isEmpty()) {
            consulta.setEstado("Pendiente");
        }

        return consultaRepository.save(consulta);
	}

	/**
	 * Método para cambiar el estdao de la cita.
	 */
	@Override
	@Transactional
	public Consulta actualizarEstado(Long id, String nuevoEstado, String observaciones) {
		
		return consultaRepository.findById(id)
                .map(consultaExistente -> {
                    consultaExistente.setEstado(nuevoEstado);
                    if (observaciones != null) {
                        consultaExistente.setObservaciones(observaciones);
                    }
                    return consultaRepository.save(consultaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con ID: " + id));
	}

	/**
	 * Método para eliminar consulta.
	 */
	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Consulta no encontrada con ID: " + id);
        }
        consultaRepository.deleteById(id);
     }

	

}
