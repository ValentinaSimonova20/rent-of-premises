package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationService extends CrudService<ApplicationDTO, Long>{
    List<ApplicationDTO> findByUserId(Long id);
    ApplicationDTO findByUserIdAndPremisesId(Long userId, Long premId);
}
