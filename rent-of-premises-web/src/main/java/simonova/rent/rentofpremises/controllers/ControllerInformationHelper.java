package simonova.rent.rentofpremises.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.services.UserService;

@ControllerAdvice
public class ControllerInformationHelper {

    private UserService userService;

    public ControllerInformationHelper(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRole")
    public String setUserRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Person.getAuthUserRole(authentication, userService).getRole().toString();

    }

}
