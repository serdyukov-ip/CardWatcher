package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/list")
    public String showListOfCards() {
        return "users/list";
    }

    @GetMapping("/create")
    public String createCard() {
        return "users/create";
    }

    @GetMapping("/update")
    public String updateCard() {
        return "users/update";
    }

    @GetMapping("/delete")
    public String deleteCard() {
        return "users/delete";
    }

}
