package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Clients;

public interface ClientsRepository extends CrudRepository<Clients, Long> {
}
