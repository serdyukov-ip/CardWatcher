package ru.serdyukov.ilya.CardWatcher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serdyukov.ilya.CardWatcher.models.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    public List<Payment> findOneByCreditCardIdOrderByRecommendPaymentDtDesc(int id);

    public Payment findFirstByCreditCardIdOrderByRecommendPaymentDtDesc(int id);
}
