package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Application;
import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findByUserId(Long id);
}
