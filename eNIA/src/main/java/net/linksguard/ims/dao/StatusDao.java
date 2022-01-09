package net.linksguard.ims.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.linksguard.ims.entities.ImsStatus;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StatusDao extends CrudRepository<ImsStatus, Integer> {
}