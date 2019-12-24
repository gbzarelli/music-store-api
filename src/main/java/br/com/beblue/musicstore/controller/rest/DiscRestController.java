package br.com.beblue.musicstore.controller.rest;

import br.com.beblue.musicstore.controller.DiscController;
import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import br.com.beblue.musicstore.service.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(DiscRestController.ROOT_PATH)
class DiscRestController implements DiscController {

    static final String ROOT_PATH = "/disc";
    static final String PATH_BY_GENRE = "/genre/{genre}";
    static final String PATH_BY_ID = "/{id}";

    private final DiscService discService;

    @Autowired
    DiscRestController(final DiscService discService) {
        this.discService = discService;
    }

    @GetMapping(value = PATH_BY_GENRE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<DiscDTO> listDiscsByGenre(@PathVariable String genre, Pageable pageable) {
        return discService.getDiscsByGenre(genre, pageable);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<DiscDTO> listDiscs(Pageable pageable) {
        return discService.getDiscs(pageable);
    }

    @GetMapping(value = PATH_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public DiscDTO getDiscByID(@PathVariable Integer id) throws NoValuePresentException {
        return discService.getDiscByID(id);
    }

}
