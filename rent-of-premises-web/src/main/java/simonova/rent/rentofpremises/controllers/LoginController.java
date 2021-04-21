package simonova.rent.rentofpremises.controllers;
import org.dom4j.rule.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import simonova.rent.rentofpremises.dto.PersonDTO;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.services.UserService;

import javax.validation.Valid;

/**
 * Контроллер отображает страницу авторизациию В нем также определены страницы, которые отображаюются
 * после авторизации того или иного пользователя (менеджер, клиент)
 */
@Controller
public class LoginController {
    UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(Model model){
        PersonDTO personDTO = new PersonDTO();
        model.addAttribute("user", personDTO);
        return "index";
    }
    /**
     * Метод для сохранения введного email пользователем после неудачной попытки входа
     * @param model модель приложения
     * @param email введеная пользователем почта
     * @return
     */
    @GetMapping("/login/error/{email}")
    public String redirect(Model model, @PathVariable String email){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(email);
        model.addAttribute("user", personDTO);
        model.addAttribute("isLoginErr", true);
        return "index";
    }
    /**
     * Главная страница клиента
     * @return html страница клиента
     */
    @GetMapping("/success")
    public String getSuccessPage(){

        return "clients/index";
    }
    @ModelAttribute("activePage")
    public String setActivePage(){
        return "login";
    }
}
