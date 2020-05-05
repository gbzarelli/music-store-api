package br.com.helpdev.musicstore.controller.rest;

import br.com.helpdev.musicstore.controller.GenreController;
import br.com.helpdev.musicstore.controller.dto.GenreDTO;
import br.com.helpdev.musicstore.service.GenreService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GenreRestController.ROOT_PATH)
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class GenreRestController implements GenreController {

    static final String ROOT_PATH = "/genre";

    private final GenreService genreService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

}
