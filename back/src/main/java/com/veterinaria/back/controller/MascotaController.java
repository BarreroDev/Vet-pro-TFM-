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

import com.veterinaria.back.model.Mascota;
import com.veterinaria.back.service.MascotaService;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {
	
	@Autowired MascotaService mascotaService;
	
	
	//Método para registrar mascota desde Post.
	@PostMapping
	public Mascota crearMascota(@RequestBody Mascota mascota) {
		return mascotaService.guardarMascota(mascota);
	}
	
	//Método listar mascotas desde el GET.
	@GetMapping
    public List<Mascota> obtenerTodas() {
        return mascotaService.obtenerMascota();
    }
	
	//Método para listar por id mascota desde GET.
	@GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable Long id) {
        return mascotaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	
	//Método para obtener mascota atraves del dni de un dueño.
	@GetMapping("/dueno/{dni}")
    public List<Mascota> obtenerMascotasPorDueno(@PathVariable String dni) {
        return mascotaService.obtenerMascotasPorDueno(dni);
    }
	
	
	//Método para eliminar mascota.
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.ok().build();
    }

}
