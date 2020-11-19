package simonova.rent.rentofpremises.controllers;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import simonova.rent.rentofpremises.model.Client;
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
    public String getRegisterPage(){
        return "register/index";
    }

    /**
     * Добавить нового клиента в бд
     * @param newClient - объект нового клиента
     * @param model
     * @return страницу регистрации или страницу логирования
     * TODO: вынести добавление сообщения и валидацию в отдельный метод
     */
    @PostMapping("/register")
    public ModelAndView addClient(@Valid Client newClient, BindingResult result, Model model){

        if (result.hasErrors()) {
            return new ModelAndView("register/index", "formErrors", result.getAllErrors());
        }

        Optional<Client> client = clientRepository.findByEmail(newClient.getEmail());
        if(client.isPresent()){
            model.addAttribute("message","Пользователь с таким email уже зарегистрирован");
            model.addAttribute("email",client.get().getEmail());
            return  new ModelAndView("register/index");
        }



        newClient.setStatus(Status.ACTIVE);
        newClient.setRole(Role.USER);
        newClient.setPass(BCrypt.hashpw(newClient.getPass(), BCrypt.gensalt(12)));
        clientRepository.save(newClient);
        return new ModelAndView("redirect:/login");
    }

}
