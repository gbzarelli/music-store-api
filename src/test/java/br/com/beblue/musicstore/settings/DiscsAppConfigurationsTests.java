package br.com.beblue.musicstore.settings;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.model.repository.GenreRepository;
import br.com.beblue.musicstore.util.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.List;

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
        generateDiscsByGenres();
    }

    private void generateDiscsByGenres() {
        genreRepository.findAll().forEach(this::generateDiscsByGenre);
    }

    private void generateDiscsByGenre(GenreEntity genreEntity) {
        List<DiscEntity> entities = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            entities.add(new DiscEntity(
                    "Name " + genreEntity.getName() + " " + i,
                    "Artist " + genreEntity.getName() + " " + i,
                    "null",
                    genreEntity,
                    PriceUtil.generateRandomPriceDisc()
            ));
        }
        discRepository.saveAll(entities);
    }
}
