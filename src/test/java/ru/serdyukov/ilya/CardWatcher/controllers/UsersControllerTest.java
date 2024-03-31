package ru.serdyukov.ilya.CardWatcher.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.UsersService;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UsersController usersController;

    @Test
    public void testShowListOfUsers() {
        //when(usersService.findAll()).thenReturn(/* some mock data */);

        String viewName = usersController.showListOfUsers(model);

        verify(model).addAttribute("users", usersService.findAll());
        assertThat(viewName).isEqualTo("users/list");
    }

    @Test
    public void testShowUser() {
        int id = 1;
        //when(usersService.findOne(id)).thenReturn(/* some mock data */);

        String viewName = usersController.showUser(id, model);

        verify(model).addAttribute("user", usersService.findOne(id));
        assertThat(viewName).isEqualTo("users/show");
    }

    @Test
    public void testCreateUser() {
        User user = new User();

        String viewName = usersController.createUser(user);

        assertThat(viewName).isEqualTo("users/create");
    }

    @Test
    public void testNewUserWithErrors() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = usersController.newUser(user, bindingResult);

        verify(usersService, never()).save(any(User.class));
        assertThat(viewName).isEqualTo("users/create");
    }

    @Test
    public void testNewUserWithoutErrors() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = usersController.newUser(user, bindingResult);

        verify(usersService).save(user);
        assertThat(viewName).isEqualTo("redirect:/users/list");
    }

    @Test
    public void testUpdateUser() {
        int id = 1;
        //when(usersService.findOne(id)).thenReturn(/* some mock data */);

        String viewName = usersController.updateUser(model, id);

        verify(model).addAttribute("user", usersService.findOne(id));
        assertThat(viewName).isEqualTo("users/update");
    }

    @Test
    public void testUpdateWithErrors() {
        User user = new User();
        int id = 1;
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = usersController.update(user, bindingResult, id);

        verify(usersService, never()).update(anyInt(), any(User.class));
        assertThat(viewName).isEqualTo("users/update");
    }

    @Test
    public void testUpdateWithoutErrors() {
        User user = new User();
        int id = 1;
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = usersController.update(user, bindingResult, id);

        verify(usersService).update(id, user);
        assertThat(viewName).isEqualTo("redirect:/users/list");
    }

    @Test
    public void testDeleteCard() {
        int id = 1;

        String viewName = usersController.deleteCard(id);

        verify(usersService).delete(id);
        assertThat(viewName).isEqualTo("redirect:/users/list");
    }
}