package net.linksguard.ims.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.linksguard.ims.entities.ImsCategory;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<ImsCategory, Integer> {
    // get all categories of the specified category type
    List<ImsCategory> findCategoryByCategoryTypeEquals(String categoryType);

    // get all categories sorted by type then name
    List<ImsCategory> findAllByOrderByCategoryTypeAscCategoryNameAsc();

}