package simonova.rent.rentofpremises.controllers;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
import simonova.rent.rentofpremises.converters.ApplicationConverter;
import simonova.rent.rentofpremises.converters.PremisesConverter;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.*;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;
import simonova.rent.rentofpremises.services.PremisesService;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
public class AreasController {

    private final PremisesService premisesService;
    private final ApplicationService applicationService;
    private final UserService userService;
    private List<PremisesDTO> premisesDTOS;
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
        premisesDTOS = new ArrayList<>();
        model.addAttribute("filter", new FilterArea());
        Page<Premises> page = premisesService.findByIsRented(false,1,3);
        ModelMapper modelMapper = new ModelMapper();
        PremisesConverter premisesConverter = new PremisesConverter(modelMapper);
        page.forEach(prem -> premisesDTOS.add(premisesConverter.convertToDTO(prem)));
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return findPaginated(1,  model);
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

        return "areas/show";
    }

    /**
     * Подать заявку на аренду данного офиса
     * @param id идентификатор офиса
     * @param applicationDTO информация о заявке на аренду
     * @return html-страницу с информацией об офисе
     */
    @PostMapping("/areas/{id}/show")
    public String sendApplication(@PathVariable Long id, @Valid @ModelAttribute("application") ApplicationDTO applicationDTO,
                                  @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                  @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate, Model model){
        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO client = Person.getAuthUser(authentication, userService);
        PremisesDTO premisesList = premisesService.findById(id);

        // Проверка подавал ли уже пользователь заявку на аренду данной площади
        if(applicationService.findByUserIdAndPremisesId(client.getId(), id) == null){
            // Добавить заявку
            ApplicationDTO newApp = new ApplicationDTO(client, premisesList,  applicationDTO.getAdditionalInfo(), AppStatus.WAIT_FOR_CONSIDERATION );
            newApp.setStartRent(startDate);
            newApp.setEndRent(endDate);
            applicationService.save(newApp);
            return "redirect:/applications";
        }
        else {
            model.addAttribute("message", "Вы уже подали заявку на данную площадь");
            model.addAttribute(premises, premisesService.findById(id));
            model.addAttribute("application", applicationDTO);
            return "areas/show";
        }

    }

    @GetMapping("areas/page/{pageNo}")
    @Transactional
    public String findPaginated(@PathVariable int pageNo, Model model){
        int pageSize = 3;


        if(model.getAttribute("filter")==null) {
            premisesDTOS = new ArrayList<>();
            PremisesConverter premisesConverter = new PremisesConverter(new ModelMapper());
            Page<Premises> page = premisesService.findByIsRented(false,pageNo,pageSize);
            page.forEach(prem -> premisesDTOS.add(premisesConverter.convertToDTO(prem)));
            model.addAttribute("filter", new FilterArea());
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());


        }

        model.addAttribute(premises, premisesDTOS);


        model.addAttribute("currentPage", pageNo);


        return "clients/index";

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

        premisesDTO.setPhoto(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        // при добавлении площадь еще не сдана
        premisesDTO.setRented(false);


        // Загрузка фотографии помещения


        PremisesDTO savedPremises = premisesService.save(premisesDTO);


        return "redirect:/areas/"+savedPremises.getId()+"/show";
    }


    // Фильтрация помещений
    @PostMapping("/areas")
    @Transactional
    public String filterAreas(@Valid FilterArea filterArea, Model model){

        int pageSize = 3;
        Page<Premises> page;

        if(filterArea.getFloor() == -1){
            page = premisesService.findAllPremisesPaginated(filterArea, 1, pageSize);

        }
        else {
            page = premisesService.findAllPremisesPaginated(filterArea, filterArea.getFloor(), 1, pageSize);
        }

        premisesDTOS = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        PremisesConverter premisesConverter = new PremisesConverter(modelMapper);
        page.forEach(prem -> premisesDTOS.add(premisesConverter.convertToDTO(prem)));
        model.addAttribute("filter", filterArea);

        model.addAttribute(premises, premisesDTOS);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        return findPaginated(1, model);

    }

    @ModelAttribute("floors")
    public List<Integer> setFloorsForFilter(){
        return premisesService.getAllFloors();
    }



}
