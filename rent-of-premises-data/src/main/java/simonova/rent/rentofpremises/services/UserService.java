package simonova.rent.rentofpremises.services;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.User;

@Transactional
public interface UserService extends CrudService<UserDTO, Long>{

    UserDTO findByEmail(String email);

    Page<User> findPaginated(int pageNo, int pageSize);

    void deleteById(Long id);
}
