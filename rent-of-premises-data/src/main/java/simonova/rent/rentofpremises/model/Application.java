package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс заявки на аренду помещения бизнес-центров
 */
@Entity
@Table(name = "applications")
public class Application extends BaseEntity{


    /** Поле для хранения клиента, который подал заявку*/
    @ManyToOne
    private User user;

    /** Поле для хранения площади, на которую подана заявка*/
    @ManyToOne
    private Premises premises;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

    private Date startRent;

    private Date endRent;

    /** Поле для хранения статуса заявки*/
    @Enumerated(value = EnumType.STRING)
    private AppStatus status;


    public Application() {

    }

    public User getClient() {
        return user;
    }

    public void setClient(User client) {
        this.user = client;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public AppStatus getStatus() {
        return status;
    }

    public void setStatus(AppStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartRent() {
        return startRent;
    }

    public void setStartRent(Date startRent) {
        this.startRent = startRent;
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(Date endRent) {
        this.endRent = endRent;
    }
}
