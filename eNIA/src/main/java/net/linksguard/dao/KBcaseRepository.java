package net.linksguard.dao;

 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 

import net.linksguard.entities.KBcase; 
 
 

public interface KBcaseRepository extends JpaRepository<KBcase, Long> {
	@Query(value = "SELECT * FROM kbcase",nativeQuery = true)
	public Page<KBcase> allCaseRequest(Pageable pageable);
	@Query("SELECT p FROM KBcase p WHERE "
			+ "CONCAT(p.projectLabel, p.shortLabel, p.descriptionLabel, p.causeLabel, p.shortCauseLabel )"
			+ " LIKE %?1%")
	public Page<KBcase> findByDescriptionLabelContains(String mc, Pageable pageable);
	
 
	
}
