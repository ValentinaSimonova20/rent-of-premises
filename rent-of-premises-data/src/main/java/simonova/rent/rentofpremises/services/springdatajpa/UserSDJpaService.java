package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.repositories.UserRepository;
import simonova.rent.rentofpremises.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для взаимодействия с таблицей клиентов бизнес-центра
 */
@Service
public class UserSDJpaService implements UserService {

    UserRepository clientRepository;

    public UserSDJpaService(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<User> findAll() {

        List<User> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public User findByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(User object) {
        return clientRepository.save(object);
    }

    @Override
    public void delete(User object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
