package simonova.rent.rentofpremises.services.springdatajpa;

import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.converters.UserConverter;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.User;
import simonova.rent.rentofpremises.repositories.UserRepository;
import simonova.rent.rentofpremises.services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для взаимодействия с таблицей клиентов бизнес-центра
 */
@Service
public class UserSDJpaService implements UserService {

    UserRepository clientRepository;

    public UserSDJpaService(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<UserDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        UserConverter userConverter = new UserConverter(modelMapper);

        List<UserDTO> clients = new ArrayList<>();

        clientRepository.findAll().forEach(client -> clients.add(userConverter.convertToDto(client)));
        return clients;
    }

    @Override
    @Transactional
    public UserDTO findByEmail(String email)
    {
        ModelMapper modelMapper = new ModelMapper();
        UserConverter userConverter = new UserConverter(modelMapper);
        User user = clientRepository.findByEmail(email).orElse(null);

        if(user== null) return null;

        return userConverter.convertToDto(user);
    }



    @Override
    public UserDTO findById(Long aLong) {
        ModelMapper modelMapper = new ModelMapper();

        UserConverter userConverter = new UserConverter(modelMapper);
        User user = clientRepository.findById(aLong).orElse(null);
        return userConverter.convertToDto(user);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();

        UserConverter userConverter = new UserConverter(modelMapper);

        User user = userConverter.convertToEntity(userDTO);
        clientRepository.save(user);

        return userConverter.convertToDto(user);
    }

    @Override
    public void delete(UserDTO userDTO) {
        UserConverter userConverter = new UserConverter(new ModelMapper());
        clientRepository.delete(userConverter.convertToEntity(userDTO));
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
