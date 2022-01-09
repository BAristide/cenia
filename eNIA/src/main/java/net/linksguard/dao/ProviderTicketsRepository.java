package net.linksguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.linksguard.entities.ProviderTickets;

public interface ProviderTicketsRepository  extends JpaRepository<ProviderTickets, Long>{

	@Query("SELECT serviceClasse FROM ServicesPlateformes WHERE name = ?1")
	public String findServiceClassByName(String name);
	
}
