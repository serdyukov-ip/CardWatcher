package ru.serdyukov.ilya.CardWatcher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.serdyukov.ilya.CardWatcher.models.Card;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Card, Integer> {
    List<Card> findByIdUser(int id);
}
