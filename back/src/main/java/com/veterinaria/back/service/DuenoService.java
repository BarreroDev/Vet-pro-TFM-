package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.back.model.Dueno;
import com.veterinaria.back.repository.DuenoRepository;

@Service
public class DuenoService {
	
	@Autowired
	private DuenoRepository duenoRepository;
	
	//Método para guardar usuarios.
	public Dueno guardarDueno(Dueno dueno) {
		return duenoRepository.save(dueno);
	}
	
	//Método para listar todos los dueños.
	public List<Dueno> listarDuenos(){
		return duenoRepository.findAll();
	}
	
	//Método para filtrar por el DNI.
	public Optional<Dueno> listarPorDni(String dni) {
        return duenoRepository.findById(dni);
    }
	
	//Método para eliminaar usuarios.
	public void eliminarDueno(String dni) {
        duenoRepository.deleteById(dni);
    }

}
