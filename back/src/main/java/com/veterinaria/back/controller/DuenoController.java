package com.veterinaria.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

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
	
	/**
	 * Método que trae todos los dueños.
	 * @return la liitar con todos los dueños.
	 */
	@GetMapping
	public ResponseEntity<List<Dueno>> obtenerTodos() {
		return ResponseEntity.ok(duenosService.obtenerTodos());
	}
	
	/**
	 * Método para filtrar.
	 * @param id
	 * @return un dueño por su Id.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Dueno> obternerPorId(@PathVariable Long id){
		return duenosService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Método para filtrar.
	 * @param dni
	 * @return un dueño por su dni.
	 */
	@GetMapping("/buscar/dni/{dni}")
	public ResponseEntity<Dueno> obternerPorDni(@PathVariable String dni){
		return duenosService.obtenerPorDni(dni)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Método para crear un dueño.
	 * @param dueno
	 * @return un nuevo dueño
	 */
	@PostMapping
	public ResponseEntity<Dueno> guardar(@RequestBody Dueno dueno){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(duenosService.guardar(dueno));	
	}
	
	/**
	 * Método para modificar.
	 * @param id
	 * @param dueno
	 * @return datos modificados de un dueño.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Dueno> actualizar(@PathVariable Long id, @RequestBody Dueno dueno){
		try {
			return ResponseEntity.ok(duenosService.actualizar(id, dueno));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		try {
			duenosService.eliminiarporId(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	

}
