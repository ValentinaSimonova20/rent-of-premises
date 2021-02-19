package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Premises;
import simonova.rent.rentofpremises.repositories.PremisesRepository;
import simonova.rent.rentofpremises.services.PremisesService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для взаимодействия с таблицей, хранящей информацию о площадях бизнес-центра
 */
@Service
public class PremisesSDJpaService implements PremisesService {

    PremisesRepository premisesRepository;

    public PremisesSDJpaService(PremisesRepository premisesRepository) {
        this.premisesRepository = premisesRepository;
    }

    @Override
    public Premises findByName(String name) {
        return premisesRepository.findByName(name).orElse(null);
    }

    @Override
    public Premises findByArea(double area) {
        return premisesRepository.findByArea(area).orElse(null);
    }

    @Override
    public List<Premises> findAllPremises(String name, double areaMax,double areaMin, int floor, int workplaces, double minPrice, double maxPrice) {
        return premisesRepository.findAllPremises(name, areaMax, areaMin, floor, workplaces, minPrice, maxPrice);
    }

    @Override
    public List<Premises> findAll() {
        List<Premises> premises = new ArrayList<>();
        premisesRepository.findAll().forEach(premises::add);
        return premises;
    }

    @Override
    public Premises findById(Long aLong) {
        return premisesRepository.findById(aLong).orElse(null);
    }

    @Override
    public Premises save(Premises premises) {
        return premisesRepository.save(premises);
    }

    @Override
    public void delete(Premises premises) {
        premisesRepository.delete(premises);
    }

    @Override
    public void deleteById(Long aLong) {
        premisesRepository.deleteById(aLong);
    }

    @Override
    public Double getMaxArea() {
        return premisesRepository.getMaxArea();
    }

    @Override
    public Double getMaxPrice() {
        return premisesRepository.getMaxPrice();
    }

    @Override
    public Integer getMaxWorkplaces() {
        return premisesRepository.getMaxWorkplaces();
    }

    @Override
    public Integer getMaxFloor() {
        return premisesRepository.getMaxFloor();
    }

    @Override
    public List<Integer> getAllFloors() {
        return premisesRepository.getAllFloors();
    }


    @Override
    public List<Premises> findAllPremises(String name, double areaMax, double areMin, int workplaces, double minPrice, double maxPrice) {
        return premisesRepository.findAllPremises(name, areaMax, areMin, workplaces, minPrice, maxPrice);
    }
}
