package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscController {

    Page<DiscDTO> listDiscsByGenre(String genre, Pageable pageable);

    Page<DiscDTO> listDiscs(Pageable pageable);

    DiscDTO getDiscByID(Integer id) throws NoValuePresentException;

}
