package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.spotify.SpotifyAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {

    private final SpotifyAPI spotifyAPI;

    @Autowired
    public SpotifyRepository(SpotifyAPI spotifyAPI) {
        this.spotifyAPI = spotifyAPI;
    }

    public void findAlbum(String abba) {
        spotifyAPI.teste();
    }
}
