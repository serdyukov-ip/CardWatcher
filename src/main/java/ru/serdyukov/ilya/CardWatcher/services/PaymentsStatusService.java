package ru.serdyukov.ilya.CardWatcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serdyukov.ilya.CardWatcher.models.PaymentStatus;
import ru.serdyukov.ilya.CardWatcher.repositories.PaymentsStatusRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentsStatusService {

    private final PaymentsStatusRepository paymentsStatusRepository;

    @Autowired
    public PaymentsStatusService(PaymentsStatusRepository paymentsStatusRepository) {
        this.paymentsStatusRepository = paymentsStatusRepository;
    }

    public List<PaymentStatus> findAll() {
        return paymentsStatusRepository.findAll();
    }

    public PaymentStatus findOne(int id) {
        Optional<PaymentStatus> foundPaymentStatus = paymentsStatusRepository.findById(id);
        return foundPaymentStatus.orElse(null);
    }

    @Transactional
    public void save(PaymentStatus card) {
        paymentsStatusRepository.save(card);
    }

    @Transactional
    public void update(int id, PaymentStatus card) {
        card.setId(id);
        paymentsStatusRepository.save(card);
    }

    @Transactional
    public void delete(int id) {
        paymentsStatusRepository.deleteById(id);
    }
}
