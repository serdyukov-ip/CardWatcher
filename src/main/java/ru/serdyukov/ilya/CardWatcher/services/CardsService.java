package ru.serdyukov.ilya.CardWatcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serdyukov.ilya.CardWatcher.models.Card;
import ru.serdyukov.ilya.CardWatcher.repositories.CardsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CardsService {

    private final CardsRepository cardsRepository;

    @Autowired
    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public List<Card> findAll() {
        return cardsRepository.findAll();
    }

    public Card findOne(int id) {
        Optional<Card> foundCard = cardsRepository.findById(id);
        return foundCard.orElse(null);
    }

    @Transactional
    public void save(Card card) {
        cardsRepository.save(card);
    }

    @Transactional
    public void update(int id, Card card) {
        card.setId(id);
        cardsRepository.save(card);
    }

    @Transactional
    public void delete(int id) {
        cardsRepository.deleteById(id);
    }

}
