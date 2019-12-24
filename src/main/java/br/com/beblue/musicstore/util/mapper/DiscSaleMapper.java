package br.com.beblue.musicstore.util.mapper;

import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.model.entity.DiscSaleEntity;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.beblue.musicstore.util.mapper.GenreMapper.genreEntityToGenreDTO;

public class DiscSaleMapper {
    private DiscSaleMapper() {
    }

    public static List<DiscDTO> discsSalesEntitiesToDiscsDTO(List<DiscSaleEntity> entities) {
        return entities.stream().map(DiscSaleMapper::discEntityToDiscDTO).collect(Collectors.toList());
    }

    public static DiscDTO discEntityToDiscDTO(DiscSaleEntity discEntity) {
        return new DiscDTO(discEntity.getDiscEntity().getId(), discEntity.getDiscEntity().getName(), discEntity.getDiscEntity().getArtist(),
                genreEntityToGenreDTO(discEntity.getDiscEntity().getGenreEntity()), discEntity.getPrice(), discEntity.getCashback());
    }
}
