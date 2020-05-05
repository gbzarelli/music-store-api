package br.com.helpdev.musicstore.service;

import br.com.helpdev.musicstore.controller.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenres();
}
