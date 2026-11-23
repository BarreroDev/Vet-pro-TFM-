package com.veterinaria.back.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.veterinaria.back.model.Veterinario;
import com.veterinaria.back.repository.VeterinarioRepository;

/**
 * Implementación de la lógica de negocio para la gestión de veterinarios.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@Service
public class VeterinarioServiceImpl implements VeterinarioService {

	private final VeterinarioRepository veterinarioRepository;
	
	public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
		this.veterinarioRepository = veterinarioRepository;
	}
	
	/**
	 * Método paara listar todos los veterinarios.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Veterinario> obtenerTodos() {
		
		return veterinarioRepository.findAll();
	}

	/**
	 * Métetodo para filtrar por Id si fuera necesiario.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Veterinario> obtenerPorId(Long id) {
		
		return veterinarioRepository.findById(id);
	}

	/*
	 * Método paara filtrar por numero de colegiado.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Veterinario> obtenerPorNumeroColegiado(String numeroColegiado) {
		// TODO Auto-generated method stub
		return veterinarioRepository.findByNumeroColegiado(numeroColegiado);
	}

	/**
	 * Método para gguardar un veterianario.
	 * En caso de existir no lo guarda.
	 */
	@Override
	@Transactional
	public Veterinario guardar(Veterinario veterinario) {
		if(veterinarioRepository.existsByNumeroColegiado(veterinario.getNumeroColegiado()))
			throw new IllegalArgumentException("Ya existe un veterinario registrado con el número de colegiado: " + veterinario.getNumeroColegiado());
		return veterinarioRepository.save(veterinario);
	}

	/*
	 * Método para actualizar los datos de un veterinario.
	 */
	@Override
	@Transactional
	public Veterinario actualizar(Long id, Veterinario veterinarioActializado) {
		return veterinarioRepository.findById(id)
				.map(vetExistente -> {
					vetExistente.setNombre(veterinarioActializado.getNombre());
					vetExistente.setApellidos(veterinarioActializado.getApellidos());
					vetExistente.setTelefono(veterinarioActializado.getTelefono());
					vetExistente.setEmail(veterinarioActializado.getEmail());
					vetExistente.setRol(veterinarioActializado.getRol());
					vetExistente.setFotoUrl(veterinarioActializado.getFotoUrl());
					return veterinarioRepository.save(vetExistente);
				})
				.orElseThrow(() -> new RuntimeException("Vetererinario no encontrado con ID: " + id));
	}

	/**
	 * Método para borrar un veterinario.
	 */
	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		if(!veterinarioRepository.existsById(id)) {
			throw new RuntimeException("No se puede eliminar. Veterinario no encontrado.");
		}
		veterinarioRepository.deleteById(id);

	}
	
	@Value("${spring.file.img:img/}")
	private String img;

	public Veterinario guardarFoto(Veterinario vet, MultipartFile file) throws IOException {
	    if (file != null && !file.isEmpty()) {
	        Path uploadDir = Paths.get(img.trim()).toAbsolutePath().normalize();
	        Files.createDirectories(uploadDir);

	        String originalName = file.getOriginalFilename() != null
	                ? Path.of(file.getOriginalFilename()).getFileName().toString()
	                : "imagen.jpg";
	        String fileName = System.currentTimeMillis() + "_" + originalName;
	        Path filePath = uploadDir.resolve(fileName);

	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        vet.setFotoUrl("http://localhost:8080/img/" + fileName);
	    }

	    return veterinarioRepository.save(vet);
	}
	

}
