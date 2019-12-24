package br.com.beblue.musicstore.util.mapper;

import br.com.beblue.musicstore.controller.dto.DiscDTO;
import br.com.beblue.musicstore.model.entity.DiscEntity;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.com.beblue.musicstore.util.mapper.GenreMapper.*;

public class DiscMapper {

    private DiscMapper() {
    }

    public static DiscEntity trackToDiscEntity(Track track) {
        DiscEntity discEntity = new DiscEntity();
        discEntity.setName(track.getAlbum().getName());
        String artists = Arrays.stream(track.getAlbum().getArtists())
                .map(ArtistSimplified::getName)
                .collect(Collectors.joining(", "));
        discEntity.setArtist(artists);
        discEntity.setSpotify_href(track.getAlbum().getHref());
        return discEntity;
    }

    public static Page<DiscDTO> pageEntityToPageDTO(Page<DiscEntity> entities) {
        return entities.map(DiscMapper::discEntityToDiscDTO);
    }

    public static DiscDTO discEntityToDiscDTO(DiscEntity discEntity) {
        return new DiscDTO(discEntity.getId(), discEntity.getName(), discEntity.getArtist(),
                genreEntityToGenreDTO(discEntity.getGenreEntity()), discEntity.getPrice(), 0);
    }
}
