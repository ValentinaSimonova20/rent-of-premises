package simonova.rent.rentofpremises.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import simonova.rent.rentofpremises.model.Client;
import simonova.rent.rentofpremises.services.ClientService;

import javax.validation.Valid;

@Controller
public class ProfileController {

    private final ClientService clientService;

    public ProfileController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Страница c профилем клиента(личной информацией)
     * @return
     */
    @GetMapping("/profile")
    public String getProfile( Model model){
        model.addAttribute("activePage","profile");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("client", clientService.findByEmail(currentPrincipalName));
        return "clients/profile";
    }

    @PostMapping("/profile")
    public String editProfile(@Valid Client client,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Client currentClient = clientService.findByEmail(currentPrincipalName);

        client.setId(currentClient.getId());
        client.setPass(currentClient.getPass());
        client.setStatus(currentClient.getStatus());
        client.setRole(currentClient.getRole());


        Client savedClient = clientService.save(client);
        return "clients/profile";

    }
}
