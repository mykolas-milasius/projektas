package pagrindinis.projektas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>
{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);
	public User getUserByEmail(@Param("email") String email);
}