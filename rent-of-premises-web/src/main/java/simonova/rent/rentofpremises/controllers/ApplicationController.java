package simonova.rent.rentofpremises.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simonova.rent.rentofpremises.model.AppStatus;
import simonova.rent.rentofpremises.model.Application;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;

import java.util.List;

@Controller
public class ApplicationController {

    UserService userService;
    ApplicationService applicationService;

    public ApplicationController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    /**
     * Страница со списком заявок на аренду клиента
     * @return html-страницу со списком заявок клиента или со всеми заявками на аренду для менеджера
     */
    @Transactional
    @GetMapping("/applications")
    public String getApplications(Model model){
        model.addAttribute("activePage","applications");


        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByEmail(currentPrincipalName);
        List<Application> apps;
        if(user.getRole().toString().equals("USER")){
            // если роль пользователя - user(клиент) - высвечивать его заявки
            apps = applicationService.findByUserId(user.getId());
        }
        else {
            // если роль пользователя manager - высвечивать все заявки
            apps = applicationService.findAll();
        }

        model.addAttribute("apps", apps);
        model.addAttribute("statuses", AppStatus.values());
        model.addAttribute("role", user.getRole().toString());
        return "clients/applications";
    }

    /**
     * Изменить статус выбранной заявки
     * @param appId - идентификатор заявки на аренду
     * @param appStatus - измененный статус заявки (выбранный менеджером)
     * @return html-страницу со списком заявок
     */
    @Transactional
    @PostMapping("/app/{appId}/changeStat")
    public String changeStatus(@PathVariable Long appId, @RequestParam("stat") AppStatus appStatus){

        Application app = applicationService.findById(appId);
        app.setStatus(appStatus);
        applicationService.save(app);
        return "redirect:/applications";

    }
}
