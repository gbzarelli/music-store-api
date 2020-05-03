package br.com.beblue.musicstore.model.repository.impl;

import br.com.beblue.musicstore.model.repository.SpotifyRepository;
import br.com.beblue.musicstore.spotify.SpotifyAPI;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
class SpotifyRepositoryImpl implements SpotifyRepository {

    private final SpotifyAPI spotifyAPI;

    @Autowired
    SpotifyRepositoryImpl(final SpotifyAPI spotifyAPI) {
        this.spotifyAPI = spotifyAPI;
    }

    public Paging<Track> findTrackByGenre(final String genre) throws IOException, SpotifyWebApiException {
        return spotifyAPI.findTrackByGenre(genre);
    }
}
