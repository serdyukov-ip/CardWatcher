package ru.serdyukov.ilya.CardWatcher.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/list")
    public String showListOfUsers(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "users/list";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersService.findOne(id));
        return "users/show";
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        return "users/create";
    }

    @PostMapping()
    public String newUser(@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "users/create";

        usersService.save(user);
        return "redirect:/users/list";
    }

    @GetMapping("/{id}/update")
    public String updateCard(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", usersService.findOne(id));
        return "users/update";
    }

    @PatchMapping("/{id}")
    public String updateCard(@ModelAttribute("user") User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {

        System.err.println("Запрос поступил в Controller");
        System.err.println("user id: " + user.getId() + " user name: " + user.getUserName() + " user login: " + user.getLogin());

        if (bindingResult.hasErrors()) {
            return "users/update";
        }

        usersService.update(id, user);
        return "redirect:/users/list";
    }

    @DeleteMapping("/{id}")
    public String deleteCard(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/users/list";
    }

}
