package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.FilterArea;
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
    public List<Premises> findAllPremises(FilterArea filterArea, int floor) {

        return premisesRepository.findAllPremises(copyObject(filterArea), floor);
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
    public List<Premises> findAllPremises(FilterArea filterArea) {


        return premisesRepository.findAllPremises(copyObject(filterArea));
    }


    private FilterArea copyObject(FilterArea filterArea){
        FilterArea filterArea2 = new FilterArea();

        filterArea2.setAreaMin(filterArea.getAreaMin() == null ? 0.0 : filterArea.getAreaMin());

        filterArea2.setAreaMax(filterArea.getAreaMax() == null ? getMaxArea() : filterArea.getAreaMax());

        filterArea2.setPriceMin(filterArea.getPriceMin() == null ? 0.0 : filterArea.getPriceMin());

        filterArea2.setPriceMax(filterArea.getPriceMax() == null ? getMaxPrice() : filterArea.getPriceMax());

        filterArea2.setWorkplaces(filterArea.getWorkplaces() == null ? getMaxWorkplaces(): filterArea.getWorkplaces());

        filterArea2.setAreaName(filterArea.getAreaName());
        filterArea2.setFloor(filterArea2.getFloor());

        return filterArea2;
    }
}
