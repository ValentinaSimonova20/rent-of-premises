package simonova.rent.rentofpremises.controllers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.model.*;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;
import simonova.rent.rentofpremises.services.PremisesService;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final PremisesService premisesService;
    private final ApplicationService applicationService;
    private final UserService userService;



    public UserController(PremisesService premisesService, ApplicationService applicationService, UserService userService) {
        this.premisesService = premisesService;
        this.applicationService = applicationService;
        this.userService = userService;
    }

    /**
     * Главная страница клиентов бизнес-центра - список доступных площадей
     * @param model - контейнер, содержащий информацию приложения
     * @return html страница со списком площадей бизнес-центра
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
     * Открывает страницу просмотра офиса
     * @param id - идентификатор выбранного офиса
     * @param model - контейнер информации
     * @return html-страницу с расширенной информацией о выбранном офисе
     */
    @GetMapping("/areas/{id}/show")
    public String getAreaById(@PathVariable String id, Model model){

        model.addAttribute("premises", premisesService.findById(Long.valueOf(id)));
        ApplInfo application = new ApplInfo();
        model.addAttribute("applicationn", application);

        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User client = userService.findByEmail(currentPrincipalName);


        model.addAttribute("userRole", client.getRole().toString());

        return "areas/show";
    }

    @PostMapping("/areas/{id}/show")
    public String sendAppl(@PathVariable String id, @Valid @ModelAttribute("applicationn") ApplInfo applInfo, BindingResult result, Model model){
        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User client = userService.findByEmail(currentPrincipalName);
        Premises premises = premisesService.findById(Long.parseLong(id));


        // Добавить заявку
        Application newApp = new Application(client, premises, applInfo.getRentalPeriodYears(), applInfo.getRentalPeriodMonth(), applInfo.getAdditionalInfo(), AppStatus.WAIT_FOR_CONSIDERATION );
        applicationService.save(newApp);

        return "redirect:/areas/"+id+"/show";
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
        model.addAttribute("statuses",AppStatus.values());
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

    @GetMapping("/clientInfo/{id}/show")
    public String showClientInfo(Model model, @PathVariable Long id){
        model.addAttribute("client", userService.findById(id));
        return "clients/show";
    }

}
