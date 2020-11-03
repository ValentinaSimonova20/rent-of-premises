package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Contracts;

public interface ContractsRepository extends CrudRepository<Contracts, Long> {
}
