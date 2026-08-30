package com.veterinaria.back.service;

import java.util.List;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.veterinaria.back.model.Dueno;
import com.veterinaria.back.model.Mascota;
import com.veterinaria.back.repository.DuenoRepository;
import com.veterinaria.back.repository.MascotaRepository;

/**
 * Implementación de la lógica de negocio para las mascotas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */

@Service
public class MascotasServiceImpl implements MascotasService {

	private final MascotaRepository mascotaRepository;
	private final DuenoRepository duenoRepository;
	
	public MascotasServiceImpl(MascotaRepository mascotaRepository, DuenoRepository duenoRepository) {
		this.mascotaRepository = mascotaRepository;
		this.duenoRepository = duenoRepository;
	}
	
	
    /**
     * Método para listar todas las mascotas.
     */
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> obtenerTodas() {
		// TODO Auto-generated method stub
		return mascotaRepository.findAll();
	}

	/**
	 * Método para filtrar por Id.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Mascota> obtenerPorId(Long id) {
	    return mascotaRepository.findById(id);
	}
	
	/**
	 * Método para buscar las mascotas con el dni del dueño.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> obtenerPorDniDueno(String dni) {
		return mascotaRepository.findByDuenoDni(dni);
	}

	/**
	 * Método para guardar unanueva mascota pero siendo asociada a un dueño.
	 */
	@Override
	@Transactional
	public Mascota guardar(Mascota mascota, String dniDueno) {
		Dueno dueno = duenoRepository.findByDni(dniDueno)
				.orElseThrow(() -> new RuntimeException("No existe un dueño registrado con el DNI: " + dniDueno));
		mascota.setDueno(dueno);
		return mascotaRepository.save(mascota);
	}

	
	/**
	 * Método para actualiazar los datos de una mascota.
	 */
	@Override
	@Transactional
	public Mascota actualizar(Long id, Mascota mascotaActualizada) {
	    return mascotaRepository.findById(id)
	            .map(mascotaExistente -> {
	                mascotaExistente.setNombre(mascotaActualizada.getNombre());
	                mascotaExistente.setEspecie(mascotaActualizada.getEspecie());
	                mascotaExistente.setRaza(mascotaActualizada.getRaza());
	                mascotaExistente.setEdad(mascotaActualizada.getEdad());
	                mascotaExistente.setPeso(mascotaActualizada.getPeso());
	                mascotaExistente.setFotoUrl(mascotaActualizada.getFotoUrl());
	                return mascotaRepository.save(mascotaExistente);
	            })
	            .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
	}

	/**
	 * Método para eliminar una mascota.
	 */
	@Override
	public void eliminarPorId(Long id) {
		if(!mascotaRepository.existsById(id)) {
			throw new RuntimeException("No se puede eliminar. Mascota no encontrada con ID: " + id);
		}
		mascotaRepository.deleteById(id);

	}

}
