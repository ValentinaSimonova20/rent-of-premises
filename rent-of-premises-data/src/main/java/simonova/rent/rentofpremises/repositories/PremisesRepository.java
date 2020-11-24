package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Premises;

import java.util.Optional;

public interface PremisesRepository extends CrudRepository<Premises, Long> {

    Optional<Premises> findByName(String name);

    Optional<Premises> findByArea(double area);

}
