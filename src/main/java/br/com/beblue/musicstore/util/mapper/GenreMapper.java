package br.com.beblue.musicstore.util.mapper;

import br.com.beblue.musicstore.controller.dto.GenreDTO;
import br.com.beblue.musicstore.model.entity.GenreEntity;

public class GenreMapper {
    private GenreMapper() {
    }

    public static GenreDTO genreEntityToGenreDTO(GenreEntity genreEntity) {
        return new GenreDTO(genreEntity.getId(), genreEntity.getName());
    }
}
