package net.linksguard.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import net.linksguard.entities.Fiche;
import net.linksguard.entities.StatutFiche; 

public interface FicheReporistory extends JpaRepository<Fiche, Long> {
	@Query(value =  "SELECT COUNT(*) FROM fiche p WHERE p.type_fiche = :mc",nativeQuery = true)
	public long findTypeFiche(@Param("mc") String mc);
	
	@Query(value =  "SELECT * FROM fiche p WHERE p.type_fiche = :mc",nativeQuery = true)
	public Page<Fiche> findByTypeFicheList(String mc, Pageable pageable);
	
	@Query(value =  "SELECT * FROM fiche",nativeQuery = true)
	public List<Fiche> typeFicheList1();
	public Page<Fiche> findByStatusFicheId(StatutFiche mc, Pageable pageable);
	public List<Fiche> findByStatusFicheId(StatutFiche mc);
	
	public List<Fiche> findByStatusFicheIdAndId(StatutFiche mc, long id);
	 

}
