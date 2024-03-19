package ru.serdyukov.ilya.CardWatcher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serdyukov.ilya.CardWatcher.models.Currency;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currency, Integer> {
}
