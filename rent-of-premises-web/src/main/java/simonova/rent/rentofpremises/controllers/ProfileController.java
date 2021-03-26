package simonova.rent.rentofpremises.controllers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.services.UserService;

import javax.validation.Valid;

/**
 * Контроллер для обработки действий в пункте меню "Ваш профиль"
 */

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService clientService;
    private static final String VIEWS_PROFILE_FORM = "clients/profile";

    public ProfileController(UserService clientService) {
        this.clientService = clientService;
    }

    /**
     * Отображение страницы c профилем клиента или менеджера(личной информацией)
     * @return html страница с информацией пользователя
     */
    @PreAuthorize("hasAuthority('developers:read')")
    @GetMapping
    public String getProfile( Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("client", Person.getAuthUser(authentication, clientService));
        return VIEWS_PROFILE_FORM;
    }

    /**
     * Подтвердить изменения на странице профиля
     * @param userDTO информация об авторизованном клиенте
     * @return html страница с обновленной информацией пользователя
     */
    @PreAuthorize("hasAuthority('developers:read')")
    @PostMapping
    public String editProfile(@Valid @ModelAttribute("client") UserDTO userDTO, BindingResult result, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            userDTO.setRole(Person.getAuthUser(authentication, clientService).getRole());
            model.addAttribute("client", userDTO);
            return VIEWS_PROFILE_FORM;
        }


        // Проверка на то, нет ли в базе данных пользователя с таким email
        UserDTO newClient = clientService.findByEmail(userDTO.getEmail());
        if(newClient != null & !userDTO.getEmail().equals(Person.getAuthUser(authentication, clientService).getEmail())){
            model.addAttribute("message","Пользователь с таким email уже зарегистрирован");
            userDTO.setRole(Person.getAuthUser(authentication, clientService).getRole());
            model.addAttribute("client", userDTO);
            return  VIEWS_PROFILE_FORM;
        }

        UserDTO currentClient = Person.getAuthUser(authentication, clientService);

        userDTO.setId(currentClient.getId());
        userDTO.setPass(currentClient.getPass());
        userDTO.setStatus(currentClient.getStatus());
        userDTO.setRole(currentClient.getRole());

        // сохранить изменения в базе данных
        clientService.save(userDTO);
        model.addAttribute("client", userDTO);
        return VIEWS_PROFILE_FORM;

    }

    @ModelAttribute("activePage")
    public String setActivePage(){
        return "profile";
    }

    @ModelAttribute("userRole")
    public String setUserRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Person.getAuthUser(authentication, clientService).getRole().toString();

    }
}
