package br.com.helpdev.musicstore.model.repository;

import br.com.helpdev.musicstore.model.entity.GenreEntity;
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
class DiscRepositoryTest {

}