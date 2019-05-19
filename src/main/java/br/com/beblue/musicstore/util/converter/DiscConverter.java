package br.com.beblue.musicstore.util.converter;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DiscConverter {
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
}
