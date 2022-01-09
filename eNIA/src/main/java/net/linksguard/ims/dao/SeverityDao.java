package net.linksguard.ims.dao;

 
import net.linksguard.ims.entities.ImsSeverity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SeverityDao extends CrudRepository<ImsSeverity, Integer> {

}