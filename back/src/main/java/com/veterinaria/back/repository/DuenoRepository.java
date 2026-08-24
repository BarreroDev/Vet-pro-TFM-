package com.veterinaria.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.back.model.Dueno;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Long> {

}
