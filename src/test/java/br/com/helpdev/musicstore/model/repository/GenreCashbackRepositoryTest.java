package br.com.helpdev.musicstore.model.repository;

import br.com.helpdev.musicstore.model.entity.GenreCashbackEntity;
import br.com.helpdev.musicstore.model.entity.GenreEntity;
import br.com.helpdev.musicstore.util.GenreCashbackUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import static br.com.helpdev.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = GenreRepository.class)
@EntityScan(basePackageClasses = GenreEntity.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class GenreCashbackRepositoryTest {

    @Autowired
    GenreCashbackRepository repository;

    @Test
    void test_cashback_map_database() {
        GenreCashbackUtils.genreCashbackMaps.forEach(test -> {
            GenreCashbackEntity entity = repository.findByGenreEntityIdAndWeekdayAndEnableTrue(test.genreId, test.weekday);
            Assertions.assertEquals(entity.getGenreEntity().getId(), test.genreId);
            Assertions.assertEquals(entity.getWeekday(), test.weekday);
            Assertions.assertEquals(entity.getCashback(), test.cashback);
        });

    }
}