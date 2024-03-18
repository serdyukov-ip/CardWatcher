package ru.serdyukov.ilya.CardWatcher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serdyukov.ilya.CardWatcher.models.Bank;
import ru.serdyukov.ilya.CardWatcher.models.Card;

@Repository
public interface BanksRepository extends JpaRepository<Bank, Integer> {
}
