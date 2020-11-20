package simonova.rent.rentofpremises.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Класс клиенты бизнес-центра
 */
@Entity
@Table(name = "clients")
public class Client extends Person{


    /** Поле для хранения списка заявок клиента*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client") // CascadeType.ALL - если удалится информация о клиенте - удалится информация о всех его заявках
    private Set<Application> applications;


    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }


}
