package simonova.rent.rentofpremises.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.services.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService clientService;

    private static final String VIEWS_REGISTER_FORM = "register/index";

    @Autowired
    public RegisterController(UserService clientService) {

        this.clientService = clientService;
    }

    /**
     * Отображение страницы регистрации
     * @return html страница регистрации
     */
    @GetMapping
    public String getRegisterPage(Model model){
        Person person = new Person();
        model.addAttribute("client",person);
        return VIEWS_REGISTER_FORM;
    }

    /**
     * Добавить нового клиента в бд
     * @param userDTO объект нового клиента
     * @param model контейнер информации приложения
     * @return страницу регистрации или страницу логирования
     */
    @PostMapping
    public String addClient(@Valid @ModelAttribute("client") UserDTO userDTO, BindingResult result, Model model){

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("client", userDTO);
            return VIEWS_REGISTER_FORM;
        }

        // Проверка на то, нет ли в базе данных пользователя с таким email
        UserDTO newClient = clientService.findByEmail(userDTO.getEmail());
        if(newClient != null){
            model.addAttribute("message","Пользователь с таким email уже зарегистрирован");
            model.addAttribute("client", userDTO);
            return  VIEWS_REGISTER_FORM;
        }

        userDTO.setStatus(Status.ACTIVE);
        userDTO.setRole(Role.USER);
        userDTO.setPass(BCrypt.hashpw(userDTO.getPass(), BCrypt.gensalt(12)));
        clientService.save(userDTO);
        return "redirect:/login";
    }

    @ModelAttribute("activePage")
    public String setActivePage(){
        return "reg";
    }

}
