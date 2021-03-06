package simonova.rent.rentofpremises.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class Conf {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
