package net.linksguard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.linksguard.entities.Equipes;
import net.linksguard.metier.EquipesMetier;

@RestController
public class EquipeRestService {
	@Autowired
	private EquipesMetier equipesMetier;

	@RequestMapping(value = "/equipe",method = RequestMethod.POST)
	public boolean saveEquipes(@RequestBody Equipes equipes) {
		if(equipesMetier.searchEquipe(equipes.getNom()).size() > 0) {
			return false;
		}else {
			equipesMetier.saveEquipes(equipes);
		}
		return true;
	}
	@RequestMapping(value = "/equipe",method = RequestMethod.GET)
	public List<Equipes> listEquipe() {
		
		return equipesMetier.listEquipe();
	}

}
