package simonova.rent.rentofpremises.dataloader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.model.User;
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

    private void loadData(){
        UserDTO manager = new UserDTO();
        manager.setName("name");
        manager.setSurname("surname");
        manager.setEmail("manager@gmail.com");
        manager.setPass("$2y$12$LZsE5Ar2K4uvcqj3YBYFhulju3K.KqHTN9G61caGqASKGjEKUQQrq");
        manager.setStatus(Status.ACTIVE);
        manager.setRole(Role.MANAGER);
        manager.setPatronymic("patrf");
        userService.save(manager);
    }
}
