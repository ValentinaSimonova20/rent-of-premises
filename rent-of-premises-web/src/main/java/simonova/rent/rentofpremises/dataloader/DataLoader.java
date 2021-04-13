package simonova.rent.rentofpremises.dataloader;

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

    private void loadData(){
        UserDTO admin = new UserDTO();
        admin.setName("name");
        admin.setSurname("surname");
        admin.setEmail("manager@gmail.com");
        admin.setPass("$2y$12$LZsE5Ar2K4uvcqj3YBYFhulju3K.KqHTN9G61caGqASKGjEKUQQrq");
        admin.setStatus(Status.ACTIVE);
        admin.setRole(Role.ADMIN);
        admin.setPatronymic("patrf");
        userService.save(admin);
    }
}
