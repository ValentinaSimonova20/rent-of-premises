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
}
