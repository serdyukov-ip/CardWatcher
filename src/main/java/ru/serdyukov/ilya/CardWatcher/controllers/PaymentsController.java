package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serdyukov.ilya.CardWatcher.models.Payment;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.PaymentsService;

@Controller
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/")
    public String showListOfPayments(Model model) {
        model.addAttribute("payments", paymentsService.findAll());
        return "payments/list-payments";
    }

    @GetMapping("/{id}")
    public String showPayment(@PathVariable("id") int id, Model model) {
        model.addAttribute("payment", paymentsService.findOne(id));
        return "payments/show";
    }

    @GetMapping("/create")
    public String createPayment(@ModelAttribute("payment") Payment payment) {
        return "payments/create";
    }


    @PostMapping()
    public String newPayment(@ModelAttribute("payment") Payment payment, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "payments/create";

        paymentsService.save(payment);
        return "redirect:/payments/list";
    }

    @GetMapping("/{id}/update")
    public String updatePayment(Model model, @PathVariable("id") int id) {
        model.addAttribute("payment", paymentsService.findOne(id));
        return "payments/update";
    }

    @PatchMapping("/{id}")
    public String updatePayment(@ModelAttribute("payment") Payment payment, BindingResult bindingResult,
                             @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "payments/update";
        }

        paymentsService.update(id, payment);
        return "redirect:/payments/list";
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable("id") int id) {
        paymentsService.delete(id);
        return "redirect:/payments/list";
    }

}
