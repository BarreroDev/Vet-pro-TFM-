package com.veterinaria.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinaria.back.model.Consulta;
import com.veterinaria.back.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
    private ConsultaRepository consultaRepository;

    //Método agendar una nueva consulta.
    public Consulta guardarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    //Método obtener el historial de todas las consultas de la clínica.
    public List<Consulta> obtenerTodas() {
        return consultaRepository.findAll();
    }

    //Método buscar una consulta específica por su ID
    public Optional<Consulta> obtenerPorId(Long id) {
        return consultaRepository.findById(id);
    }

    //Métrodo para cancelar o eliminar una consulta.
    public void eliminarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
