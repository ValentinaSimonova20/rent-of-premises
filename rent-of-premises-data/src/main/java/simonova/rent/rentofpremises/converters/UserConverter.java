package simonova.rent.rentofpremises.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.model.User;


@Component
public class UserConverter {

    private ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserConverter(){

    }

   public UserDTO convertToDto(User user){
        return modelMapper.map(user, UserDTO.class);
   }

   public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
   }
}
