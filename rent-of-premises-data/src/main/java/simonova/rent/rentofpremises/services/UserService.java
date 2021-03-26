package simonova.rent.rentofpremises.services;

import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.dto.UserDTO;

public interface UserService extends CrudService<UserDTO, Long>{

    UserDTO findByEmail(String email);
}
