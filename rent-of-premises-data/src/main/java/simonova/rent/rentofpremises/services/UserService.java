package simonova.rent.rentofpremises.services;

import simonova.rent.rentofpremises.model.User;

public interface UserService extends CrudService<User, Long>{

    User findByEmail(String email);
}
