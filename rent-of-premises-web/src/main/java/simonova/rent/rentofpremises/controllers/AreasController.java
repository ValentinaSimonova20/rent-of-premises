package simonova.rent.rentofpremises.controllers;
import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.model.*;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;
import simonova.rent.rentofpremises.services.PremisesService;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class AreasController {

    private final PremisesService premisesService;
    private final ApplicationService applicationService;
    private final UserService userService;
    private final static String premises = "premises";



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
        model.addAttribute("userRole", Person.getAuthUserRole(authentication, userService));

        // передача на страницу списка всех площадей
        model.addAttribute(premises, premisesService.findAll());
        model.addAttribute("floors", premisesService.getAllFloors());


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

        model.addAttribute(premises, premisesService.findById(Long.valueOf(id)));
        Application application = new Application();
        model.addAttribute("application", application);

        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User client = userService.findByEmail(currentPrincipalName);
        model.addAttribute("floors", premisesService.getAllFloors());


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
     * hasAuthority('developers:write') - проверяет существуют ли у пользователя права на просмотр данной страницы
     * в данном случае доступ имеет только менеджер todo: add controller for error page
     * @param model контейнер данных
     * @param id идентификатор клиента
     * @return html-страницу с информацией о клиенте
     */
    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/clientInfo/{id}/show")
    public String showClientInfo(Model model, @PathVariable Long id){
        model.addAttribute("client", userService.findById(id));
        return "clients/show";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/areas/{premisesId}/edit")
    public String updatePremises(@PathVariable Long premisesId, Model model){
        model.addAttribute(premises, premisesService.findById(premisesId));
        return "areas/addOrEditPremisesForm";
    }

   @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/areas/{premisesId}/edit")
    public String processUpdatePremises(@Valid Premises premises, BindingResult result, @PathVariable Long premisesId){
        // todo add validation
        premises.setId(premisesId);
        Premises savedPremises = premisesService.save(premises);
        return "redirect:/areas/"+savedPremises.getId()+"/show";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/areas/add")
    public String initFormPremises(Model model){
        model.addAttribute(premises, new Premises());
        return "areas/addOrEditPremisesForm";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/areas/add")
    public String addPremises(@Valid Premises premises){
        Premises savedPremises = premisesService.save(premises);
        return "redirect:/areas/"+savedPremises.getId()+"/show";
    }


    // Фильтрация помещений
    @PostMapping("/areas")
    public String filterAreas(@RequestParam(value = "areaName", required = false) Optional<String> areaName,
                              @RequestParam(value = "areaMax", required = false) Optional<Double> areaMax,
                              @RequestParam(value = "areaMin", required = false) Optional<Double> areaMin,
                              @RequestParam(value = "priceMin", required = false) Optional<Double> priceMin,
                              @RequestParam(value = "priceMax", required = false) Optional<Double> priceMax,
                              @RequestParam(value = "floor", required = false) Optional<Integer> floor,
                              @RequestParam(value = "workplace", required = false) Optional<Integer> workplace,
                              Model model){

        model.addAttribute("areaName", areaName.orElse(""));
        model.addAttribute("areaMax", areaMax.orElse(null));
        model.addAttribute("areaMin", areaMin.orElse(null));
        model.addAttribute("priceMin", priceMin.orElse(null));
        model.addAttribute("priceMax", priceMax.orElse(null));
        model.addAttribute("floorAttr", floor.orElse(-1));
        model.addAttribute("workplace", workplace.orElse(null));
        model.addAttribute("floors", premisesService.getAllFloors());

        int selectedFloor = floor.orElse(-1);

        if(selectedFloor == -1){
            model.addAttribute("premises", premisesService.findAllPremises(
                    areaName.orElse(""),
                    areaMax.orElseGet(premisesService::getMaxArea),areaMin.orElse(0.0),
                    workplace.orElseGet(premisesService::getMaxWorkplaces),
                    priceMin.orElse(0.0), priceMax.orElseGet(premisesService::getMaxPrice)));
        }
        else {
            model.addAttribute("premises", premisesService.findAllPremises(
                    areaName.orElse(""),
                    areaMax.orElseGet(premisesService::getMaxArea),areaMin.orElse(0.0), selectedFloor,
                    workplace.orElseGet(premisesService::getMaxWorkplaces),
                    priceMin.orElse(0.0), priceMax.orElseGet(premisesService::getMaxPrice)));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("userRole", Person.getAuthUserRole(authentication, userService));


        return "clients/index";

    }






}
