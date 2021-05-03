package simonova.rent.rentofpremises.controllers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import simonova.rent.rentofpremises.converters.UserConverter;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * Контроллер, обрабатывающий действия администратора, связанные с добавлением клиентов
 * или изменением информации о них
 */
@Slf4j
@Controller
public class AdminController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Отображение списка пользователей
     * @param model модель приложения для передачи/получения данных
     * @return html страница
     */
    @Transactional
    @PreAuthorize("hasAuthority('developers:addUsers')")
    @GetMapping("/adminPage")
    public String showUsers(Model model) {

        model.addAttribute("activePage", "admin");
        return findPaginated(1, model);
    }

    /**
     * отображение списка пользователей постранично
     * @param pageNo номер страницы
     * @param model модель приложения
     * @return html страница
     */
    @Transactional
    @PreAuthorize("hasAuthority('developers:addUsers')")
    @GetMapping("users/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo, Model model) {
        int pageSize = 10;
        Page<User> page = userService.findPaginated(pageNo, pageSize);
        model.addAttribute("activePage", "admin");

        UserConverter userConverter = new UserConverter(modelMapper);
        List<UserDTO> users = new ArrayList<>();
        page.forEach(user -> users.add(userConverter.convertToDto(user)));

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users", users);

        return "admin/clientsList";
    }

    /**
     * Отображение страницы с редактированием информации о пользователе
     * @param userId id пользователя, информация о котором будет редактироваться
     * @param model модель приложения
     * @return html страница с формой редактирования информации о пользователе
     */
    @PreAuthorize("hasAuthority('developers:addUsers')")
    @GetMapping("/users/{userId}/edit")
    public String editUser(@PathVariable Long userId, Model model){
        model.addAttribute("client", userService.findById(userId));
        model.addAttribute("activePage", "admin");
        model.addAttribute("roles", Role.values());
        return "admin/addOrEditClient";
    }

    /**
     * Подтверждение внесенных изменений о пользователе и добавление их в базу данных
     * @param userDTO модель данных пользователя
     * @param result для проверки информации об ошибках
     * @param userId id пользователя, измененная информация о котором вносится в систему
     * @param model модель приложения
     * @return
     */
    @PreAuthorize("hasAuthority('developers:addUsers')")
    @PostMapping("/users/{userId}/edit")
    public String editUserPost(@Valid @ModelAttribute("client") UserDTO userDTO, BindingResult result, @PathVariable Long userId, Model model){

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("client", userDTO);
            model.addAttribute("roles", Role.values());
            model.addAttribute("activePage", "admin");
            return "admin/addOrEditClient";
        }

        userDTO.setId(userId);
        userDTO.setStatus(Status.ACTIVE);

        if(userDTO.getPass() == null)
            userDTO.setPass(userService.findById(userId).getPass());

        UserDTO savedUser = userService.save(userDTO);

        return "redirect:/adminPage";
    }


    /**
     * Отображение страницы с формой добавления нового пользователя в систему
     * @param model модель приложения
     * @return html-страница с формой добавления
     */
    @PreAuthorize("hasAuthority('developers:addUsers')")
    @GetMapping("/users/add")
    public String addUserInitForm(Model model){
        model.addAttribute("client", new UserDTO());
        model.addAttribute("activePage", "addUser");
        model.addAttribute("roles", Role.values());
        return "admin/addOrEditClient";
    }

    /**
     * Добавление нового пользователя в систему
     * @param userDTO модель данных нового пользователя
     * @param result переменная с информацией об ошибках
     * @param model модель приложения
     * @return html-страница со списком всех пользователей
     */
    @PreAuthorize("hasAuthority('developers:addUsers')")
    @PostMapping("/users/add")
    public String addUserInitForm(@Valid @ModelAttribute("client") UserDTO userDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            model.addAttribute("client", userDTO);
            model.addAttribute("roles", Role.values());
            model.addAttribute("activePage", "addUser");
            return "admin/addOrEditClient";
        }

        if(userService.findByEmail(userDTO.getEmail())!=null){
            model.addAttribute("client", userDTO);
            model.addAttribute("message", "Пользователь с таким email уже зарегистрирован в системе");
            return "admin/addOrEditClient";
        }

        userDTO.setStatus(Status.ACTIVE);
        userDTO.setPass(BCrypt.hashpw(userDTO.getPass(), BCrypt.gensalt(12)));
        userService.save(userDTO);

        return "redirect:/adminPage";
    }

    @GetMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
        return "redirect:/adminPage";
    }




}
