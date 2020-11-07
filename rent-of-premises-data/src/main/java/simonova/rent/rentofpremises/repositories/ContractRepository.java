package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {
}
