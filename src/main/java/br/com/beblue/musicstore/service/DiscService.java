package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.exception.NoValuePresentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscService {
    Page<DiscDTO> getDiscsByGenre(String genre, Pageable pageable);

    Page<DiscDTO> getDiscs(Pageable pageable);

    DiscDTO getDiscByID(Integer id) throws NoValuePresentException;
}
