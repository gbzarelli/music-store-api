package br.com.beblue.musicstore.controller;

import br.com.beblue.musicstore.controller.dto.GenreDTO;

import java.util.List;

public interface GenreController {
    List<GenreDTO> getAllGenres();
}
