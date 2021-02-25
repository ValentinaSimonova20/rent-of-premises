package simonova.rent.rentofpremises.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Контроллер отображает страницу авторизациию В нем также определны страницы, которые отображаюются
 * после авторизации того или иного пользователя (менеджер, клиент)
 */
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(){
        return "index";
    }

    /**
     * Главная страница клиента
     * @return html страница клиента
     */
    @GetMapping("/success")
    public String getSuccessPage(){

        return "clients/index";
    }


    @ModelAttribute("activePage")
    public String setActivePage(){
        return "login";
    }


}
