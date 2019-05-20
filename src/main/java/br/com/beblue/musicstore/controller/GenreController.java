package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.dto.GenreDTO;
import br.com.beblue.musicstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.beblue.musicstore.controller.GenreController.ROOT_PATH;

@RestController
@RequestMapping(ROOT_PATH)
public class GenreController {
    static final String ROOT_PATH = "/genre";

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDTO> getAll() {
        return genreService.getAllGenres();
    }

}
