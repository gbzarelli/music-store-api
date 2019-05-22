package br.com.beblue.musicstore.util.converter;

import br.com.beblue.musicstore.dto.DiscDTO;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.DiscSaleEntity;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.beblue.musicstore.util.converter.GenreConverter.genreEntityToGenreDTO;

public class DiscSaleConverter {

    public static List<DiscDTO> discsSalesEntitiesToDiscsDTO(List<DiscSaleEntity> entities) {
        return entities.stream().map(DiscSaleConverter::discEntityToDiscDTO).collect(Collectors.toList());
    }

    public static DiscDTO discEntityToDiscDTO(DiscSaleEntity discEntity) {
        return new DiscDTO(discEntity.getDiscEntity().getId(), discEntity.getDiscEntity().getName(), discEntity.getDiscEntity().getArtist(),
                genreEntityToGenreDTO(discEntity.getDiscEntity().getGenreEntity()), discEntity.getPrice(), discEntity.getCashback());
    }
}
