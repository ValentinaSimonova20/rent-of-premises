package simonova.rent.rentofpremises.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Класс, отвечающий за переадресацию пользователей после авторизации в системе
 * Пользователь перенаправляется на определенную страницу в зависимости от его ролей(клиент, менеджер или администратор)
 */

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());


        if (roles.contains("developers:addUsers")) {
            httpServletResponse.sendRedirect("/admin");
        } else if(roles.contains("developers:write")){
            httpServletResponse.sendRedirect("/manager_success");
        }
        else {
            httpServletResponse.sendRedirect("/success");
        }


    }


}

