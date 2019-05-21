package br.com.beblue.musicstore.util.converter;

import br.com.beblue.musicstore.dto.GenreDTO;
import br.com.beblue.musicstore.model.entity.GenreEntity;

public class GenreConverter {
    public static GenreDTO genreEntityToGenreDTO(GenreEntity genreEntity) {
        return new GenreDTO(genreEntity.getId(), genreEntity.getName());
    }
}
