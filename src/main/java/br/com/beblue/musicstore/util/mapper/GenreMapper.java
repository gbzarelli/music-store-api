package br.com.beblue.musicstore.util.mapper;

import br.com.beblue.musicstore.controller.dto.GenreDTO;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreMapper {

    public static GenreDTO genreEntityToGenreDTO(final GenreEntity genreEntity) {
        return new GenreDTO(genreEntity.getId(), genreEntity.getName());
    }
}
