package ba.unsa.etf.ppis_project.repos;

import ba.unsa.etf.ppis_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from user where username=:u", nativeQuery = true)
    void deleteByUsername(String u);

    @Query(value = "SELECT * FROM User u, Role r WHERE r.id = u.role_id AND r.name =:roleName", nativeQuery = true)
    List<User> getUsersByRole(@Param("roleName") String roleName);

    User getUserByUsername(String username);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
