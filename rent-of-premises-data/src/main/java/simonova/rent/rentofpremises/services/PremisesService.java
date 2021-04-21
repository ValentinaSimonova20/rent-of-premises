package simonova.rent.rentofpremises.services;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.model.FilterArea;
import simonova.rent.rentofpremises.model.Premises;

import java.util.List;

@Transactional
public interface PremisesService extends CrudService<PremisesDTO, Long>{

    // Поиск помещения по названию
    PremisesDTO findByName(String name);

    // поиск помещения по площади
    PremisesDTO findByArea(double area);

    Page<Premises> findAllPremisesPaginated(FilterArea filterArea,List<Boolean> rents, List<Integer> floors, int pageNo, int pageSize);

    Double getMaxArea();

    Double getMaxPrice();

    Integer getMaxWorkplaces();

    Integer getMaxFloor();

    List<Integer> getAllFloors();

    Page<Premises> findByIsRented(boolean isRented,int pageNo, int pageSize);
}
