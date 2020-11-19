package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Application;
import simonova.rent.rentofpremises.repositories.ApplicationRepository;
import simonova.rent.rentofpremises.services.ApplicationService;

import java.util.HashSet;
import java.util.Set;

/**
 * Сервис предоставляет взаимодействие с заявками на аренду клиентов
 */
@Service
public class ApplicationSDJpaService implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationSDJpaService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Set<Application> findAll() {

        Set<Application> applications = new HashSet<>();
        applicationRepository.findAll().forEach(applications::add);
        return applications;
    }

    @Override
    public Application findById(Long aLong) {
        return applicationRepository.findById(aLong).orElse(null);
    }

    @Override
    public Application save(Application object) {
        return applicationRepository.save(object);
    }

    @Override
    public void delete(Application object) {
        applicationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        applicationRepository.deleteById(aLong);
    }
}
