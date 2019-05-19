package br.com.beblue.musicstore.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class SpotifyAPI {

    private static final String QUERY_BY_GENRE = "genre: %s";
    private final Credentials credentials;

    public SpotifyAPI(@Value("${spotify.client.id}") String clientId,
                      @Value("${spotify.client.secret}") String clientSecret
    ) throws SpotifyWebApiException, IOException {
        this.credentials = Credentials.loadCredentials(clientId, clientSecret);
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
        SearchTracksRequest request = credentials.spotifyApi
                .searchTracks(String.format(QUERY_BY_GENRE, genre))
                .limit(50)
                .build();
        return request.execute();
    }
}
