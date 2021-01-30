package simonova.rent.rentofpremises.model;

public class ApplInfo {

    /** Поле для хранения срока аренды в годах*/
    private int rentalPeriodYears;

    /** Поле для хранения срока аренды в месяцах (кол-во месяцев добавляется к кол-ву лет)*/
    private int rentalPeriodMonth;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

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
}
