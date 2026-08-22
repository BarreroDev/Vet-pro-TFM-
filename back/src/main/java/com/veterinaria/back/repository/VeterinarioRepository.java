package com.veterinaria.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.back.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

	Optional<Veterinario> findByNumeroColegiado(String numeroColegiado);
}
