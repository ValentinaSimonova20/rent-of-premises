package simonova.rent.rentofpremises.dto;

import simonova.rent.rentofpremises.model.AppStatus;



public class ApplicationDTO extends BaseDTO{

    /** Поле для хранения клиента, который подал заявку*/
    private UserDTO user;

    /** Поле для хранения площади, на которую подана заявка*/
    private PremisesDTO premises;

    /** Поле для хранения срока аренды в годах*/
    private int rentalPeriodYears;

    /** Поле для хранения срока аренды в месяцах (кол-во месяцев добавляется к кол-ву лет)*/
    private int rentalPeriodMonth;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

    /** Поле для хранения статуса заявки*/
    private AppStatus status;



    public ApplicationDTO(UserDTO user, PremisesDTO premises, int rentalPeriodYears, int rentalPeriodMonth, String additionalInfo, AppStatus status) {
        this.user = user;
        this.premises = premises;
        this.rentalPeriodYears = rentalPeriodYears;
        this.rentalPeriodMonth = rentalPeriodMonth;
        this.additionalInfo = additionalInfo;
        this.status = status;

    }

    public ApplicationDTO() {

    }

    public UserDTO getClient() {
        return user;
    }

    public void setClient(UserDTO client) {
        this.user = client;
    }

    public PremisesDTO getPremises() {
        return premises;
    }

    public void setPremises(PremisesDTO premises) {
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
