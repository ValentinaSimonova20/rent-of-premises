package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Employees;

public interface EmployeesRepository extends CrudRepository<Employees, Long> {
}
