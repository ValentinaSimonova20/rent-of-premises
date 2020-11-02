package simonova.rent.rentofpremises.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс помещения на аренду бизнес-центра
 */
@Entity
public class Premises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /** Поле для хранения названия помещения*/
    private String name;

    /** Поле для хранения описания помещения*/
    @Lob // для хранения большого объема данных
    private String description;

    /** Поле для хранения площади помещения (в м^2)*/
    private double area;

    /** Поле для хранения цены аренды за помещение (в руб./мес.)*/
    private double price;

    /** Поле для хранения этажа, на котором находится помещение*/
    private int floor;

    /** Поле для хранения рабочих мест в помещении*/
    private int  workplaces;

    //todo add photo of the premises
    //private Byte[] image

    /** Поле для хранения списка заявок нв данное помещение*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "premises")
    private Set<Application> applications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(int workplaces) {
        this.workplaces = workplaces;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}