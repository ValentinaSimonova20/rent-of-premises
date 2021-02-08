package simonova.rent.rentofpremises.dto;

import simonova.rent.rentofpremises.model.AppStatus;
import simonova.rent.rentofpremises.model.Premises;
import simonova.rent.rentofpremises.model.User;


public class ApplicationDTO extends BaseDTO{

    /** Поле для хранения клиента, который подал заявку*/
    private User user;

    /** Поле для хранения площади, на которую подана заявка*/
    private Premises premises;

    /** Поле для хранения срока аренды в годах*/
    private int rentalPeriodYears;

    /** Поле для хранения срока аренды в месяцах (кол-во месяцев добавляется к кол-ву лет)*/
    private int rentalPeriodMonth;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

    /** Поле для хранения статуса заявки*/
    private AppStatus status;



    public ApplicationDTO(User user, Premises premises, int rentalPeriodYears, int rentalPeriodMonth, String additionalInfo, AppStatus status) {
        this.user = user;
        this.premises = premises;
        this.rentalPeriodYears = rentalPeriodYears;
        this.rentalPeriodMonth = rentalPeriodMonth;
        this.additionalInfo = additionalInfo;
        this.status = status;

    }

    public ApplicationDTO() {

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
