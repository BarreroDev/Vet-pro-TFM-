package com.veterinaria.back.controller;

import com.veterinaria.back.service.DuenoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.back.model.Dueno;

@RestController
@RequestMapping("/api/duenos")
@CrossOrigin(origins = "http://localhost:4200")

public class DuenoController {
	
	@Autowired
	private DuenoService duenoService;

    //Método que trae un nuevo dueño para que el servicio lo guarde.
	@PostMapping
	public Dueno crearDueno(@RequestBody Dueno dueno) {
		return duenoService.guardarDueno(dueno);
	}
	
	//Método para listar todos los dueños.
	@GetMapping
	public List<Dueno> obtenerDuenos(){
		return duenoService.listarDuenos();//listarDuenos viene del servicio.
	}
	
	//Método para filtrar por dni.
	@GetMapping("/{dni}")
	public ResponseEntity<Dueno> obtenerPorDni(@PathVariable String dni){
		return duenoService.listarPorDni(dni)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Método paar eliminar por dni.
	@DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminarDueno(@PathVariable String dni) {
        duenoService.eliminarDueno(dni);
        return ResponseEntity.ok().build();
    }
	

}
