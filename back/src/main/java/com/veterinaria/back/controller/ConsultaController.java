package com.veterinaria.back.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.back.model.Consulta;
import com.veterinaria.back.service.ConsultaService;

/**
 * Controlador REST para la gestión de Consultas y Citas Médicas.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

	private final ConsultaService consultaService;
	
	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@GetMapping
	public ResponseEntity<List<Consulta>> obtenerTodas(){
		return ResponseEntity.ok(consultaService.obtenerTodas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> obtenerPorId(@PathVariable Long id){
		return consultaService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/mascota/{mascotaId}")
	public ResponseEntity<List<Consulta>> obtenerPorMascota(@PathVariable Long mascotaId){
		return ResponseEntity.ok(consultaService.obtenerPorMascota(mascotaId));
	}
	
	@GetMapping("/rango")
	public ResponseEntity<List<Consulta>> obtenerPorRangoFecha(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin){
		return ResponseEntity.ok(consultaService.obtenerPorRangoFechas(inicio, fin));
	}
	
	@PostMapping("/mascota/{mascotaId}/veterinario/{veterinarioId}")
	public ResponseEntity<Consulta> guardar(
			@RequestBody Consulta consulta,
			@PathVariable Long mascotaId,
			@PathVariable Long veterinarioId){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(consultaService.guardar(consulta, mascotaId, veterinarioId));
	}
	
	
	
	
}
