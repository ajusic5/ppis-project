package ba.unsa.etf.ppis_project.repos;

import ba.unsa.etf.ppis_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
