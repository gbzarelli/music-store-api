package br.com.helpdev.musicstore.service.impl;

import br.com.helpdev.musicstore.exception.AlreadyImportedDiscsException;
import br.com.helpdev.musicstore.exception.ImportedException;
import br.com.helpdev.musicstore.exception.NoGenresException;
import br.com.helpdev.musicstore.model.entity.DiscEntity;
import br.com.helpdev.musicstore.model.entity.GenreEntity;
import br.com.helpdev.musicstore.model.repository.DiscRepository;
import br.com.helpdev.musicstore.model.repository.GenreRepository;
import br.com.helpdev.musicstore.model.repository.SpotifyRepository;
import br.com.helpdev.musicstore.service.SpotifyImportService;
import br.com.helpdev.musicstore.util.PriceUtil;
import br.com.helpdev.musicstore.util.mapper.DiscMapper;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class SpotifyImportServiceImpl implements SpotifyImportService {

    private final GenreRepository genreRepository;
    private final DiscRepository discRepository;
    private final SpotifyRepository spotifyRepository;

    public void importDiscsByGenres(final ImportErrorCallback importErrorCallback) {
        if (checkAlreadyImported(importErrorCallback)) return;
        try {
            executeImportDiscs(importErrorCallback);
        } catch (NoGenresException e) {
            importErrorCallback.error(e);
        }
    }

    private boolean checkAlreadyImported(final ImportErrorCallback importErrorCallback) {
        // Poderia ser realizado uma consistencia mais adequada para
        // verificar se os discos já foram importados.
        if (discRepository.count() > 0) {
            importErrorCallback.error(new AlreadyImportedDiscsException());
            return true;
        }
        return false;
    }

    private void executeImportDiscs(final ImportErrorCallback importErrorCallback)
            throws NoGenresException {
        final var executorService = createExecutorToImportDiscs();
        genreRepository.findAll().forEach(genre -> executorService.submit(() -> {
            try {
                importGenre(genre);
            } catch (IOException | SpotifyWebApiException e) {
                importErrorCallback.error(new ImportedException(e));
            }
        }));
        executorService.shutdown();
        awaitImport(importErrorCallback, executorService);
    }

    private ExecutorService createExecutorToImportDiscs()
            throws NoGenresException {
        final var value = Long.valueOf(genreRepository.count()).intValue();
        if (value > 0) {
            return Executors.newFixedThreadPool(value);
        } else {
            throw new NoGenresException();
        }
    }

    private void importGenre(final GenreEntity genre)
            throws IOException, SpotifyWebApiException {
        getFirstsItemsByGenre(genre).parallelStream().forEach(track -> saveDisc(track, genre));
    }

    private List<Track> getFirstsItemsByGenre(final GenreEntity genre)
            throws IOException, SpotifyWebApiException {
        return Arrays.asList(spotifyRepository.findTrackByGenre(genre.getName()).getItems());
    }

    private void saveDisc(final Track track,
                          final GenreEntity genreEntity) {
        final var discEntity = convertToDisc(track, genreEntity);
        discRepository.save(discEntity);
    }

    private DiscEntity convertToDisc(final Track track,
                                     final GenreEntity genreEntity) {
        final var discEntity = DiscMapper.trackToDiscEntity(track);
        discEntity.setGenreEntity(genreEntity);
        discEntity.setPrice(PriceUtil.generateRandomPriceDisc());
        return discEntity;
    }

    private void awaitImport(final ImportErrorCallback importErrorCallback,
                             final ExecutorService executorService) {
        try {
            executorService.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException in) {
            importErrorCallback.error(new ImportedException(in));
        }
    }
}