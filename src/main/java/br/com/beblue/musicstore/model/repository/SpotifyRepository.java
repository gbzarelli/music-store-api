package br.com.beblue.musicstore.model.repository;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;

import java.io.IOException;

public interface SpotifyRepository {
    Paging<Track> findTrackByGenre(String genre) throws IOException, SpotifyWebApiException;
}
