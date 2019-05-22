package br.com.beblue.musicstore.model.repository;

import br.com.beblue.musicstore.model.entity.GenreEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static br.com.beblue.musicstore.util.DataUtil.DEFAULT_GENRES;
import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = GenreRepository.class)
@EntityScan(basePackageClasses = GenreEntity.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class GenreRepositoryTest {

}