package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.repositories.UsersRepository;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsService(usersRepository);
    }

    @Test
    @DisplayName("loading a user by username should return the user's details if the user exists")
    void loadUserByUsername_userExists_returnsUserDetails() {
        String login = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        when(usersRepository.findByLogin(login)).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        assertThat(userDetails.getUsername()).isEqualTo(login);
        assertThat(userDetails.getPassword()).isEqualTo(password);
        assertThat(userDetails.getAuthorities()).hasSize(1);
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("loading a user by username should throw a `username not found` exception if the user does not exist")
    void loadUserByUsername_userDoesNotExist_throwsUsernameNotFoundException() {
        String login = "nonExistentUser";
        when(usersRepository.findByLogin(login)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(login))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User not found!");
    }
}