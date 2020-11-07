package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс оплата за аренду помещений бизнес-центра
 */
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity{

    /** Поле для хранения контракта, по которому совершена оплата*/
    @ManyToOne
    private Contract contract;

    /** Поле для хранения даты, когда была совершена оплата по договору*/
    private Date paymentDate;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
