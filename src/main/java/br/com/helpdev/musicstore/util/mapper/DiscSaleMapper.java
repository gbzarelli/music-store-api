package br.com.helpdev.musicstore.util.mapper;

import br.com.helpdev.musicstore.controller.dto.DiscDTO;
import br.com.helpdev.musicstore.model.entity.DiscSaleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.helpdev.musicstore.util.mapper.GenreMapper.genreEntityToGenreDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscSaleMapper {

    public static List<DiscDTO> discsSalesEntitiesToDiscsDTO(final List<DiscSaleEntity> entities) {
        return entities.stream().map(DiscSaleMapper::discEntityToDiscDTO).collect(Collectors.toList());
    }

    public static DiscDTO discEntityToDiscDTO(final DiscSaleEntity discEntity) {
        return new DiscDTO(discEntity.getDiscEntity().getId(), discEntity.getDiscEntity().getName(), discEntity.getDiscEntity().getArtist(),
                genreEntityToGenreDTO(discEntity.getDiscEntity().getGenreEntity()), discEntity.getPrice(), discEntity.getCashback());
    }
}
