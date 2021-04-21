package simonova.rent.rentofpremises.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.model.Application;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей заявок
 */
@Transactional
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserId(Long id);
    Optional<Application> findByUserIdAndPremisesId(Long userId, Long premId);
    Page<Application> findByUserId(Long id, Pageable pageable);
}
