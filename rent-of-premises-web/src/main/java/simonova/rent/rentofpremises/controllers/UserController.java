package simonova.rent.rentofpremises.controllers;

import org.dom4j.rule.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import simonova.rent.rentofpremises.model.Premises;
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
     * Подать заявку на площадь
     * @param id
     * @param model
     * @return
     */
    @GetMapping("areas/{id}/show/sendApp")
    public String sendApp(@PathVariable String id, Model model){

        System.out.println(id);
        System.out.println("заявка на площадь подана");

        return "redirect:/areas/"+id+"/show";
    }

    /**
     * Открывает страницу просмотра офиса
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/areas/{id}/show")
    public String getAreaById(@PathVariable String id, Model model){

        model.addAttribute("premises", premisesService.findById(Long.valueOf(id)));

        return "areas/show";
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
