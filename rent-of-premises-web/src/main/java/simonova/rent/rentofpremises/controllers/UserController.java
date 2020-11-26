package simonova.rent.rentofpremises.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import simonova.rent.rentofpremises.services.PremisesService;

@Controller
public class UserController {

    private final PremisesService premisesService;

    public UserController(PremisesService premisesService) {
        this.premisesService = premisesService;
    }

    /**
     * Главная страница клиентов бизнес-центра - список доступных площадей
     * @param model
     * @return
     */
    @GetMapping("/areas")
    public String areas(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("user_name",currentPrincipalName);

        // передача на страницу списка всех площадей
        model.addAttribute("premises", premisesService.findAll());

        return "clients/index";
    }
}
