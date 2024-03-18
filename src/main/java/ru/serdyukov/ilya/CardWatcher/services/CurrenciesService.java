package ru.serdyukov.ilya.CardWatcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serdyukov.ilya.CardWatcher.models.Currency;
import ru.serdyukov.ilya.CardWatcher.repositories.CurrenciesRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrenciesService {

    private final CurrenciesRepository currenciesRepository;

    @Autowired
    public CurrenciesService(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }

    public List<ru.serdyukov.ilya.CardWatcher.models.Currency> findAll() {
        return currenciesRepository.findAll();
    }

    public ru.serdyukov.ilya.CardWatcher.models.Currency findOne(int id) {
        Optional<ru.serdyukov.ilya.CardWatcher.models.Currency> foundCurrency = currenciesRepository.findById(id);
        return foundCurrency.orElse(null);
    }

    @Transactional
    public void save(ru.serdyukov.ilya.CardWatcher.models.Currency currency) {
        currenciesRepository.save(currency);
    }

    @Transactional
    public void update(int id, Currency currency) {
        currency.setId(id);
        currenciesRepository.save(currency);
    }

    @Transactional
    public void delete(int id) {
        currenciesRepository.deleteById(id);
    }
}
