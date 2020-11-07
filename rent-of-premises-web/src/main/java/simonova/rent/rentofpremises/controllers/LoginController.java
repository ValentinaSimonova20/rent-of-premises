package simonova.rent.rentofpremises.controllers;

import org.dom4j.rule.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "index";
    }

    @GetMapping("/success")
    public String getSuccessPage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("user_name",authentication.getName());
        return "clients/index";
    }
}
