package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.model.Application;

import java.util.List;

public interface ApplicationService extends CrudService<Application, Long>{
    List<Application> findByClientId(Long id);
}
