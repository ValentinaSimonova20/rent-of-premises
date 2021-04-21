package simonova.rent.rentofpremises.dto;
/**
 * Базовый класс для классов-моделей для передачи данных между представлением и базой данных
 */
public class BaseDTO {
    private Long id;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public boolean isNew(){return this.id == null;}
}
