package simonova.rent.rentofpremises.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import simonova.rent.rentofpremises.exception.NoAppException;
import simonova.rent.rentofpremises.model.Person;
import simonova.rent.rentofpremises.services.UserService;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {


    UserService userService;

    public ControllerExceptionHandler(UserService userService) {
        this.userService = userService;
    }


    /**
     * Возвращает пользователю страницу с информацией об ошибке (ограничение доступа)
     * @param exception объект ошибки
     * @return modelAndView
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleForbiddenRequest(Exception exception){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.error("Handling forbidden exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/403error");

        modelAndView.addObject("exception", exception);
        modelAndView.addObject("activePage","applications");
        modelAndView.addObject("userRole", Person.getAuthUser(authentication, userService).getRole().toString());
        return modelAndView;


    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoAppException.class)
    public ModelAndView handleAppException(Exception exception){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.error("Handling not found exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors/400appError");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("activePage","applications");
        modelAndView.addObject("userRole", Person.getAuthUser(authentication, userService).getRole().toString());

        return modelAndView;

    }







}
