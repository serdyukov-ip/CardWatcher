package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serdyukov.ilya.CardWatcher.models.Card;
import ru.serdyukov.ilya.CardWatcher.repositories.CardsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardsServiceTest {

    @Mock
    private CardsRepository cardsRepository;

    @InjectMocks
    private CardsService cardsService;

    @Test
    @DisplayName("`findAll` method should return all cards")
    void findAll_shouldReturnAllCards() {
        // given
        List<Card> cards = Arrays.asList(
                new Card(),
                new Card()
        );
        when(cardsRepository.findAll()).thenReturn(cards);

        // when
        List<Card> result = cardsService.findAll();

        // then
        assertThat(result).isEqualTo(cards);
    }

    @Test
    @DisplayName("`findByIdUser` method should return cards for the user")
    void findByIdUser_shouldReturnCardsForUser() {
        // given
        int userId = 1;
        List<Card> cards = Arrays.asList(
                new Card(),
                new Card()
        );
        when(cardsRepository.findByIdUser(userId)).thenReturn(cards);

        // when
        List<Card> result = cardsService.findByIdUser(userId);

        // then
        assertThat(result).isEqualTo(cards);
    }

    @Test
    @DisplayName("`findOne` method should return a card when it exists")
    void findOne_shouldReturnCard_whenExists() {
        // given
        int id = 1;
        Card card = new Card();
        when(cardsRepository.findById(id)).thenReturn(Optional.of(card));

        // when
        Card result = cardsService.findOne(id);

        // then
        assertThat(result).isEqualTo(card);
    }

    @Test
    @DisplayName("`findOne` method should return `null` when the card does not exist")
    void findOne_shouldReturnNull_whenNotExists() {
        // given
        int id = 1;
        when(cardsRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Card result = cardsService.findOne(id);

        // then
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("`save` method should save a card")
    void save_shouldSaveCard() {
        // given
        Card card = new Card();

        // when
        cardsService.save(card);

        // then
        verify(cardsRepository).save(card);
    }

    @Test
    @DisplayName("`update` method should update a card")
    void update_shouldUpdateCard() {
        // given
        int id = 1;
        Card card = new Card();

        // when
        cardsService.update(id, card);

        // then
        verify(cardsRepository).save(card);
    }

    @Test
    @DisplayName("`delete` method should delete a card")
    void delete_shouldDeleteCard() {
        // given
        int id = 1;

        // when
        cardsService.delete(id);

        // then
        verify(cardsRepository).deleteById(id);
    }
}
