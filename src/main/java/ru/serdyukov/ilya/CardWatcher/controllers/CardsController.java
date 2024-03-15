package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cards")
public class CardsController {

    @GetMapping("/list")
    public String showListOfCards() {
        return "cards/list";
    }

    @GetMapping("/create")
    public String createCard() {
        return "cards/create";
    }

    @GetMapping("/update")
    public String updateCard() {
        return "cards/update";
    }

    @GetMapping("/delete")
    public String deleteCard() {
        return "cards/delete";
    }

}
