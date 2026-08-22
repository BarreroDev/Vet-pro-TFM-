package com.veterinaria.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veterinaria.back.model.Consulta;
import com.veterinaria.back.service.ConsultaService;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {

	@Autowired
    private ConsultaService consultaService;
	
	@PostMapping
    public Consulta crearConsulta(@RequestBody Consulta consulta) {
        return consultaService.guardarConsulta(consulta);
    }
}
