package ru.serdyukov.ilya.CardWatcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serdyukov.ilya.CardWatcher.models.Payment;
import ru.serdyukov.ilya.CardWatcher.repositories.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentsService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentsService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findOne(int id) {
        Optional<Payment> findPayment = paymentRepository.findById(id);
        return findPayment.orElse(null);
    }

    public List<Payment> findOneByCreditCardId(int id) {
        return paymentRepository.findOneByCreditCardIdOrderByRecommendPaymentDtDesc(id);
    }

    @Transactional
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Transactional
    public void update(int id, Payment payment) {
        payment.setId(id);
        paymentRepository.save(payment);
    }

    @Transactional
    public void delete(int id) {
        paymentRepository.deleteById(id);
    }

}
