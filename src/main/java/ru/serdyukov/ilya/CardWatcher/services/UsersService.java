package ru.serdyukov.ilya.CardWatcher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> findCard = usersRepository.findById(id);
        return findCard.orElse(null);
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    public void update(int id, User user) {
        user.setId(id);
        System.err.println("Запрос поступил в UserService");
        System.err.println("User id: " + user.getId() + " User name: " + user.getUserName() + " User login: " + user.getLogin());
        usersRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

}
