package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serdyukov.ilya.CardWatcher.models.PaymentStatus;
import ru.serdyukov.ilya.CardWatcher.repositories.PaymentsStatusRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentsStatusServiceTest {

    @Mock
    private PaymentsStatusRepository paymentsStatusRepository;

    @InjectMocks
    private PaymentsStatusService paymentsStatusService;

    @Test
    @DisplayName("`findAll` method should return all payment statuses")
    void findAll_shouldReturnAllPaymentStatuses() {
        // given
        List<PaymentStatus> paymentStatuses = Arrays.asList(
                new PaymentStatus(),
                new PaymentStatus()
        );
        when(paymentsStatusRepository.findAll()).thenReturn(paymentStatuses);

        // when
        List<PaymentStatus> result = paymentsStatusService.findAll();

        // then
        assertThat(result).isEqualTo(paymentStatuses);
    }

    @Test
    @DisplayName("`findOne` method should return a payment status when it exists")
    void findOne_shouldReturnPaymentStatus_whenExists() {
        // given
        int id = 1;
        PaymentStatus paymentStatus = new PaymentStatus();
        when(paymentsStatusRepository.findById(id)).thenReturn(Optional.of(paymentStatus));

        // when
        PaymentStatus result = paymentsStatusService.findOne(id);

        // then
        assertThat(result).isEqualTo(paymentStatus);
    }

    @Test
    @DisplayName("find one should return null when not exists")
    void findOne_shouldReturnNull_whenNotExists() {
        // given
        int id = 1;
        when(paymentsStatusRepository.findById(id)).thenReturn(Optional.empty());

        // when
        PaymentStatus result = paymentsStatusService.findOne(id);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("save should save payment status")
    void save_shouldSavePaymentStatus() {
        // given
        PaymentStatus paymentStatus = new PaymentStatus();

        // when
        paymentsStatusService.save(paymentStatus);

        // then
        verify(paymentsStatusRepository).save(paymentStatus);
    }

    @Test
    @DisplayName("update should update payment status")
    void update_shouldUpdatePaymentStatus() {
        // given
        int id = 1;
        PaymentStatus paymentStatus = new PaymentStatus();

        // when
        paymentsStatusService.update(id, paymentStatus);

        // then
        verify(paymentsStatusRepository).save(paymentStatus);
    }

    @Test
    @DisplayName("delete should delete payment status")
    void delete_shouldDeletePaymentStatus() {
        // given
        int id = 1;

        // when
        paymentsStatusService.delete(id);

        // then
        verify(paymentsStatusRepository).deleteById(id);
    }
}