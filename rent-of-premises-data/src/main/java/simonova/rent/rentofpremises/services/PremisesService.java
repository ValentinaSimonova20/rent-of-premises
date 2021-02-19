package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.model.Premises;

import java.util.List;

public interface PremisesService extends CrudService<Premises, Long>{

    // Поиск помещения по названию
    Premises findByName(String name);

    // поиск помещения по площади
    Premises findByArea(double area);

    List<Premises> findAllPremises(String name, double areaMax, double areMin,
                                   int floor, int workplaces,
                                   double minPrice, double maxPrice);

    List<Premises> findAllPremises(String name, double areaMax, double areMin,int workplaces,
                                   double minPrice, double maxPrice);

    Double getMaxArea();

    Double getMaxPrice();

    Integer getMaxWorkplaces();

    Integer getMaxFloor();

    List<Integer> getAllFloors();
}
