package simonova.rent.rentofpremises.controllers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.model.*;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;
import simonova.rent.rentofpremises.services.PremisesService;
import javax.validation.Valid;

@Controller
public class AreasController {

    private final PremisesService premisesService;
    private final ApplicationService applicationService;
    private final UserService userService;



    public AreasController(PremisesService premisesService, ApplicationService applicationService, UserService userService) {
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
        Application application = new Application();
        model.addAttribute("application", application);

        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User client = userService.findByEmail(currentPrincipalName);


        model.addAttribute("userRole", client.getRole().toString());

        return "areas/show";
    }

    /**
     * Подать заявку на аренду данного офиса
     * @param id идентификатор офиса
     * @param applicationDTO информация о заявке на аренду
     * @return html-страницу с информацией об офисе
     */
    @PostMapping("/areas/{id}/show")
    public String sendApplication(@PathVariable String id, @Valid @ModelAttribute("application") ApplicationDTO applicationDTO){
        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User client = userService.findByEmail(currentPrincipalName);
        Premises premises = premisesService.findById(Long.parseLong(id));

        // Добавить заявку
        ApplicationDTO newApp = new ApplicationDTO(client, premises, applicationDTO.getRentalPeriodYears(), applicationDTO.getRentalPeriodMonth(), applicationDTO.getAdditionalInfo(), AppStatus.WAIT_FOR_CONSIDERATION );
        applicationService.save(newApp);

        return "redirect:/areas/"+id+"/show";
    }

    /**
     * Страница просмотра менеджером информаии об определенном клиенте
     * @param model контейнер данных
     * @param id идентификатор клиента
     * @return html-страницу с информацией о клиенте
     */
    @GetMapping("/clientInfo/{id}/show")
    public String showClientInfo(Model model, @PathVariable Long id){
        model.addAttribute("client", userService.findById(id));
        return "clients/show";
    }

}
