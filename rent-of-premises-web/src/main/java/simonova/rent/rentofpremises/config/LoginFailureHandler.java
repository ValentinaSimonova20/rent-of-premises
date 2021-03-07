package simonova.rent.rentofpremises.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginFailureHandler implements AuthenticationFailureHandler {


    /**
     * Обработка ошибки входа
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     * @throws ServletException
     */

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException, ServletException {
        String email = request.getParameter("email");

        String redirectURL = "/login/error/" + email;

        response.sendRedirect(redirectURL);
    }
}
