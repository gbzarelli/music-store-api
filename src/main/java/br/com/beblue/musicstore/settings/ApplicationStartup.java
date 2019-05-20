package br.com.beblue.musicstore.settings;

import br.com.beblue.musicstore.exception.ImportedException;
import br.com.beblue.musicstore.service.SpotifyImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final SpotifyImportService spotifyService;

    @Autowired
    public ApplicationStartup(SpotifyImportService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        long time = System.currentTimeMillis();
        spotifyService.importDiscsByGenres(ImportedException::printStackTrace);
        System.out.println("Time to import: " + TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - time)) + "seg");
    }
}
