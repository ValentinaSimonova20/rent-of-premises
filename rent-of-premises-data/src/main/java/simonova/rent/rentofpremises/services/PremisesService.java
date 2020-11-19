package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.model.Premises;

public interface PremisesService extends CrudService<Premises, Long>{

    // Поиск помещения по названию
    Premises findByName(String name);

    // поиск помещения по площади
    Premises findByArea(double area);
}
