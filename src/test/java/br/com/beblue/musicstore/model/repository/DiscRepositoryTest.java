package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static br.com.beblue.musicstore.util.DiscsGenerator.generateDiscsByGenres;
import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = GenreRepository.class)
@EntityScan(basePackageClasses = GenreEntity.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class DiscRepositoryTest {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    DiscRepository discRepository;

    @Test
    void test_findByIdIn() {
        generateDiscsByGenres(genreRepository, discRepository);
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        List<DiscEntity> byIdIn = discRepository.findByIdIn(ids);
        Assertions.assertEquals(byIdIn.size(), ids.size());
    }

}