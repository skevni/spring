package ru.gb.sklyarov.shop.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class MainController {

    @GetMapping
    public String getStartPage() {
        return "index.html";
    }
}
