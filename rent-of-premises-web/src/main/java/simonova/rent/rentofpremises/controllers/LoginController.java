package simonova.rent.rentofpremises.controllers;

import org.dom4j.rule.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import simonova.rent.rentofpremises.model.Client;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.repositories.ClientRepository;

import java.util.Optional;

@Controller
public class LoginController {

    ClientRepository clientRepository;

    public LoginController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "index";
    }

    /**
     * Главная страница клиента
     * @param model
     * @return html страница клиента
     */
    @GetMapping("/success")
    public String getSuccessPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("user_name",authentication.getName());
        return "clients/index";
    }

    /**
     * Главная страница менеджера
     * @param model
     * @return страница менеджера
     */
    @GetMapping("/manager_success")
    public String getSuccessPageManager(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("user_name",authentication.getName());
        return "managers/index";
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
     * @param newClient
     * @param model
     * @return
     * // TODO: захешировать пароль и так его записать в БД. Убрать Контроллеры регистрации в отдельный файл
     */
    @PostMapping("/register")
    public String addClient(Client newClient, Model model){
        Optional<Client> client = clientRepository.findByEmail(newClient.getEmail());
        if(client.isPresent()){
            model.addAttribute("message","Пользователь с таким email уже зарегистрирован");
            return "register/index";
        }

        newClient.setStatus(Status.ACTIVE);
        newClient.setRole(Role.USER);
        clientRepository.save(newClient);
        return "redirect:/login";
    }


}
