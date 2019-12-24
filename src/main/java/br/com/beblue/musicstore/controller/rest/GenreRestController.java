package br.com.beblue.musicstore.controller.rest;

import br.com.beblue.musicstore.controller.GenreController;
import br.com.beblue.musicstore.controller.dto.GenreDTO;
import br.com.beblue.musicstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GenreRestController.ROOT_PATH)
class GenreRestController implements GenreController {

    static final String ROOT_PATH = "/genre";

    private final GenreService genreService;

    @Autowired
    GenreRestController(final GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

}
