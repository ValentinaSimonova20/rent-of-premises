package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.model.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService extends CrudService<ApplicationDTO, Long>{
    List<ApplicationDTO> findByUserId(Long id);
    ApplicationDTO findByUserIdAndPremisesId(Long userId, Long premId);
}
