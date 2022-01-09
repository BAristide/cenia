package net.linksguard.metier;

import java.util.List;

import net.linksguard.entities.Fiche;

public interface FicheMetier {
	public Fiche saveFiche(Fiche fiche);
	public Fiche getFicheC(Long id);
	

}
