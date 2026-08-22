package com.veterinaria.back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.veterinaria.back.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
	
	List<Mascota> findByDuenoDni(String dni);

}
