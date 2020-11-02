package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Класс контракты на аренду с клиентами бизнес-центра
 */
@Entity
public class Contracts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Поле для хранения заявки, по которой заключен договор*/
    @OneToOne
    private Application application;

    /** Поле для хранения сотрудника, который заключил договор*/
    @ManyToOne
    private Employees employee;

    /** Поле для хранения даты заключения договора*/
    private Date contractDate;

    /** Поле для хранения даты окончания договора*/
    private Date contractEndDate;

    /** Поле для хранения даты следующей оплаты помещения*/
    private Date nextPaymentDate;

    /** Поле для хранения списка оплат по данному договору*/
    @OneToMany(mappedBy = "contract")
    private Set<Payment> payments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
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
