package br.com.beblue.musicstore.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpotifyAPI {

    private final Credentials credentials;

    public SpotifyAPI(@Value("${spotify.client.id}") String clientId,
                      @Value("${spotify.client.secret}") String clientSecret
    ) throws SpotifyWebApiException, IOException {
        this.credentials = Credentials.loadCredentials(clientId, clientSecret);
    }

    public void teste() {
//        try {
//            SearchAlbumsRequest request = credentials.spotifyApi.searchAlbums("Abba").build();
//            Paging<AlbumSimplified> execute = request.execute();
//            System.out.println(execute.toString());
//            System.out.println(execute.getItems().length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SpotifyWebApiException e) {
//            e.printStackTrace();
//        }
    }
}
