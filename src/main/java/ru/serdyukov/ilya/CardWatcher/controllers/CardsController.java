package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serdyukov.ilya.CardWatcher.models.Bank;
import ru.serdyukov.ilya.CardWatcher.models.Card;
import ru.serdyukov.ilya.CardWatcher.models.Currency;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.*;

@Controller
@RequestMapping("/cards")
public class CardsController {

    private final BanksService banksService;
    private final CardsService cardsService;
    private final CurrenciesService currenciesService;
    private final PaymentsService paymentsService;
    private final PaymentsStatusService paymentsStatusService;
    private final UsersService userService;

    @Autowired
    public CardsController(CardsService cardsService, UsersService userService, BanksService banksService,
                           CurrenciesService currenciesService, PaymentsService paymentsService,
                           PaymentsStatusService paymentsStatusService) {
        this.cardsService = cardsService;
        this.userService = userService;
        this.banksService = banksService;
        this.currenciesService = currenciesService;
        this.paymentsService = paymentsService;
        this.paymentsStatusService = paymentsStatusService;
    }

    @GetMapping("/")
    public String showListOfCards(Model model) {
        model.addAttribute("cards", cardsService.findAll());
        return "cards/list-cards";
    }

    @GetMapping("/{id}")
    public String showCard(@PathVariable("id") int id, Model model) {
        model.addAttribute("card", cardsService.findOne(id));
        return "cards/update-cards";
    }

    @GetMapping("/create")
    public String createCard(@ModelAttribute("card") Card card) {
        return "cards/create";
    }

    @PostMapping()
    public String newCard(@ModelAttribute("user") Card card, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "cards/create";

        cardsService.save(card);
        return "redirect:/cards/list";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("card") Card card, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "cards/update-cards";
        }

        cardsService.update(id, card);
        return "redirect:/cards/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteCard(@PathVariable("id") int id) {
        cardsService.delete(id);
        return "redirect:/cards";
    }

}

