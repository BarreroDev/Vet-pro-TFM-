package com.veterinaria.back.controller;

import com.veterinaria.back.model.Veterinario;
import com.veterinaria.back.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controlador REST para la gestión de Veterinarios.
 * 
 * @author Roberto Barrero de la Rosa
 * @version 1.0
 */
@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public ResponseEntity<List<Veterinario>> obtenerTodos() {
        return ResponseEntity.ok(veterinarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> obtenerPorId(@PathVariable Long id) {
        return veterinarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Método POST adaptado para recibir datos del veterinario (JSON) y una foto opcional (Multipart).
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Veterinario> guardar(
            @RequestPart("veterinario") Veterinario veterinario,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Veterinario nuevoVet = veterinarioService.guardarFoto(veterinario, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> actualizar(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        try {
            return ResponseEntity.ok(veterinarioService.actualizar(id, veterinario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            veterinarioService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}