package net.linksguard.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

 
import net.linksguard.entities.ServicesPlateformes;

public interface ServicePlateformeRepository extends JpaRepository<ServicesPlateformes, Long> {
	
	public List<ServicesPlateformes> findByName(String nom);
	public Page<ServicesPlateformes> findByNameContains(String mc, Pageable pageable);
	
	public List<ServicesPlateformes> findByNameContains(String mc);
}
