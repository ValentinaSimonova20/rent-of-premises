package simonova.rent.rentofpremises.model;

/**
 * Класс BaseEntity - базовый класс для всех таблиц. Содержит общее поле для всех сущностей - Id.
 */
public class BaseEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
