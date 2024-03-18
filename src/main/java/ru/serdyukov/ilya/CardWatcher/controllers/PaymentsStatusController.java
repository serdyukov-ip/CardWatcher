package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.serdyukov.ilya.CardWatcher.services.PaymentsService;

@Controller
@RequestMapping("/payments_status")
public class PaymentsStatusController {

    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsStatusController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/")
    public void showListOfUsers(Model model) {
        model.addAttribute("payments", paymentsService.findAll());
    }

    @GetMapping("/{id}")
    public void showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("payment", paymentsService.findOne(id));
    }


}
