package simonova.rent.rentofpremises.services.springdatajpa;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.converters.PremisesConverter;
import simonova.rent.rentofpremises.dto.PremisesDTO;
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
@Transactional
public class PremisesSDJpaService implements PremisesService {

    PremisesRepository premisesRepository;

    public PremisesSDJpaService(PremisesRepository premisesRepository) {
        this.premisesRepository = premisesRepository;
    }

    @Override
    public PremisesDTO findByName(String name) {
        PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
        Premises premises = premisesRepository.findByName(name).orElse(null);

        return premisesConverter.convertToDTO(premises);
    }

    @Override
    public PremisesDTO findByArea(double area) {
        PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
        Premises premises =premisesRepository.findByArea(area).orElse(null);
        return premisesConverter.convertToDTO(premises);
    }



    @Override
    public List<PremisesDTO> findAll() {
        PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
        List<PremisesDTO> premises = new ArrayList<>();
        premisesRepository.findAll().forEach(prem -> premises.add(premisesConverter.convertToDTO(prem)));
        return premises;
    }

    /**
     * Получить все площадь по признаку сдана площадь или нет
     * @param isRented отражает сдан офис или нет
     * @return список сданных или не сданных площадей
     */
    @Override
    public Page<Premises> findByIsRented(boolean isRented, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return premisesRepository.findByIsRented(isRented,pageable);
    }

    @Override
    public PremisesDTO findById(Long aLong) {
        PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
        Premises premises = premisesRepository.findById(aLong).orElse(null);
        if(premises == null) return null;
        return premisesConverter.convertToDTO(premises);
    }

    @Override
    public PremisesDTO save(PremisesDTO premisesDTO) {
        PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
        Premises premises = premisesConverter.convertToEntity(premisesDTO);
        premisesRepository.save(premises);

        return premisesConverter.convertToDTO(premises);
    }

    @Override
    public void delete(PremisesDTO premisesDTO) {
        PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
        premisesRepository.delete(premisesConverter.convertToEntity(premisesDTO));
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



    private FilterArea copyObject(FilterArea filterArea){
        FilterArea filterArea2 = new FilterArea();

        filterArea2.setAreaMin(filterArea.getAreaMin() == null ? 0.0 : filterArea.getAreaMin());

        filterArea2.setAreaMax(filterArea.getAreaMax() == null ? getMaxArea() : filterArea.getAreaMax());

        filterArea2.setPriceMin(filterArea.getPriceMin() == null ? 0.0 : filterArea.getPriceMin());

        filterArea2.setPriceMax(filterArea.getPriceMax() == null ? getMaxPrice() : filterArea.getPriceMax());

        filterArea2.setWorkplaces(filterArea.getWorkplaces() == null ? getMaxWorkplaces(): filterArea.getWorkplaces());

        filterArea2.setFloor(filterArea2.getFloor());

        filterArea2.setRented(filterArea.getRented());
        filterArea2.setPriceSort(filterArea.getPriceSort());

        return filterArea2;
    }



    @Override
    public Page<Premises> findAllPremisesPaginated(FilterArea filterArea,List<Boolean> rent,List<Integer> floors, int pageNo, int pageSize) {
        Pageable pageable;
        switch (filterArea.getPriceSort()){
            case "asc":
                pageable = PageRequest.of(pageNo-1, pageSize, Sort.by("price"));
                break;
            case "desc":
                pageable = PageRequest.of(pageNo-1, pageSize, Sort.by("price").descending());
                break;
            default:
                pageable = PageRequest.of(pageNo-1, pageSize);
        }
        return premisesRepository.findAllPremises(copyObject(filterArea),rent, floors, pageable);
    }




}
