package br.com.beblue.musicstore.settings;

import br.com.beblue.musicstore.service.SpotifyImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final SpotifyImportService spotifyService;

    @Autowired
    public ApplicationStartup(SpotifyImportService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new Thread(() -> {
            try {
                spotifyService.importDisksByGenres();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
