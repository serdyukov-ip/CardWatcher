package ru.serdyukov.ilya.CardWatcher.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.repositories.UsersRepository;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void  ifCompareEncodedPasswordsTheyShouldBeEqual() {

        User user = new User();
        user.setPassword("testPassword1234");

        when(passwordEncoder.encode(anyString())).thenReturn("testPassword1234");

        registrationService.register(user);

        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(usersRepository, times(1)).save(user);

    }

}
