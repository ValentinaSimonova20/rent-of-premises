package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
