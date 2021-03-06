package simonova.rent.rentofpremises.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.model.FilterArea;
import simonova.rent.rentofpremises.model.Premises;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей площадей
 */
@Transactional
public interface PremisesRepository extends JpaRepository<Premises, Long> {

    Optional<Premises> findByName(String name);

    Optional<Premises> findByArea(double area);

    Optional<Premises> findById(Long id);

    Page<Premises> findByIsRented(boolean isRented, Pageable pageable);

    @Query(value = "SELECT * FROM premises WHERE price BETWEEN :#{#filter.priceMin} AND :#{#filter.priceMax}" +
            " AND area BETWEEN :#{#filter.areaMin} AND :#{#filter.areaMax} AND workplaces <= :#{#filter.workplaces} AND floor IN(:floors) AND is_rented IN (:rents)", nativeQuery = true)
    Page<Premises> findAllPremises(@Param("filter") FilterArea filterArea,@Param("rents") List<Boolean> rents,@Param("floors") List<Integer> floors, Pageable pageable);

    @Query(value = "SELECT max(area) FROM premises ", nativeQuery = true)
    Double getMaxArea();

    @Query(value = "SELECT max(price) FROM premises ", nativeQuery = true)
    Double getMaxPrice();

    @Query(value = "SELECT max(workplaces) FROM premises ", nativeQuery = true)
    Integer getMaxWorkplaces();

    @Query(value = "SELECT max(floor) FROM premises ", nativeQuery = true)
    Integer getMaxFloor();

    @Query(value = "SELECT DISTINCT floor FROM premises ", nativeQuery = true)
    List<Integer> getAllFloors();

}
