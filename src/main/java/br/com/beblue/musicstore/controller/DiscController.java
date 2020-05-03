package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscController {

    Page<DiscDTO> listDiscsByGenre(final String genre, final Pageable pageable);

    Page<DiscDTO> listDiscs(final Pageable pageable);

    DiscDTO getDiscByID(final Integer id) throws NoValuePresentException;

}
