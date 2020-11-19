package simonova.rent.rentofpremises.services;

import java.util.Set;

/**
 * В интерфейсе определены общие для всех таблиц операции
 * @param <T> тип объекта (Client, Payment ...)
 * @param <ID> тип первичного ключа
 */
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
