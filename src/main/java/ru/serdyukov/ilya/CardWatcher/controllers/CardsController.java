package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serdyukov.ilya.CardWatcher.services.CardsService;
import ru.serdyukov.ilya.CardWatcher.services.UsersService;
import ru.serdyukov.ilya.CardWatcher.models.Card;

import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardsController {


    private final CardsService cardsService;
    private final UsersService userService;

    @Autowired
    public CardsController(CardsService cardsService,UsersService userService) {
        this.cardsService = cardsService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String showListOfCards(Model model) {

        ru.serdyukov.ilya.CardWatcher.models.User user = this.getUser();

        List<Card> cardsList = cardsService.findByIdUser(user.getId());
        if (cardsList.isEmpty())
            model.addAttribute("no_card", true);

        model.addAttribute("cards", cardsList);
        model.addAttribute("user", user);
        return "cards/list-cards";
    }

    @GetMapping("/{id}")
    public String showCard(@PathVariable("id") int id, Model model) {
        model.addAttribute("card", cardsService.findOne(id));
        return "cards/update-cards";
    }

    @GetMapping("/create")
    public String createCard(@ModelAttribute("card") Card card,
                             @ModelAttribute("user") ru.serdyukov.ilya.CardWatcher.models.User user) {
        return "cards/create-card";
    }

    @PostMapping("/create")
    public String newCard(@ModelAttribute("card") Card card,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "cards/create-card";

        // here is the hardcode - still in work :D
        ru.serdyukov.ilya.CardWatcher.models.User user = this.getUser();
        card.setIdUser(user.getId());
        card.setIdBank(1);
        card.setIdCurrency(1);

        cardsService.save(card);
        return "redirect:/cards/";
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
        return "redirect:/cards/";
    }


    private ru.serdyukov.ilya.CardWatcher.models.User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User)authentication.getPrincipal();
        return userService.findByLogin(principal.getUsername());
    }

}

