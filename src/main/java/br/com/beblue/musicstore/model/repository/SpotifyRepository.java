package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.spotify.SpotifyAPI;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class SpotifyRepository {

    private final SpotifyAPI spotifyAPI;

    @Autowired
    public SpotifyRepository(SpotifyAPI spotifyAPI) {
        this.spotifyAPI = spotifyAPI;
    }

    public Paging<Track> findTrackByGenre(String genre) throws IOException, SpotifyWebApiException {
        return spotifyAPI.findTrackByGenre(genre);
    }
}
