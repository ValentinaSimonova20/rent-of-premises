package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.model.User;

import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
