package simonova.rent.rentofpremises.dto;

import org.hibernate.annotations.Type;
import simonova.rent.rentofpremises.model.Application;

import java.util.Set;

public class PremisesDTO extends BaseDTO{


    /** Поле для хранения названия помещения*/
    private String name;

    /** Поле для хранения описания помещения*/
    @Type(type = "org.hibernate.type.TextType")
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
