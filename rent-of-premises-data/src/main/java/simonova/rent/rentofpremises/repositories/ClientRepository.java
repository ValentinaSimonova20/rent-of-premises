package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Client;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
}
