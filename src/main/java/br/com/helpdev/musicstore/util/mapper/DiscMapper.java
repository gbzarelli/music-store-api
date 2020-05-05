package br.com.helpdev.musicstore.util.mapper;

import br.com.helpdev.musicstore.controller.dto.DiscDTO;
import br.com.helpdev.musicstore.model.entity.DiscEntity;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.com.helpdev.musicstore.util.mapper.GenreMapper.genreEntityToGenreDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscMapper {

    public static DiscEntity trackToDiscEntity(final Track track) {
        final var discEntity = new DiscEntity();
        discEntity.setName(track.getAlbum().getName());
        final var artists = Arrays.stream(track.getAlbum().getArtists())
                .map(ArtistSimplified::getName)
                .collect(Collectors.joining(", "));
        discEntity.setArtist(artists);
        discEntity.setSpotify_href(track.getAlbum().getHref());
        return discEntity;
    }

    public static Page<DiscDTO> pageEntityToPageDTO(final Page<DiscEntity> entities) {
        return entities.map(DiscMapper::discEntityToDiscDTO);
    }

    public static DiscDTO discEntityToDiscDTO(final DiscEntity discEntity) {
        return new DiscDTO(discEntity.getId(), discEntity.getName(), discEntity.getArtist(),
                genreEntityToGenreDTO(discEntity.getGenreEntity()), discEntity.getPrice(), 0);
    }
}
