package simonova.rent.rentofpremises.controllers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.services.UserService;

import javax.validation.Valid;

/**
 * Контроллер для обработки действий в пункте меню "Ваш профиль"
 */

@Controller
public class ProfileController {

    private final UserService clientService;

    public ProfileController(UserService clientService) {
        this.clientService = clientService;
    }

    /**
     * Отображение страницы c профилем клиента или менеджера(личной информацией)
     * @return html страница с информацией пользователя
     */
    @GetMapping("/profile")
    public String getProfile( Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();

        model.addAttribute("client", clientService.findByEmail(currentPrincipalName));
        return "clients/profile";
    }

    /**
     * Подтвердить изменения на странице профиля
     * @param userDTO информация об авторизованном клиенте
     * @return html страница с обновленной информацией пользователя
     */
    @PostMapping("/profile")
    public String editProfile(@Valid @ModelAttribute("client") UserDTO userDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDTO currentClient = clientService.findByEmail(currentPrincipalName);

        userDTO = currentClient;
        userDTO.setId(currentClient.getId());
        userDTO.setPass(currentClient.getPass());
        userDTO.setStatus(currentClient.getStatus());
        userDTO.setRole(currentClient.getRole());

        // сохранить изменения в базе данных
        clientService.save(userDTO);
        return "clients/profile";

    }

    @ModelAttribute("activePage")
    public String setActivePage(){
        return "profile";
    }
}
