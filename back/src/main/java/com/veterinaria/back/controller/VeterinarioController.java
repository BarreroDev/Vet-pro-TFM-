package com.veterinaria.back.controller;

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

import com.veterinaria.back.model.Veterinario;
import com.veterinaria.back.service.VeterinarioService;

@RestController
@RequestMapping("/api/veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

	@Autowired
    private VeterinarioService veterinarioService;
	
	//Método para registrar veterinario desde Post.
	@PostMapping
    public Veterinario crearVeterinario(@RequestBody Veterinario veterinario) {
        return veterinarioService.nuevoVeterinario(veterinario);
    }
	//Método listar veterinario desde el GET.
	@GetMapping
    public List<Veterinario> obtenerTodos() {
        return veterinarioService.obtenerTodos();
    }
	
	//Método para listar por numero de colgiado desde GET.
	@GetMapping("/colegiado/{numeroColegiado}")
    public ResponseEntity<Veterinario> obtenerPorNumeroColegiado(@PathVariable String numeroColegiado) {
        return veterinarioService.obtenerPorNumeroColegiado(numeroColegiado)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
	}
	
	
	//Método para eliminar veterinario.
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVeterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinario(id);
        return ResponseEntity.ok().build();
    }
	
	
}
