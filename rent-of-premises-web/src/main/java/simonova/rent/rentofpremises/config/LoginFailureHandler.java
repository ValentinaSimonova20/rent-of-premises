package simonova.rent.rentofpremises.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Класс, отвечающий за обработку ошибки при ошибке авторизации
 * С помощью данного метода поле ввода email-а не будет очищаться
 */
@Configuration
public class LoginFailureHandler implements AuthenticationFailureHandler {
    /**
     * Обработка ошибки входа
     * @param request запрос
     * @param response ответ
     * @param exception объект ошибки
     * @throws IOException исключение
     * @throws ServletException исключение
     * @throws ServletException исключение
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException, ServletException {
        String email = request.getParameter("email");
        String redirectURL = "/login/error/" + email;
        response.sendRedirect(redirectURL);
    }
}
