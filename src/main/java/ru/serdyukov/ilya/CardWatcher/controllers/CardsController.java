package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serdyukov.ilya.CardWatcher.models.Card;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.CardsService;

@Controller
@RequestMapping("/cards")
public class CardsController {

    private final CardsService cardsService;

    @Autowired
    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @GetMapping("/list")
    public String showListOfCards(Model model) {
        model.addAttribute("cards", cardsService.findAll());
        return "cards/list";
    }

    @GetMapping("/{id}")
    public String showCard(@PathVariable("id") int id, Model model) {
        model.addAttribute("card", cardsService.findOne(id));
        return "cards/show";
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

    @GetMapping("/{id}/update")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("card", cardsService.findOne(id));
        return "cards/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("card") Card card, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "cards/update";
        }

        cardsService.update(id, card);
        return "redirect:/cards/list";
    }

    @DeleteMapping("/{id}")
    public String deleteCard(@PathVariable("id") int id) {
        cardsService.delete(id);
        return "redirect:/cards/list";
    }

}

