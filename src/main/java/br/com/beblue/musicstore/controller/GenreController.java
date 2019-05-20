package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.dto.GenreDTO;
import br.com.beblue.musicstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {

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
