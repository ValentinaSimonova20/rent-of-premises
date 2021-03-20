package simonova.rent.rentofpremises.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import simonova.rent.rentofpremises.model.FilterArea;
import simonova.rent.rentofpremises.model.Premises;

import java.util.List;
import java.util.Optional;

public interface PremisesRepository extends CrudRepository<Premises, Long> {

    Optional<Premises> findByName(String name);

    Optional<Premises> findByArea(double area);


    @Query(value = "SELECT * FROM premises WHERE floor= :filteredFloor AND price BETWEEN :#{#filter.priceMin} AND :#{#filter.priceMax}", nativeQuery = true)
    List<Premises> findAllPremises(@Param("filter") FilterArea filterArea,@Param("filteredFloor") int filteredFloor);

    @Query(value = "SELECT * FROM premises WHERE price BETWEEN :#{#filter.priceMin} AND :#{#filter.priceMax}" +
            " AND area BETWEEN :#{#filter.areaMin} AND :#{#filter.areaMax} AND workplaces <= :#{#filter.workplaces}", nativeQuery = true)
    List<Premises> findAllPremises(@Param("filter") FilterArea filterArea);


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
