package com.veterinaria.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
