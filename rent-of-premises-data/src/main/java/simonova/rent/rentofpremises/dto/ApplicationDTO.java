package simonova.rent.rentofpremises.dto;

import simonova.rent.rentofpremises.model.AppStatus;

import java.util.Date;


public class ApplicationDTO extends BaseDTO{

    /** Поле для хранения клиента, который подал заявку*/
    private UserDTO user;

    /** Поле для хранения площади, на которую подана заявка*/
    private PremisesDTO premises;

    /** Поле для хранения дополнительной информации (вводится клиентом при подаче заявки)*/
    private String additionalInfo;

    private Date startRent;

    private Date endRent;

    /** Поле для хранения статуса заявки*/
    private AppStatus status;


    public ApplicationDTO(UserDTO user, PremisesDTO premises,  String additionalInfo, AppStatus status) {
        this.user = user;
        this.premises = premises;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
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
