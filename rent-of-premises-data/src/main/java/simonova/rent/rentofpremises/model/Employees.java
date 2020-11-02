package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс сотрудники бизнес-центра
 */
@Entity
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Поле для хранения фамилии сотрудника бизнес-центра*/
    private String surname;

    /** Поле для хранения имени сотрудника бизнес-центра*/
    private String name;

    /** Поле для хранения отчества сотрудника бизнес-центра*/
    private String patronymic;

    /** Поле для хранения логина сотрудника бизнес-центра*/
    private String login;

    /** Поле для хранения поля сотрудника бизнес-центра*/
    private String pass;

    /** Поле для хранения должности сотрудника бизнес-центра*/
    private String position;

    /** Поле для хранения списка договоров, которые заключил сотрудник*/
    @OneToMany(mappedBy = "employee")
    private Set<Contracts> contracts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contracts> contracts) {
        this.contracts = contracts;
    }
}
