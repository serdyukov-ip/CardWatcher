package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serdyukov.ilya.CardWatcher.models.Currency;
import ru.serdyukov.ilya.CardWatcher.repositories.CurrenciesRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrenciesServiceTest {

    @Mock
    private CurrenciesRepository currenciesRepository;

    @InjectMocks
    private CurrenciesService currenciesService;

    @Test
    @DisplayName("`findAll` method should return all currencies")
    void findAll_shouldReturnAllCurrencies() {
        // given
        List<Currency> currencies = Arrays.asList(
                new Currency(),
                new Currency()
        );
        when(currenciesRepository.findAll()).thenReturn(currencies);

        // when
        List<Currency> result = currenciesService.findAll();

        // then
        assertThat(result).isEqualTo(currencies);
    }

    @Test
    @DisplayName("`findOne` method should return a currency when it exists")
    void findOne_shouldReturnCurrency_whenExists() {
        // given
        int id = 1;
        Currency currency = new Currency();
        when(currenciesRepository.findById(id)).thenReturn(Optional.of(currency));

        // when
        Currency result = currenciesService.findOne(id);

        // then
        assertThat(result).isEqualTo(currency);
    }

    @Test
    @DisplayName("`findOne` method should return `null` when the currency does not exist")
    void findOne_shouldReturnNull_whenNotExists() {
        // given
        int id = 1;
        when(currenciesRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Currency result = currenciesService.findOne(id);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("`save` method should save a currency")
    void save_shouldSaveCurrency() {
        // given
        Currency currency = new Currency();

        // when
        currenciesService.save(currency);

        // then
        verify(currenciesRepository).save(currency);
    }

    @Test
    @DisplayName("`update` method should update a currency")
    void update_shouldUpdateCurrency() {
        // given
        int id = 1;
        Currency currency = new Currency();

        // when
        currenciesService.update(id, currency);

        // then
        verify(currenciesRepository).save(currency);
    }

    @Test
    @DisplayName("`delete` method should delete a currency")
    void delete_shouldDeleteCurrency() {
        // given
        int id = 1;

        // when
        currenciesService.delete(id);

        // then
        verify(currenciesRepository).deleteById(id);
    }
}
