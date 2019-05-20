package br.com.beblue.musicstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.beblue.musicstore.controller.HomeController.ROOT_PATH;

@RestController
@RequestMapping(ROOT_PATH)
public class HomeController {
    static final String ROOT_PATH = "/";
    public static final String HOME_MESSAGE = "Welcome to the Music Store API";

    @GetMapping
    public String index() {
        return getIndexMessage();
    }

    private String getIndexMessage() {
        return HOME_MESSAGE;
    }

}
