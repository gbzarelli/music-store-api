package br.com.beblue.musicstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String index() {
        return getIndexMessage();
    }

    private String getIndexMessage() {
        return "Welcome to the Music Store API";
    }

}
