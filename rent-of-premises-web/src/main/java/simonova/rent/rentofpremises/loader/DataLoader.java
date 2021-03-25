package simonova.rent.rentofpremises.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.services.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;


    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = userService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    private void loadData() {

        UserDTO manager = new UserDTO();
        manager.setEmail("manager@gmail.com");
        manager.setName("Иван");
        manager.setSurname("Иванов");
        manager.setStatus(Status.ACTIVE);
        manager.setRole(Role.MANAGER);
        manager.setPass("$2y$12$OSpHhGN/fIA1J0PmZ01yAe2wJBCduvddjxUUYNNgxqRius8E4VInK");
        userService.save(manager);

        UserDTO user = new UserDTO();
        user.setEmail("user@gmail.com");
        user.setName("Иван");
        user.setSurname("Иванов");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setPass("$2y$12$OSpHhGN/fIA1J0PmZ01yAe2wJBCduvddjxUUYNNgxqRius8E4VInK");
        userService.save(user);


    }


}
