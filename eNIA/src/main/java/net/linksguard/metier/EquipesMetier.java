package net.linksguard.metier;

import java.util.List;

import net.linksguard.entities.Equipes;

public interface EquipesMetier {
	public Equipes saveEquipes(Equipes equipes);
	public List<Equipes> listEquipe();
	public List<Equipes> searchEquipe(String nom);
	

}
