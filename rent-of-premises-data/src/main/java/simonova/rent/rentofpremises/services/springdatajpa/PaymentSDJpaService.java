package simonova.rent.rentofpremises.services.springdatajpa;

import org.springframework.stereotype.Service;
import simonova.rent.rentofpremises.model.Payment;
import simonova.rent.rentofpremises.repositories.PaymentRepository;
import simonova.rent.rentofpremises.services.PaymentService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сервис для взаимодействия с таблицей платежи (оплата клиентов за аренду площадей)
 */
@Service
public class PaymentSDJpaService implements PaymentService {

    PaymentRepository paymentRepository;

    public PaymentSDJpaService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        paymentRepository.findAll().forEach(payments::add);
        return payments;
    }

    @Override
    public Payment findById(Long aLong) {
        return paymentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public void deleteById(Long aLong) {
        paymentRepository.deleteById(aLong);
    }
}
