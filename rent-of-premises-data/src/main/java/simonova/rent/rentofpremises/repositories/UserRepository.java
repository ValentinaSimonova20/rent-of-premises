package simonova.rent.rentofpremises.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import simonova.rent.rentofpremises.model.User;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей пользователей
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
