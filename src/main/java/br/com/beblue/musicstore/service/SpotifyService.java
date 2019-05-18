package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.model.repository.SpotifyRepository;
import br.com.beblue.musicstore.spotify.SpotifyAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    private final DiscRepository discRepository;
    private final SpotifyRepository spotifyRepository;

    @Autowired
    public SpotifyService(DiscRepository discRepository, SpotifyRepository spotifyRepository) {
        this.discRepository = discRepository;
        this.spotifyRepository = spotifyRepository;
    }

    public void loadMusics() {
        System.out.println("Loading musics...");
        spotifyRepository.findAlbum("Abba");
    }
}
