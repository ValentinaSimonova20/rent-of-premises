package simonova.rent.rentofpremises.model;

public class FilterArea implements Cloneable{
    private Double priceMin;
    private Double priceMax;
    private Integer floor;

    private Double areaMin;
    private Double areaMax;

    private Integer workplaces;

    private String priceSort;

    public FilterArea(){}

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getAreaMin() {
        return areaMin;
    }

    public void setAreaMin(Double areaMin) {
        this.areaMin = areaMin;
    }

    public Double getAreaMax() {
        return areaMax;
    }

    public void setAreaMax(Double areaMax) {
        this.areaMax = areaMax;
    }

    public Integer getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(Integer workplaces) {
        this.workplaces = workplaces;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }
}
