package br.com.helpdev.musicstore.settings;

import br.com.helpdev.musicstore.model.repository.DiscRepository;
import br.com.helpdev.musicstore.model.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationListener;

import static br.com.helpdev.musicstore.util.DiscsGenerator.generateDiscsByGenres;

@TestConfiguration
public class DiscsAppConfigurationsTests implements ApplicationListener<ApplicationReadyEvent> {

    @MockBean
    ApplicationStartup applicationStartup;

    @Autowired
    DiscRepository discRepository;

    @Autowired
    GenreRepository genreRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        generateDiscsByGenres(genreRepository, discRepository);
    }

}
