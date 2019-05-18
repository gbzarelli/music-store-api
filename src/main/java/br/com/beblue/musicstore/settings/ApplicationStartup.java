package br.com.beblue.musicstore.settings;

import br.com.beblue.musicstore.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final SpotifyService spotifyService;

    @Autowired
    public ApplicationStartup(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new Thread(() -> {
            try {
                spotifyService.loadMusics();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
