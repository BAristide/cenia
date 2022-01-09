package net.linksguard.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.linksguard.entities.Equipes; 

public interface EquipeRepository extends JpaRepository<Equipes, Long>{
	
	public List<Equipes> findByNom(String c);
	public Page<Equipes> findByNomContains(String mc, Pageable pageable);
}
