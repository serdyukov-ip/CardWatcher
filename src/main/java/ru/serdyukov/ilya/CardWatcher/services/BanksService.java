package ru.serdyukov.ilya.CardWatcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serdyukov.ilya.CardWatcher.models.Bank;
import ru.serdyukov.ilya.CardWatcher.repositories.BanksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BanksService {

    private final BanksRepository banksRepository;

    @Autowired
    public BanksService(BanksRepository banksRepository) {
        this.banksRepository = banksRepository;
    }

    public List<Bank> findAll() {
        return banksRepository.findAll();
    }

    public Bank findOne(int id) {
        Optional<Bank> foundBank = banksRepository.findById(id);
        return foundBank.orElse(null);
    }

    @Transactional
    public void save(Bank bank) {
        banksRepository.save(bank);
    }

    @Transactional
    public void update(int id, Bank bank) {
        bank.setId(id);
        banksRepository.save(bank);
    }

    @Transactional
    public void delete(int id) {
        banksRepository.deleteById(id);
    }
}
