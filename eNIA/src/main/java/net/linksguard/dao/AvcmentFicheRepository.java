package net.linksguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.linksguard.entities.EtaAvcMent;

public interface AvcmentFicheRepository extends JpaRepository<EtaAvcMent, Long> {
	

}
