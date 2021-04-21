package simonova.rent.rentofpremises.model;

/**
 * Класс для хранения признаков фильтрации помещений
 */
public class FilterArea implements Cloneable{
    /** Поле для хранения минимальной цены*/
    private Double priceMin;

    /** Поле для хранения максимальной цены*/
    private Double priceMax;

    /** Поле для хранения номера этажа*/
    private Integer floor;

    /** Поле для хранения минимальной площади*/
    private Double areaMin;

    /** Поле для хранения максимальной площади*/
    private Double areaMax;

    /** Поле для хранения количества рабочих мест*/
    private Integer workplaces;

    /** Поле для хранения сорировки по цене (по убыванию или возрастанию)*/
    private String priceSort;

    public FilterArea(){}

    public Double getPriceMin() {return priceMin;}

    public void setPriceMin(Double priceMin) {this.priceMin = priceMin;}

    public Double getPriceMax() {return priceMax;}

    public void setPriceMax(Double priceMax) {this.priceMax = priceMax; }

    public Integer getFloor() {return floor;}

    public void setFloor(Integer floor) {this.floor = floor;}

    public Double getAreaMin() {return areaMin; }

    public void setAreaMin(Double areaMin) {this.areaMin = areaMin;}

    public Double getAreaMax() {return areaMax;}

    public void setAreaMax(Double areaMax) {this.areaMax = areaMax;}

    public Integer getWorkplaces() {return workplaces;}

    public String rented;

    public String getRented() {return rented;}

    public void setRented(String rented) {this.rented = rented;}

    public void setWorkplaces(Integer workplaces) {this.workplaces = workplaces;}

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getPriceSort() {return priceSort;}

    public void setPriceSort(String priceSort) {this.priceSort = priceSort;}
}
