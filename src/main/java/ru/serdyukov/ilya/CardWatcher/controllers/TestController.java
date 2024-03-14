package ru.serdyukov.ilya.CardWatcher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String sayHello() {
        return "hello";
    }

}
