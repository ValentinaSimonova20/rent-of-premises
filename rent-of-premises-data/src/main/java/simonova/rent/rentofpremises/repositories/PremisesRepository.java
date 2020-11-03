package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Premises;

public interface PremisesRepository extends CrudRepository<Premises, Long> {
}
