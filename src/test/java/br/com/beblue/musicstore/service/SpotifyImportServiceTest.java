package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.exception.AlreadyImportedDiscsException;
import br.com.beblue.musicstore.exception.NoGenresException;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.model.repository.GenreRepository;
import br.com.beblue.musicstore.model.repository.SpotifyRepository;
import br.com.beblue.musicstore.settings.ApplicationStartup;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.com.beblue.musicstore.util.ResourceConst.ACTIVE_PROFILES_TEST_VALUE;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(ACTIVE_PROFILES_TEST_VALUE)
class SpotifyImportServiceTest {

    @MockBean
    ApplicationStartup applicationStartup;

    @MockBean
    GenreRepository genreRepository;

    @MockBean
    DiscRepository discRepository;

    @MockBean
    SpotifyRepository spotifyRepository;

    @Autowired
    SpotifyImportService service;

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
}
