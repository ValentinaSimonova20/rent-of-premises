package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findByUserId(Long id);
    Optional<Application> findByUserIdAndPremisesId(Long userId, Long premId);
}
