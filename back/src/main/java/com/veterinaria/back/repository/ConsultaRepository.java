package com.veterinaria.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.back.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
