package com.veterinaria.back.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.back.model.Dueno;
import com.veterinaria.back.service.DuenosService;

/**
 * Controlador REST para la gestión de Dueños de mascotas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@RestController
@RequestMapping("/api/duenos")
@CrossOrigin(origins = "http://localhost:4200")
public class DuenoController {
	
	private final DuenosService duenosService;
	
	public DuenoController(DuenosService duenosService) {
		this.duenosService = duenosService;
	}
	
	@GetMapping
	public ResponseEntity<List<Dueno>> obtenerTodos() {
		return ResponseEntity.ok(duenosService.obtenerTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Dueno> obternerPorId(@PathVariable Long id){
		return duenosService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/buscar/dni/{dni}")
	public ResponseEntity<Dueno> obternerPorDni(@PathVariable String dni){
		return duenosService.obtenerPorDni(dni)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	

}
