package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.controller.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenres();
}
