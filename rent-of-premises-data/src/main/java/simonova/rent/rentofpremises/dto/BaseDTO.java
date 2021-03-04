package simonova.rent.rentofpremises.dto;

public class BaseDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew(){
        return this.id == null;
    }
}
