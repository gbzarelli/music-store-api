package br.com.beblue.musicstore.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

import static br.com.beblue.musicstore.util.ResourceConst.KEY_SPOTIFY_CLIENT_ID;
import static br.com.beblue.musicstore.util.ResourceConst.KEY_SPOTIFY_CLIENT_SECRET;

@Component
public class SpotifyAPI {

    private static final String QUERY_BY_GENRE = "genre: %s";
    private final String clientId;
    private final String clientSecret;
    private Credentials credentials;

    SpotifyAPI(@Value(KEY_SPOTIFY_CLIENT_ID) final String clientId,
               @Value(KEY_SPOTIFY_CLIENT_SECRET) final String clientSecret
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Foi utilizado o findTrack em vez do findAlbum pois a API do Spotify não aceita o
     * `findAlbum` com o filtro de genero. Segue a nota da documentação:
     * <p>
     * Depending on object types being searched for, other field filters, include genre
     * (applicable to tracks and artists), upc, and isrc.
     * For example: q=lil%20genre:%22southern%20hip%20hop%22&type=artist.
     * Use double quotation marks around the genre keyword string if it contains spaces.
     * (https://developer.spotify.com/documentation/web-api/reference/search/search/)
     */
    public Paging<Track> findTrackByGenre(String genre) throws IOException, SpotifyWebApiException {
        Objects.requireNonNull(genre);
        final var request = getCredentials().spotifyApi
                .searchTracks(String.format(QUERY_BY_GENRE, genre))
                .limit(50)
                .build();
        return request.execute();
    }

    private Credentials getCredentials() throws IOException, SpotifyWebApiException {
        if (credentials == null) this.credentials = Credentials.loadCredentials(clientId, clientSecret);
        return credentials;
    }
}
