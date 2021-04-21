package simonova.rent.rentofpremises.dto;

import simonova.rent.rentofpremises.model.Role;
import simonova.rent.rentofpremises.model.Status;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * Базовый класс для классов-моделей пользователей
 */
public class PersonDTO extends BaseDTO{

    /** Поле для хранения фамилии */
    @Size(min = 3, max = 255)
    private String surname;

    /** Поле для хранения имени */
    @Size(min = 3, max = 255)
    private String name;

    /** Поле для хранения отчества */
    private String patronymic;

    /** Поле для хранения серии паспорта*/
    private String seriesNumberOfPassport;

    /** Поле для хранения номера паспорта */
    private String passportNumber;

    /** Поле для хранения номера телефона */
    private String phoneNumber;

    /** Поле для хранения электронной почты */
    @Size(min = 3, max = 255)
    @Email
    private String email;

    /** Поле для хранения пароля  */
    @Size(min = 8, max = 255)
    private String pass;

    /** Поле для хранения роли*/
    private Role role;

    /** Поле для хранения статуса*/
    private Status status;

    /** Поле для хранения названия организации*/
    private String organization;
    private String activity;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPatronymic() {return patronymic;}

    public void setPatronymic(String patronymic) {this.patronymic = patronymic;}

    public String getSeriesNumberOfPassport() {return seriesNumberOfPassport;}

    public void setSeriesNumberOfPassport(String seriesNumberOfPassport) {this.seriesNumberOfPassport = seriesNumberOfPassport;}

    public String getPassportNumber() {return passportNumber;}

    public void setPassportNumber(String passportNumber) {this.passportNumber = passportNumber;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

    public String getEmail() { return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPass() {return pass;}

    public void setPass(String pass) {this.pass = pass; }

    public Role getRole() {return role;}

    public void setRole(Role role) {this.role = role;}

    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status; }

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getOrganization() {return organization; }

    public void setOrganization(String organization) {this.organization = organization; }

    public String getActivity() {return activity;}

    public void setActivity(String activity) {this.activity = activity;}
}
