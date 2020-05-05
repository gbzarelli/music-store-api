package br.com.helpdev.musicstore.controller;

import br.com.helpdev.musicstore.controller.dto.GenreDTO;

import java.util.List;

public interface GenreController {
    List<GenreDTO> getAllGenres();
}
