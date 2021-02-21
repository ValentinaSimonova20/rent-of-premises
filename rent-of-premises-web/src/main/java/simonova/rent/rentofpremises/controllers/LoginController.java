package simonova.rent.rentofpremises.controllers;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.services.UserService;

/**
 * Контроллер отображает страницу авторизациию В нем также определны страницы, которые отображаюются
 * после авторизации того или иного пользователя (менеджер, клиент)
 */
@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("activePage","login");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication  instanceof AnonymousAuthenticationToken)){
            String currentPrincipalName = authentication.getName();
            User user = userService.findByEmail(currentPrincipalName);
            model.addAttribute("userRole", user.getRole().toString());

        }
        else {
            model.addAttribute("userRole", "None");

        }

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
