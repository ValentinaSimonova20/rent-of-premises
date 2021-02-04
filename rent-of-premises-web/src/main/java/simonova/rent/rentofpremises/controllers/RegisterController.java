package simonova.rent.rentofpremises.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.services.UserService;


import javax.validation.Valid;


@Controller
public class RegisterController {

    UserService clientService;

    @Autowired
    public RegisterController(UserService clientService) {
        this.clientService = clientService;
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
    public String addClient(@Valid @ModelAttribute("client") User client, BindingResult result, Model model){


        if (result.hasErrors()) {
            return "register/index";
        }

        // Проверка на то, нет ли в базе данных уже пользователя с таким email
        User newClient = clientService.findByEmail(client.getEmail());
        if(newClient != null){
            System.out.println("hi");
            model.addAttribute("message","Пользователь с таким email уже зарегистрирован");
            return  "register/index";
        }


        client.setStatus(Status.ACTIVE);
        client.setRole(Role.USER);
        client.setPass(BCrypt.hashpw(client.getPass(), BCrypt.gensalt(12)));
        clientService.save(client);
        return "redirect:/login";
    }

}
