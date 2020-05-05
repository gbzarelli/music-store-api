package br.com.helpdev.musicstore.util.mapper;

import br.com.helpdev.musicstore.controller.dto.GenreDTO;
import br.com.helpdev.musicstore.model.entity.GenreEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreMapper {

    public static GenreDTO genreEntityToGenreDTO(final GenreEntity genreEntity) {
        return new GenreDTO(genreEntity.getId(), genreEntity.getName());
    }
}
