package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Client;
import simonova.rent.rentofpremises.repositories.ClientRepository;
import simonova.rent.rentofpremises.services.ClientService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Сервис для взаимодействия с таблицей клиентов бизнес-центра
 */
@Service
public class ClientSDJpaService implements ClientService {

    ClientRepository clientRepository;

    public ClientSDJpaService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> findAll() {

        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Client findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
