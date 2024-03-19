package ru.serdyukov.ilya.CardWatcher.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DuplicatesPredicate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.UsersService;

@Component
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User userFromForm = (User) target;
        User userFromDB = usersService.findByLogin(userFromForm.getLogin());
        if (userFromDB != null)
            errors.rejectValue("username", "", "This user already exists!");
    }
}
