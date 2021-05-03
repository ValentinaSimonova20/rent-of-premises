package simonova.rent.rentofpremises.model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
/**
 * Класс клиенты бизнес-центра
 */
@Entity
@Table(name = "users")
public class User extends Person{
    /** Поле для хранения списка заявок клиента*/
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user") // CascadeType.ALL - если удалится информация о клиенте - удалится информация о всех его заявках
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Application> applications;
    public List<Application> getApplications() {
        return applications;
    }
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
