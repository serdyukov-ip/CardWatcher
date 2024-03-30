package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serdyukov.ilya.CardWatcher.models.Payment;
import ru.serdyukov.ilya.CardWatcher.repositories.PaymentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentsServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentsService paymentsService;

    @Test
    @DisplayName("`findAll` method should return all payments")
    void findAll_shouldReturnAllPayments() {
        // given
        List<Payment> payments = Arrays.asList(
                new Payment(),
                new Payment()
        );
        when(paymentRepository.findAll()).thenReturn(payments);

        // when
        List<Payment> result = paymentsService.findAll();

        // then
        assertThat(result).isEqualTo(payments);
    }

    @Test
    @DisplayName("`findOne` method should return a payment when it exists")
    void findOne_shouldReturnPayment_whenExists() {
        // given
        int id = 1;
        Payment payment = new Payment();
        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        // when
        Payment result = paymentsService.findOne(id);

        // then
        assertThat(result).isEqualTo(payment);
    }

    @Test
    @DisplayName("`findOne` method should return `null` when the payment does not exist")
    void findOne_shouldReturnNull_whenNotExists() {
        // given
        int id = 1;
        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Payment result = paymentsService.findOne(id);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("`save` method should save a payment")
    void save_shouldSavePayment() {
        // given
        Payment payment = new Payment();

        // when
        paymentsService.save(payment);

        // then
        verify(paymentRepository).save(payment);
    }

    @Test
    @DisplayName("`update` method should update a payment")
    void update_shouldUpdatePayment() {
        // given
        int id = 1;
        Payment payment = new Payment();

        // when
        paymentsService.update(id, payment);

        // then
        verify(paymentRepository).save(payment);
    }

    @Test
    @DisplayName("`delete` method should delete a payment")
    void delete_shouldDeletePayment() {
        // given
        int id = 1;

        // when
        paymentsService.delete(id);

        // then
        verify(paymentRepository).deleteById(id);
    }
}