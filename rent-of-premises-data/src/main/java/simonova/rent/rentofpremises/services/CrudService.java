package simonova.rent.rentofpremises.services;
import java.util.List;
/**
 * В интерфейсе определены общие для всех таблиц операции
 * @param <T> тип объекта (Client, Payment ...)
 * @param <L> тип первичного ключа
 */
public interface CrudService<T, L> {

    List<T> findAll();

    T findById(L id);

    T save(T object);

    void delete(T object);

    void deleteById(L id);
}
