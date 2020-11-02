package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс клиенты бизнес-центра
 */
@Entity
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Поле для хранения фамилии клиента*/
    private String surname;

    /** Поле для хранения имени клиента*/
    private String name;

    /** Поле для хранения отчества клиента*/
    private String patronymic;

    /** Поле для хранения серии паспорта клиента*/
    private String seriesNumberOfPassport;

    /** Поле для хранения номера паспорта клиента*/
    private String passportNumber;

    /** Поле для хранения номера телефона клиента*/
    private String phoneNumber;

    /** Поле для хранения логина клиента*/
    private String login;

    /** Поле для хранения электронной почты клиента*/
    private String email;

    /** Поле для хранения пароля клиента */
    private String pass;

    /** Поле для хранения списка заявок клиента*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client") // CascadeType.ALL - если удалится информация о клиенте - удалится информация о всех его заявках
    private Set<Application> applications;


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

    public String getSeriesNumberOfPassport() {
        return seriesNumberOfPassport;
    }

    public void setSeriesNumberOfPassport(String seriesNumberOfPassport) {
        this.seriesNumberOfPassport = seriesNumberOfPassport;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
