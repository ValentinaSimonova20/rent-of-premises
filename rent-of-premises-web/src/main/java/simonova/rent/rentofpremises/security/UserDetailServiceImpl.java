package simonova.rent.rentofpremises.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.repositories.UserRepository;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserRepository clientRepository;

    public UserDetailServiceImpl(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String email){

        User client1 = clientRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User doesn't exists"));


        return SecurityUser.fromUser(client1);

    }
}
