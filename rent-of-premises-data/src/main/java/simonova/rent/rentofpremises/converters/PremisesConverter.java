package simonova.rent.rentofpremises.converters;

import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import simonova.rent.rentofpremises.dto.PremisesDTO;
import simonova.rent.rentofpremises.model.Premises;

@Component
public class PremisesConverter {

    ModelMapper modelMapper;

    public PremisesConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PremisesConverter(){

    }

    public PremisesDTO convertToDTO(Premises premises){
        return modelMapper.map(premises, PremisesDTO.class);
    }

    public Premises convertToEntity(PremisesDTO premisesDTO){
        return modelMapper.map(premisesDTO, Premises.class);
    }
}
