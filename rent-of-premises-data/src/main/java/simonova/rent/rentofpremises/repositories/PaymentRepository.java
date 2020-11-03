package simonova.rent.rentofpremises.repositories;

import org.springframework.data.repository.CrudRepository;
import simonova.rent.rentofpremises.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
