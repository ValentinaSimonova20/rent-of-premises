package simonova.rent.rentofpremises.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Premises;

import java.util.List;
import java.util.Optional;

public interface PremisesRepository extends CrudRepository<Premises, Long> {

    Optional<Premises> findByName(String name);

    Optional<Premises> findByArea(double area);


    @Query(value = "SELECT * FROM premises WHERE name LIKE %?1% " +
            "AND area BETWEEN ?3 AND ?2 AND floor= ?4 AND workplaces<= ?5 AND price BETWEEN ?6 AND ?7", nativeQuery = true)
    List<Premises> findAllPremises(String name, double areaMax, double areaMin,
                                   int floor, int workplaces,
                                   double minPrice, double maxPrice);

    @Query(value = "SELECT * FROM premises WHERE name LIKE %?1% " +
            "AND area BETWEEN ?3 AND ?2  AND workplaces<= ?4 AND price BETWEEN ?5 AND ?6", nativeQuery = true)
    List<Premises> findAllPremises(String name, double areaMax, double areaMin,
                                   int workplaces,
                                   double minPrice, double maxPrice);


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
