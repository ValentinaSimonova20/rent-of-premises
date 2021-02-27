package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.model.FilterArea;
import simonova.rent.rentofpremises.model.Premises;

import java.util.List;

public interface PremisesService extends CrudService<PremisesDTO, Long>{

    // Поиск помещения по названию
    PremisesDTO findByName(String name);

    // поиск помещения по площади
    PremisesDTO findByArea(double area);

    List<PremisesDTO> findAllPremises(FilterArea filterArea, int floor);

    List<PremisesDTO> findAllPremises(FilterArea filterArea);

    Double getMaxArea();

    Double getMaxPrice();

    Integer getMaxWorkplaces();

    Integer getMaxFloor();

    List<Integer> getAllFloors();
}
