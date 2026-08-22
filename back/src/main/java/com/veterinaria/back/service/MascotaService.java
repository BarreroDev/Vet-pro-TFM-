package com.veterinaria.back.service
;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.back.model.Mascota;
import com.veterinaria.back.repository.MascotaRepository;

@Service
public class MascotaService {

	@Autowired
	private MascotaRepository mascotaRepository;
	
	//Método para guardar nueva mascota.
	public Mascota guardarMascota(Mascota mascota) {
		return mascotaRepository.save(mascota);
	}
	
	//Método para listar todas las mascotas.
	public List<Mascota> obtenerMascota(){
		return mascotaRepository.findAll();
	}
	
	//Método para eliminar mascotas.
	public void eliminarMascota(Long id) {
		mascotaRepository.deleteById(id);
	}
	
	//Método para buscar una mascota en concreto.
	public Optional<Mascota> obtenerPorId(Long id) {
	    return mascotaRepository.findById(id);
	}
	
	
	//Método para filtrar por las mascotas de un dueño
	public List<Mascota> obtenerMascotasPorDueno(String dni) {
	    return mascotaRepository.findByDuenoDni(dni);
	}
	
}
