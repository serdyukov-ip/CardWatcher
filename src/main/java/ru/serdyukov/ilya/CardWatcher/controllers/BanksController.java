package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.serdyukov.ilya.CardWatcher.services.BanksService;

@Controller
@RequestMapping("/banks")
public class BanksController {

    private final BanksService banksService;

    @Autowired
    public BanksController(BanksService banksService) {
        this.banksService = banksService;
    }

    @GetMapping("/")
    public void showListOfUsers(Model model) {
        model.addAttribute("banks", banksService.findAll());
    }

    @GetMapping("/{id}")
    public void showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("bank", banksService.findOne(id));
    }

}
