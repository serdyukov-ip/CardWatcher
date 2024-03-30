package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serdyukov.ilya.CardWatcher.models.Bank;
import ru.serdyukov.ilya.CardWatcher.repositories.BanksRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BanksServiceTest {

    @Mock
    private BanksRepository banksRepository;

    @InjectMocks
    private BanksService banksService;

    @Test
    @DisplayName("`findAll` method should return all banks")
    void findAll_shouldReturnAllBanks() {
        // given
        List<Bank> banks = Arrays.asList(
                new Bank(),
                new Bank()
        );
        when(banksRepository.findAll()).thenReturn(banks);

        // when
        List<Bank> result = banksService.findAll();

        // then
        assertThat(result).isEqualTo(banks);
    }

    @Test
    @DisplayName("`findOne` method should return a bank when it exists")
    void findOne_shouldReturnBank_whenExists() {
        // given
        int id = 1;
        Bank bank = new Bank();
        when(banksRepository.findById(id)).thenReturn(Optional.of(bank));

        // when
        Bank result = banksService.findOne(id);

        // then
        assertThat(result).isEqualTo(bank);
    }

    @Test
    @DisplayName("`findOne` method should return `null` when the bank does not exist")
    void findOne_shouldReturnNull_whenNotExists() {
        // given
        int id = 1;
        when(banksRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Bank result = banksService.findOne(id);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("`save` method should save a bank")
    void save_shouldSaveBank() {
        // given
        Bank bank = new Bank();

        // when
        banksService.save(bank);

        // then
        verify(banksRepository).save(bank);
    }

    @Test
    @DisplayName("`update` method should update a bank")
    void update_shouldUpdateBank() {
        // given
        int id = 1;
        Bank bank = new Bank();

        // when
        banksService.update(id, bank);

        // then
        verify(banksRepository).save(bank);
    }

    @Test
    @DisplayName("`delete` method should delete a bank")
    void delete_shouldDeleteBank() {
        // given
        int id = 1;

        // when
        banksService.delete(id);

        // then
        verify(banksRepository).deleteById(id);
    }
}