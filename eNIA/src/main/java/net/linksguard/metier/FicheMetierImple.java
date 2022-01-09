package net.linksguard.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.linksguard.dao.FicheReporistory;
import net.linksguard.entities.Fiche;
@Service
public class FicheMetierImple implements FicheMetier {
	@Autowired
	private FicheReporistory ficheReporistory;

	@Override
	@Transactional
	public Fiche saveFiche(Fiche fiche) {
		// TODO Auto-generated method stub
		fiche.setCreationDate(new Date());
		return ficheReporistory.save(fiche);
	}

	@Override
	public Fiche getFicheC(Long id) {
		// TODO Auto-generated method stub
		return ficheReporistory.getOne(id);
		
	}
	 

}
