package simonova.rent.rentofpremises.model;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import simonova.rent.rentofpremises.dto.UserDTO;
import simonova.rent.rentofpremises.services.UserService;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Базовый класс для объектов сотрудников и клиентов
 */
@MappedSuperclass
public class Person extends BaseEntity{

    /** Поле для хранения фамилии */
    private String surname;

    /** Поле для хранения имени */
    private String name;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /** Поле для хранения отчества */
    private String patronymic;

    /** Поле для хранения серии паспорта*/
    private String seriesNumberOfPassport;

    /** Поле для хранения номера паспорта */
    private String passportNumber;

    /** Поле для хранения номера телефона */
    private String phoneNumber;


    /** Поле для хранения электронной почты */
    private String email;

    /** Поле для хранения пароля  */
    private String pass;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

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

    public Role getRole() {
        return role;
    }

    public static UserDTO getAuthUser(Authentication auth, UserService clientService){
        if(!(auth  instanceof AnonymousAuthenticationToken)){
            String currentPrincipalName = auth.getName();
            return clientService.findByEmail(currentPrincipalName);

        }
        else {
            UserDTO anon = new UserDTO();
            anon.setRole(Role.NONE);
            return  anon;
        }
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    private String organization;
    private String activity;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
