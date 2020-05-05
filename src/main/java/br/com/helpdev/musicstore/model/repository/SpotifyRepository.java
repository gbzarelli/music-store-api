package br.com.helpdev.musicstore.model.repository;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;

import java.io.IOException;

public interface SpotifyRepository {
    Paging<Track> findTrackByGenre(final String genre) throws IOException, SpotifyWebApiException;
}
