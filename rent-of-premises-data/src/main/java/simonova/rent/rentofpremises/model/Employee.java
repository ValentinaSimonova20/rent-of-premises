package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс сотрудники бизнес-центра, производный класс от класса персона
 */
@Entity
@Table(name = "employees")
public class Employee extends Person{


    /** Поле для хранения списка договоров, которые заключил сотрудник*/
    @OneToMany(mappedBy = "employee")
    private Set<Contract> contracts;


    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
