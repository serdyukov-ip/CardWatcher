package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentsController {

    @GetMapping("/list")
    public String showListOfCards() {
        return "payments/list";
    }

    @GetMapping("/create")
    public String createCard() {
        return "payments/create";
    }

    @GetMapping("/update")
    public String updateCard() {
        return "payments/update";
    }

    @GetMapping("/delete")
    public String deleteCard() {
        return "payments/delete";
    }
}
