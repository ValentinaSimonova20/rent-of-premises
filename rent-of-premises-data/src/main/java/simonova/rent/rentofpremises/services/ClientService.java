package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.model.Client;

import java.util.Optional;

public interface ClientService extends CrudService<Client, Long>{

    Client findByEmail(String email);
}
