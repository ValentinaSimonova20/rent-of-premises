package simonova.rent.rentofpremises.model;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс помещения на аренду бизнес-центра
 */
@Entity
@Table(name = "premises")
public class Premises extends BaseEntity{


    /** Поле для хранения названия помещения*/
    private String name;

    /** Поле для хранения описания помещения*/
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    /** Поле для хранения площади помещения (в м^2)*/
    private Double area;

    /** Поле для хранения цены аренды за помещение (в руб./мес.)*/
    private Double price;

    /** Поле для хранения этажа, на котором находится помещение*/
    private Integer floor;

    /** Поле для хранения рабочих мест в помещении*/
    private Integer  workplaces;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String photo;

    /** Поле для хранения списка заявок нв данное помещение*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "premises")
    private Set<Application> applications;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(Integer workplaces) {
        this.workplaces = workplaces;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
