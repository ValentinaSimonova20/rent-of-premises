package simonova.rent.rentofpremises.model;

import javax.persistence.*;

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

    /** Поле для хранения срока аренды в годах*/
    private int rentalPeriodYears;

    /** Поле для хранения срока аренды в месяцах (кол-во месяцев добавляется к кол-ву лет)*/
    private int rentalPeriodMonth;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

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

    public int getRentalPeriodYears() {
        return rentalPeriodYears;
    }

    public void setRentalPeriodYears(int rentalPeriodYears) {
        this.rentalPeriodYears = rentalPeriodYears;
    }

    public int getRentalPeriodMonth() {
        return rentalPeriodMonth;
    }

    public void setRentalPeriodMonth(int rentalPeriodMonth) {
        this.rentalPeriodMonth = rentalPeriodMonth;
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

}
