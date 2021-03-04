package simonova.rent.rentofpremises.dto;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import simonova.rent.rentofpremises.model.Application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class PremisesDTO extends BaseDTO{


    /** Поле для хранения названия помещения*/
    @Size(min=3, max=255)
    private String name;

    /** Поле для хранения описания помещения*/
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    /** Поле для хранения площади помещения (в м^2)*/
    @NotNull(message = "Введите площадь помещения")
    private Double area;

    /** Поле для хранения цены аренды за помещение (в руб./мес.)*/
    @NotNull(message = "Введите стоимость аренды помещения")
    private Double price;

    /** Поле для хранения этажа, на котором находится помещение*/
    @NotNull(message = "Введите этаж помещения")
    @Range(min=1, max = 9)
    private Integer floor;

    /** Поле для хранения рабочих мест в помещении*/
    @NotNull(message = "Введите количество рабочих мест в помещении")
    private Integer  workplaces;

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
}
