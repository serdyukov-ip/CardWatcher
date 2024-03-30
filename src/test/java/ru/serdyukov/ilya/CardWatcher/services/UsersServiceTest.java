package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.repositories.UsersRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    @Test
    @DisplayName("`findAll` method should return all users")
    void findAll_shouldReturnAllUsers() {
        // given
        List<User> users = Arrays.asList(
                new User(),
                new User()
        );
        when(usersRepository.findAll()).thenReturn(users);

        // when
        List<User> result = usersService.findAll();

        // then
        assertThat(result).isEqualTo(users);
    }

    @Test
    @DisplayName("`findOne` method should return a user when it exists")
    void findOne_shouldReturnUser_whenExists() {
        int id = 1;
        User user = new User();
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));

        User result = usersService.findOne(id);

        assertThat(result).isEqualTo(user);
    }

    @Test
    @DisplayName("`findOne` method should return `null` when the user does not exist")
    void findOne_shouldReturnNull_whenNotExists() {
        int id = 1;
        when(usersRepository.findById(id)).thenReturn(Optional.empty());

        User result = usersService.findOne(id);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("`findByLogin` method should return a user when it exists")
    void findByLogin_shouldReturnUser_whenExists() {
        String login = "testLogin";
        User user = new User();
        when(usersRepository.findByLogin(login)).thenReturn(Optional.of(user));

        User result = usersService.findByLogin(login);

        assertThat(result).isEqualTo(user);
    }

    @Test
    @DisplayName("`findByLogin` method should return `null` when the user does not exist")
    void findByLogin_shouldReturnNull_whenNotExists() {
        String login = "testLogin";
        when(usersRepository.findByLogin(login)).thenReturn(Optional.empty());

        User result = usersService.findByLogin(login);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("`save` method should save a user")
    void save_shouldSaveUser() {
        User user = new User();

        usersService.save(user);

        verify(usersRepository).save(user);
    }

    @Test
    @DisplayName("`update` method should update a user")
    void update_shouldUpdateUser() {
        int id = 1;
        User user = new User();
        User oldUser = new User();
        when(usersRepository.findById(id)).thenReturn(Optional.of(oldUser));

        usersService.update(id, user);

        verify(usersRepository).save(user);
    }

    @Test
    @DisplayName("`delete` method should delete a user")
    void delete_shouldDeleteUser() {
        int id = 1;

        usersService.delete(id);

        verify(usersRepository).deleteById(id);
    }
}