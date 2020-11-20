package simonova.rent.rentofpremises.controllers;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.model.Client;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.repositories.ClientRepository;


import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegisterController {
    ClientRepository clientRepository;

    public RegisterController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Отображение страницы регистрации
     * @return html страница регистрации
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        Person person = new Person();
        model.addAttribute("client",person);
        return "register/index";
    }

    /**
     * Добавить нового клиента в бд
     * @param client - объект нового клиента
     * @param model
     * @return страницу регистрации или страницу логирования
     */
    @PostMapping("/register")
    public String addClient(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model){


        if (result.hasErrors()) {
            return "register/index";
        }

        Optional<Client> client1 = clientRepository.findByEmail(client.getEmail());
        if(client1.isPresent()){
            System.out.println("hi");
            model.addAttribute("message","Пользователь с таким email уже зарегистрирован");
            return  "register/index";
        }

        System.out.println("все супер");

        client.setStatus(Status.ACTIVE);
        client.setRole(Role.USER);
        client.setPass(BCrypt.hashpw(client.getPass(), BCrypt.gensalt(12)));
        clientRepository.save(client);
        return "redirect:/login";
    }

}
