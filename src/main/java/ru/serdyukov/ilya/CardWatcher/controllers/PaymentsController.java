package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.serdyukov.ilya.CardWatcher.models.Payment;
import ru.serdyukov.ilya.CardWatcher.models.User;
import ru.serdyukov.ilya.CardWatcher.services.PaymentsService;

import java.util.List;

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

        Payment payment = new Payment();
        payment.setCreditCardId(id);
        model.addAttribute("payment", payment);

        List<Payment> paymentList = paymentsService.findOneByCreditCardId(id);
        if (paymentList.isEmpty()) model.addAttribute("no_payment", true);

        model.addAttribute("payments", paymentList);

        return "payments/list-payments";
    }

    @GetMapping("/show/{id}")
    public String shPayment(@PathVariable("id") int id, Model model) {
        model.addAttribute("payment", paymentsService.findOne(id));
        return "payments/update-payments";
    }


    @GetMapping("/create/{id}")
    public String createPayment(@PathVariable("id") int cardId, @ModelAttribute("payment") Payment payment, Model model) {

        payment.setCreditCardId(cardId);
        payment.setPaymentStatusId(2);
        model.addAttribute("payment", payment);

        return "payments/create-payments";
    }


    @PostMapping("create/")
    public String newPayment(@ModelAttribute("payment") Payment payment, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "payments/create-payments";

        paymentsService.save(payment);
        return "redirect:/payments/" + payment.getCreditCardId();
    }

    @PatchMapping("/{id}")
    public String updatePayment(@ModelAttribute("payment") Payment payment, BindingResult bindingResult,
                             @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "payments/" + id;
        }

        Payment paymentOld = paymentsService.findOne(id);
        paymentsService.update(id, payment);
        return "redirect:/payments/" + paymentOld.getCreditCardId();
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable("id") int id) {
        Payment payment = paymentsService.findOne(id);
        paymentsService.delete(id);
        return "redirect:/payments/" + payment.getCreditCardId();
    }

}
