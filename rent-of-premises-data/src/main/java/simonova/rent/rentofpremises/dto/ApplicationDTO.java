package simonova.rent.rentofpremises.dto;
import simonova.rent.rentofpremises.model.AppStatus;
import java.time.LocalDate;

/**
 * Класс- модель заявок для передачи данных между представлением и базой данных
 */
public class ApplicationDTO extends BaseDTO{

    /** Поле для хранения клиента, который подал заявку*/
    private UserDTO user;

    /** Поле для хранения площади, на которую подана заявка*/
    private PremisesDTO premises;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

    /** Поле для хранения примерного начала аренды клиента*/
    private LocalDate startRent;

    /** Поле для хранения примерного окончания аренды клиента*/
    private LocalDate endRent;

    /** Поле для хранения статуса заявки*/
    private AppStatus status;

    public ApplicationDTO(UserDTO user, PremisesDTO premises,  String additionalInfo, AppStatus status) {
        this.user = user;
        this.premises = premises;
        this.additionalInfo = additionalInfo;
        this.status = status;

    }

    public ApplicationDTO() {}

    public UserDTO getClient() { return user;}

    public void setClient(UserDTO client) {this.user = client;}

    public PremisesDTO getPremises() {return premises;}

    public void setPremises(PremisesDTO premises) {this.premises = premises;}

    public String getAdditionalInfo() {return additionalInfo;}

    public void setAdditionalInfo(String additionalInfo) {this.additionalInfo = additionalInfo; }

    public AppStatus getStatus() { return status;}

    public void setStatus(AppStatus status) {this.status = status;}

    public UserDTO getUser() {return user; }

    public void setUser(UserDTO user) {this.user = user;}

    public LocalDate getStartRent() {return startRent;}

    public void setStartRent(LocalDate startRent) {this.startRent = startRent;}

    public LocalDate getEndRent() {return endRent;}

    public void setEndRent(LocalDate endRent) {this.endRent = endRent;}
}
