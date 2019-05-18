package br.com.beblue.musicstore.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

class Credentials {

    private final String clientId;
    private final String clientSecret;

    SpotifyApi spotifyApi;
    ClientCredentials clientCredentials;

    private Credentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    static Credentials loadCredentials(String clientId, String clientSecret) throws SpotifyWebApiException, IOException {
        Credentials credentials = new Credentials(clientId, clientSecret);
        credentials.spotifyApi = credentials.getSpotifyApi();
        credentials.clientCredentials = credentials.getClientCredentialsRequest().execute();
        credentials.spotifyApi.setAccessToken(credentials.clientCredentials.getAccessToken());
        System.out.println("Expires in: " + credentials.clientCredentials.getExpiresIn());
        return credentials;
    }


    private SpotifyApi getSpotifyApi() {
        return new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }

    private ClientCredentialsRequest getClientCredentialsRequest() {
        return spotifyApi.clientCredentials().build();
    }

}
