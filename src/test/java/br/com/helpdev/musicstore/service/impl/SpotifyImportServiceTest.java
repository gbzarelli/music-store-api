package br.com.helpdev.musicstore.service.impl;

import br.com.helpdev.musicstore.exception.AlreadyImportedDiscsException;
import br.com.helpdev.musicstore.exception.NoGenresException;
import br.com.helpdev.musicstore.model.entity.GenreEntity;
import br.com.helpdev.musicstore.model.repository.DiscRepository;
import br.com.helpdev.musicstore.model.repository.GenreRepository;
import br.com.helpdev.musicstore.model.repository.SpotifyRepository;
import br.com.helpdev.musicstore.settings.DefaultAppConfigurationsTests;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.com.helpdev.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DefaultAppConfigurationsTests.class)
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class SpotifyImportServiceTest {

    @MockBean
    GenreRepository genreRepository;

    @MockBean
    DiscRepository discRepository;

    @MockBean
    SpotifyRepository spotifyRepository;

    @Autowired
    SpotifyImportServiceImpl service;

    @Test
    void test_import_discs_with_already_imported_data() {
        when(discRepository.count()).thenReturn(200L);
        service.importDiscsByGenres(error -> Assertions.assertThrows(AlreadyImportedDiscsException.class, () -> {
            throw error;
        }));
    }

    @Test
    void test_import_discs_with_no_genres_data() {
        when(discRepository.count()).thenReturn(0L);
        when(genreRepository.count()).thenReturn(0L);
        service.importDiscsByGenres(error -> Assertions.assertThrows(NoGenresException.class, () -> {
            throw error;
        }));
    }


    @Test
    void x() throws IOException, SpotifyWebApiException {
        when(discRepository.count()).thenReturn(0L);
        when(genreRepository.count()).thenReturn(1L);
        when(genreRepository.findAll()).thenReturn(getGenresTest());
        when(spotifyRepository.findTrackByGenre(any())).thenThrow(new SpotifyWebApiException("ex"));
        service.importDiscsByGenres(error -> Assertions.assertThrows(SpotifyWebApiException.class, () -> {
            throw error.getCause();
        }));
    }

    private Iterable<GenreEntity> getGenresTest() {
        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1);
        genreEntityList.add(genreEntity);
        return genreEntityList;
    }
}
