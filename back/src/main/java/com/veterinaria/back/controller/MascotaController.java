package com.veterinaria.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.back.model.Mascota;
import com.veterinaria.back.service.MascotasService;

/**
 * Controlador REST para la gestión de Mascotas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

	private final MascotasService mascotasService;
	
	public MascotaController(MascotasService mascotasService){
		this.mascotasService = mascotasService;
	}
	
	@GetMapping
	public ResponseEntity<List<Mascota>> obtenerTodas(){
		return ResponseEntity.ok(mascotasService.obtenerTodas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mascota> obtenerPorId(@PathVariable Long id){
		return mascotasService.obtenerPorId(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/dueno/dni/{dni}")
	public ResponseEntity<List<Mascota>> obtenerPorDniDueno(@PathVariable String dni){
		return ResponseEntity.ok(mascotasService.obtenerPorDniDueno(dni));
	}
	
	@PostMapping("/dueno/{dniDueno}")
	public ResponseEntity<Mascota> guardar(@RequestBody Mascota mascota, @PathVariable String dniDueno){
		return ResponseEntity.status(HttpStatus.CREATED).body(mascotasService.guardar(mascota, dniDueno));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota mascota){
		try {
			return ResponseEntity.ok(mascotasService.actualizar(id, mascota));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id){
		try {
			mascotasService.eliminarPorId(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
