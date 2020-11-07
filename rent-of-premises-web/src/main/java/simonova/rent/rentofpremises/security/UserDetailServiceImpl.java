package simonova.rent.rentofpremises.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Client;
import simonova.rent.rentofpremises.model.Employee;
import simonova.rent.rentofpremises.repositories.ClientRepository;
import simonova.rent.rentofpremises.repositories.EmployeeRepository;

import java.util.Optional;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {


    private final ClientRepository clientRepository;


    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserDetailServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    private Optional<Client> findByClientName(String clientMail){
        return clientRepository.findByEmail(clientMail);
    }

    private Optional<Employee> findByEmployee(String employeeMail){
        return employeeRepository.findByEmail(employeeMail);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if(employee.isPresent()){
            Employee employee1 = employeeRepository.findByEmail(email).orElseThrow(()->
                    new UsernameNotFoundException("User doesn't exists"));
            return SecurityUser.fromUser(employee1);
        }

        Client client1 = clientRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(client1);




    }
}
