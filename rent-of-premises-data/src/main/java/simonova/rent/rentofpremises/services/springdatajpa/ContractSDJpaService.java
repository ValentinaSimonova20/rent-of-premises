package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Contract;
import simonova.rent.rentofpremises.repositories.ContractRepository;
import simonova.rent.rentofpremises.services.ContractService;

import java.util.ArrayList;
import java.util.List;


/**
 * Сервис для взаимодействия с таблицей контракты (контракты с клиентами бизнес-центра)
 */
@Service
public class ContractSDJpaService implements ContractService {

    ContractRepository contractRepository;

    public ContractSDJpaService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> findAll() {
        List<Contract> contracts = new ArrayList<>();
        contractRepository.findAll().forEach(contracts::add);
        return contracts;
    }

    @Override
    public Contract findById(Long aLong) {
        return contractRepository.findById(aLong).orElse(null);
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void delete(Contract contract) {
        contractRepository.delete(contract);
    }

    @Override
    public void deleteById(Long aLong) {
        contractRepository.deleteById(aLong);
    }
}
