package simonova.rent.rentofpremises.controllers;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import simonova.rent.rentofpremises.dto.PersonDTO;
import simonova.rent.rentofpremises.dto.UserDTO;

import javax.validation.Valid;

/**
 * Контроллер отображает страницу авторизациию В нем также определны страницы, которые отображаюются
 * после авторизации того или иного пользователя (менеджер, клиент)
 */
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Model model){
        PersonDTO personDTO = new PersonDTO();
        model.addAttribute("user", personDTO);
        return "index";
    }

    @GetMapping("/login/error/{email}")
    public String redirect(Model model, @PathVariable String email){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEmail(email);
        model.addAttribute("user", personDTO);
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
