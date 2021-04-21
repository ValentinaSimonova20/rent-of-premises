package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Класс-сущность заявки на аренду помещения бизнес-центров
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

    private LocalDate startRent;

    private LocalDate endRent;

    /** Поле для хранения статуса заявки*/
    @Enumerated(value = EnumType.STRING)
    private AppStatus status;

    public Application() {}

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

    public LocalDate getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDate startRent) {
        this.startRent = startRent;
    }

    public LocalDate getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDate endRent) {
        this.endRent = endRent;
    }
}
