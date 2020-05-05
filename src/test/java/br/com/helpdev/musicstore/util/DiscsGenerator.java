package br.com.helpdev.musicstore.util;

import br.com.helpdev.musicstore.model.entity.DiscEntity;
import br.com.helpdev.musicstore.model.entity.GenreEntity;
import br.com.helpdev.musicstore.model.repository.DiscRepository;
import br.com.helpdev.musicstore.model.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscsGenerator {

    public static void generateDiscsByGenres(GenreRepository genreRepository, DiscRepository discRepository) {
        genreRepository.findAll().forEach(item -> generateDiscsByGenre(discRepository, item));
    }

    private static void generateDiscsByGenre(DiscRepository discRepository, GenreEntity genreEntity) {
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
