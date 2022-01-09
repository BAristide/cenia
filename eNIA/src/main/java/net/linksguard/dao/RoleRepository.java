package net.linksguard.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.linksguard.entities.Roles;
import net.linksguard.entities.User;
import net.linksguard.ims.entities.ImsAssignedGroup; 

public interface RoleRepository extends JpaRepository< Roles, Long>{
	
 
	
}
/*

@Query("SELECT p FROM User p WHERE "
			+ "CONCAT(p.firstName, p.lieuHabitation, p.email )"
			+ " LIKE %?1%")
*/