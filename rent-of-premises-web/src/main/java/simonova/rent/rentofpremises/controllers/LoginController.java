package simonova.rent.rentofpremises.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер отображает страницу авторизациию В нем также определны страницы, которые отображаюются
 * после авторизации того или иного пользователя (менеджер, клиент)
 */
@Controller
public class LoginController {


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

        model.addAttribute("user_name",currentPrincipalName);
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

        model.addAttribute("user_name",currentPrincipalName);
        return "managers/index";
    }




}
