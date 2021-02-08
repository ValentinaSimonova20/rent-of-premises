package simonova.rent.rentofpremises.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import simonova.rent.rentofpremises.dto.ApplicationDTO;
import simonova.rent.rentofpremises.model.Application;

@Component
public class ApplicationConverter {


    ModelMapper modelMapper;

    public ApplicationConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ApplicationConverter() {

    }

    public ApplicationDTO convertToDto(Application application){
        return modelMapper.map(application, ApplicationDTO.class);
    }

    public Application convertToEntity(ApplicationDTO applicationDTO){
        return modelMapper.map(applicationDTO, Application.class);
    }


}
