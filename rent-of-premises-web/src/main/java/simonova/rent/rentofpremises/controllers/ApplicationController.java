package simonova.rent.rentofpremises.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simonova.rent.rentofpremises.converters.ApplicationConverter;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.exception.NoAppException;
import simonova.rent.rentofpremises.model.AppStatus;
import simonova.rent.rentofpremises.model.Application;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.services.ApplicationService;
import simonova.rent.rentofpremises.services.UserService;

import java.util.ArrayList;
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
    @GetMapping("/applications")
    public String getApplications(Model model){
        // получить информацию об авторизованном пользователе
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof AnonymousAuthenticationToken){

            throw new NoAppException("Зарегистрирутесь, для того чтобы подавать заявки и просматривать их.");
        }

        return findPaginated(1, model);

    }

    /**
     * Изменить статус выбранной заявки
     * @param appId - идентификатор заявки на аренду
     * @param appStatus - измененный статус заявки (выбранный менеджером)
     * @return html-страницу со списком заявок
     */
    @PostMapping("/app/{appId}/changeStat")
    public String changeStatus(@PathVariable Long appId, @RequestParam("stat") AppStatus appStatus){

        ApplicationDTO app = applicationService.findById(appId);
        app.setStatus(appStatus);
        applicationService.save(app);
        return "redirect:/applications";

    }

    @GetMapping("page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo, Model model){
        int pageSize = 3;

        Page<Application> page;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Person.getAuthUser(authentication,userService).getRole().toString().equals("MANAGER")){
            page = applicationService.findPaginated(pageNo, pageSize);
        }
        else {
            page = applicationService.findByUserId(Person.getAuthUser(authentication, userService).getId(),pageNo, pageSize);
        }

        ModelMapper modelMapper = new ModelMapper();
        ApplicationConverter applicationConverter = new ApplicationConverter(modelMapper);
        List<ApplicationDTO> applications = new ArrayList<>();
        page.forEach(application -> applications.add(applicationConverter.convertToDto(application)));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("statuses", AppStatus.values());
        model.addAttribute("apps", applications);
        return "clients/applications";

    }

    /**
     * Сераница заявок активная
     * @return свойство applications
     */
    @ModelAttribute("activePage")
    public String setActivePage(){
        return "applications";
    }


}
