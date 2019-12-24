package br.com.beblue.musicstore.settings;

import br.com.beblue.musicstore.exception.ImportedException;
import br.com.beblue.musicstore.service.SpotifyImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class.getName());

    private final SpotifyImportService spotifyService;

    @Autowired
    ApplicationStartup(SpotifyImportService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        long time = System.currentTimeMillis();
        spotifyService.importDiscsByGenres(ImportedException::printStackTrace);
        LOGGER.info("Import time: " + TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - time)) + "seg");
        LOGGER.info("----Application started----");
    }
}
