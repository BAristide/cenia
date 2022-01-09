package net.linksguard.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

 
import net.linksguard.entities.User;
import net.linksguard.ims.entities.ImsAssignedGroup; 

public interface UserRepository extends JpaRepository< User, Long>{
	
	public  List<User> findByEmail(String mc);
	@Query(value = "SELECT * FROM user  WHERE CONCAT(email,first_name, last_name) LIKE %:mc%", nativeQuery = true)
	public Page<User> findByEmailContains(@Param("mc")String mc, Pageable pageable);
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getPersonneByUserEmail (@Param("email") String email);
	
	
	public User findById(long id);
	public User deleteById(long id);
	
   
	   // get the user that matches the given username
    Optional<User> findByUsername(String username);

    // get the user that matches the given user id
    User findUserById(int id);

    // custom query to use when the user wants to change their password
    @Modifying
    @Query(value = "update user set password = ?1 where id = ?2", nativeQuery = true)
    public int updatePasswordById(String password, long id);

    // custom query to use to update the mobile phone number and carrier
    @Modifying
    @Query(value = "update user set phone = ?1  where id = ?2", nativeQuery = true)
    int updatePhoneById(String phone,   long id);

    // get a count of all users that match the giver carrier
   // int countUserByCarrierId(Carrier carrier);

    // used to perform check before deleting a group in admin controller
    int countUsersByGroupIdEquals(ImsAssignedGroup groupId);

    // check if there are any existing users with the given username or email
    int countUsersByUsernameOrEmail(String username, String email);

    // get all users sorted by last name
    List<User> findAllByOrderByLastName();

    // get all users by group
    List<User> findAllByGroupId(ImsAssignedGroup groupId);

    // get user by token
    Optional<User> findUserByToken(String token);
	
}
/*

@Query("SELECT p FROM User p WHERE "
			+ "CONCAT(p.firstName, p.lieuHabitation, p.email )"
			+ " LIKE %?1%")
*/