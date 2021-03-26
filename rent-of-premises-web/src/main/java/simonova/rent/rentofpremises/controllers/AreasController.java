package simonova.rent.rentofpremises.controllers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.*;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;
import simonova.rent.rentofpremises.services.PremisesService;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@Transactional
public class AreasController {

    private final PremisesService premisesService;
    private final ApplicationService applicationService;
    private final UserService userService;
    private static final String VIEWS_ADD_OR_EDIT_PREMISES_FORM = "areas/addOrEditPremisesForm";
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
    @Transactional
    @GetMapping({"/areas", "/"})
    public String getAreas(Model model){

        // передача на страницу списка всех площадей
        model.addAttribute(premises, premisesService.findByIsRented(false));
        model.addAttribute("filter", new FilterArea());
        model.addAttribute("floors", premisesService.getAllFloors());


        return "clients/index";
    }

    /**
     * Открывает страницу просмотра офиса
     * @param id - идентификатор выбранного офиса
     * @param model - контейнер информации
     * @return html-страницу с расширенной информацией о выбранном офисе
     */
    @Transactional
    @GetMapping("/areas/{id}/show")
    public String getAreaById(@PathVariable String id, Model model){

        model.addAttribute(premises, premisesService.findById(Long.valueOf(id)));
        Application application = new Application();
        model.addAttribute("application", application);

        return "areas/show";
    }

    /**
     * Подать заявку на аренду данного офиса
     * @param id идентификатор офиса
     * @param applicationDTO информация о заявке на аренду
     * @return html-страницу с информацией об офисе
     */
    @Transactional
    @PostMapping("/areas/{id}/show")
    public String sendApplication(@PathVariable String id, @Valid @ModelAttribute("application") ApplicationDTO applicationDTO,
                                  @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                  @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO client = Person.getAuthUser(authentication, userService);
        PremisesDTO premisesList = premisesService.findById(Long.parseLong(id));

        // Добавить заявку
        ApplicationDTO newApp = new ApplicationDTO(client, premisesList,  applicationDTO.getAdditionalInfo(), AppStatus.WAIT_FOR_CONSIDERATION );
        newApp.setStartRent(startDate);
        newApp.setEndRent(endDate);
        applicationService.save(newApp);

        return "redirect:/areas/"+id+"/show";
    }

    /**
     * Страница просмотра менеджером информаии об определенном клиенте
     * hasAuthority('developers:write') - проверяет существуют ли у пользователя права на просмотр данной страницы
     * в данном случае доступ имеет только менеджер
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
        return VIEWS_ADD_OR_EDIT_PREMISES_FORM;
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/areas/{premisesId}/edit")
    public String processUpdatePremises(@Valid @ModelAttribute("premises") PremisesDTO premisesDTO,@RequestParam("image") MultipartFile multipartFile, BindingResult result, @PathVariable Long premisesId, Model model) throws IOException {

       if (result.hasErrors()) {
           result.getAllErrors().forEach(objectError -> {
               log.debug(objectError.toString());
           });
           model.addAttribute(premises, premisesDTO);
           return VIEWS_ADD_OR_EDIT_PREMISES_FORM;
       }


       premisesDTO.setId(premisesId);

       // Проверка на то, изменил ли пользователь изображение площади
        // Если пользователь не менял изображение, файл передастся пустой
       if(multipartFile.getSize() != 0){
           premisesDTO.setPhoto(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
       }
       else {
           premisesDTO.setPhoto(premisesService.findById(premisesId).getPhoto());
       }

        PremisesDTO savedPremises = premisesService.save(premisesDTO);

        return "redirect:/areas/"+savedPremises.getId()+"/show";
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/areas/add")
    public String initFormPremises(Model model){
        model.addAttribute("activePage", "addArea");
        model.addAttribute(premises, new PremisesDTO());
        return VIEWS_ADD_OR_EDIT_PREMISES_FORM;
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/areas/add")
    public String addPremises(@Valid @ModelAttribute("premises") PremisesDTO premisesDTO,BindingResult result, @RequestParam("image") MultipartFile multipartFile,  Model model) throws IOException {

        if (result.hasErrors() | multipartFile.getSize() > 1_000_000) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute(premises, premisesDTO);

            if(multipartFile.getSize() > 1_000_000)
                model.addAttribute("fileError", "размер файла не должен превышать 10 МБ");

            return VIEWS_ADD_OR_EDIT_PREMISES_FORM;
        }



        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        premisesDTO.setPhoto(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        // при добавлении площадь еще не сдана
        premisesDTO.setRented(false);


        // Загрузка фотографии помещения


        PremisesDTO savedPremises = premisesService.save(premisesDTO);


        return "redirect:/areas/"+savedPremises.getId()+"/show";
    }


    // Фильтрация помещений
    @Transactional
    @PostMapping("/areas")
    public String filterAreas(@Valid FilterArea filterArea,Model model){

        if(filterArea.getFloor() == -1){
            model.addAttribute(premises, premisesService.findAllPremises(filterArea));
        }
        else {
            model.addAttribute(premises, premisesService.findAllPremises(filterArea, filterArea.getFloor()));
        }

        model.addAttribute("filter", filterArea);


        return "clients/index";

    }

    @ModelAttribute("floors")
    public List<Integer> setFloorsForFilter(){
        return premisesService.getAllFloors();
    }







}
