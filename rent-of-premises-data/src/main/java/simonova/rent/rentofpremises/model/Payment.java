package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Класс оплата за аренду помещений бизнес-центра
 */
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Поле для хранения контракта, по которому совершена оплата*/
    @ManyToOne
    private Contracts contract;

    /** Поле для хранения даты, когда была совершена оплата по договору*/
    private Date paymentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contracts getContract() {
        return contract;
    }

    public void setContract(Contracts contract) {
        this.contract = contract;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
