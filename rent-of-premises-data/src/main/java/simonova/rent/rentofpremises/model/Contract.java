package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Класс контракты на аренду с клиентами бизнес-центра
 */
@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity{


    /** Поле для хранения заявки, по которой заключен договор*/
    @OneToOne
    private Application application;

    /** Поле для хранения сотрудника, который заключил договор*/
    @ManyToOne
    private User user;

    /** Поле для хранения даты заключения договора*/
    private Date contractDate;

    /** Поле для хранения даты окончания договора*/
    private Date contractEndDate;

    /** Поле для хранения даты следующей оплаты помещения*/
    private Date nextPaymentDate;

    /** Поле для хранения списка оплат по данному договору*/
    @OneToMany(mappedBy = "contract")
    private Set<Payment> payments;

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public User getEmployee() {
        return user;
    }

    public void setEmployee(User employee) {
        this.user = employee;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
