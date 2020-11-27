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
    public String getAreas(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("user_name",currentPrincipalName);

        // передача на страницу списка всех площадей
        model.addAttribute("premises", premisesService.findAll());

        return "clients/index";
    }

    /**
     * Страница со списком заявок на аренду клиента
     * @return
     */
    @GetMapping("/applications")
    public String getApplications(Model model){
        model.addAttribute("activePage","applications");
        return "clients/applications";
    }

    /**
     * Страница c профилем клиента(личной информацией)
     * @return
     */
    @GetMapping("/profile")
    public String getContracts(Model model){
        model.addAttribute("activePage","profile");
        return "clients/profile";
    }
}
