package net.linksguard.ims.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.linksguard.ims.entities.ImsAssignedGroup;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GroupDao extends CrudRepository<ImsAssignedGroup, Integer> {

    // get all groups sorted by name
    List<ImsAssignedGroup> findAllByOrderByGroupName();
}
