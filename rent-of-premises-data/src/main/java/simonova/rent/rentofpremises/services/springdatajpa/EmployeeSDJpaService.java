package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Employee;
import simonova.rent.rentofpremises.repositories.EmployeeRepository;
import simonova.rent.rentofpremises.services.EmployeeService;

import java.util.HashSet;
import java.util.Set;

/**
 * Севрис для взаимодействия с таблицей работников бизнес-центра
 */
@Service
public class EmployeeSDJpaService implements EmployeeService {


    EmployeeRepository employeeRepository;

    public EmployeeSDJpaService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Set<Employee> findAll() {
        Set<Employee> employees = new HashSet<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    @Override
    public Employee findById(Long aLong) {
        return employeeRepository.findById(aLong).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteById(Long aLong) {
        employeeRepository.deleteById(aLong);
    }
}
