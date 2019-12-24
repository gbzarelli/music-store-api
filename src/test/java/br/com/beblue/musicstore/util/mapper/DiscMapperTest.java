package br.com.beblue.musicstore.util.mapper;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DiscMapperTest {

    @Test
    void trackToDiscEntity() {
        Track track = getCustomTrack();
        DiscEntity discEntity = DiscMapper.trackToDiscEntity(track);
        assertEquals(discEntity.getName(), track.getAlbum().getName());
        String artists = convertArtistsToString(track);
        assertEquals(discEntity.getArtist(), artists);
        assertEquals(discEntity.getSpotify_href(), track.getAlbum().getHref());
    }

    private String convertArtistsToString(Track track) {
        return Arrays.stream(track.getAlbum().getArtists())
                .map(ArtistSimplified::getName)
                .collect(Collectors.joining(", "));
    }

    private Track getCustomTrack() {
        return new Track.Builder().setAlbum(new AlbumSimplified.Builder()
                .setName("Album")
                .setArtists(
                        new ArtistSimplified.Builder().setName("Artist1").build(),
                        new ArtistSimplified.Builder().setName("Artist2").build()
                )
                .setHref("href_link")
                .build()
        ).build();
    }
}