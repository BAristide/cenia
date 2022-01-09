package net.linksguard.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.linksguard.dao.EquipeRepository;
import net.linksguard.entities.Equipes;

@Service
public class EquipesImpl implements EquipesMetier {
	@Autowired
	private EquipeRepository equipeRepository;

	@Override
	public Equipes saveEquipes(Equipes equipes) {
		// TODO Auto-generated method stub
		return equipeRepository.save(equipes);
	}

	@Override
	public List<Equipes> listEquipe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipes> searchEquipe(String nom) {
		equipeRepository.findByNom(nom);
		return equipeRepository.findByNom(nom);
	}
 

}
