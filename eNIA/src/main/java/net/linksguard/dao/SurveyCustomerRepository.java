package net.linksguard.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.linksguard.entities.SurveyCustomer; 

public interface SurveyCustomerRepository extends JpaRepository<SurveyCustomer, Long> {
	
 public List<SurveyCustomer> findByIssueId(long issueId);

}
