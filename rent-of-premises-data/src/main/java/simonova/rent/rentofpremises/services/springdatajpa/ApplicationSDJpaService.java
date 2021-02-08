package simonova.rent.rentofpremises.services.springdatajpa;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.converters.ApplicationConverter;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.model.Application;
import simonova.rent.rentofpremises.repositories.ApplicationRepository;
import simonova.rent.rentofpremises.services.ApplicationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис предоставляет взаимодействие с заявками на аренду (таблицей) клиентов
 */
@Service
public class ApplicationSDJpaService implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationSDJpaService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<ApplicationDTO> findAll() {
        ApplicationConverter applicationConverter = new ApplicationConverter(new ModelMapper());

        List<ApplicationDTO> applications = new ArrayList<>();
        applicationRepository.findAll().forEach(application -> applications.add(applicationConverter.convertToDto(application)));
        return applications;
    }

    @Override
    public ApplicationDTO findById(Long aLong) {
        ApplicationConverter applicationConverter = new ApplicationConverter(new ModelMapper());
        Application application = applicationRepository.findById(aLong).orElse(null);
        return applicationConverter.convertToDto(application);
    }

    @Override
    public ApplicationDTO save(ApplicationDTO applicationDTO) {

        ModelMapper modelMapper = new ModelMapper();
        ApplicationConverter applicationConverter = new ApplicationConverter(modelMapper);

        Application application = applicationConverter.convertToEntity(applicationDTO);

        applicationRepository.save(application);
        return applicationConverter.convertToDto(application);
    }

    @Override
    public void delete(ApplicationDTO applicationDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationConverter applicationConverter = new ApplicationConverter(modelMapper);
        applicationRepository.delete(applicationConverter.convertToEntity(applicationDTO));
    }

    @Override
    public void deleteById(Long aLong) {
        applicationRepository.deleteById(aLong);
    }

    @Override
    public List<ApplicationDTO> findByUserId(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicationConverter applicationConverter = new ApplicationConverter(modelMapper);

        List<ApplicationDTO> applications = new ArrayList<>();
        applicationRepository.findByUserId(id).forEach(application -> applications.add(applicationConverter.convertToDto(application)));

        return applications;
    }
}
