package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.model.entity.DiscEntity;
import br.com.beblue.musicstore.model.entity.GenreEntity;
import br.com.beblue.musicstore.model.repository.DiscRepository;
import br.com.beblue.musicstore.model.repository.GenreRepository;
import br.com.beblue.musicstore.model.repository.SpotifyRepository;
import br.com.beblue.musicstore.util.PriceUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static br.com.beblue.musicstore.util.converter.DiscConverter.trackToDiscEntity;

@Service
public class SpotifyImportService {

    private final GenreRepository genreRepository;
    private final DiscRepository discRepository;
    private final SpotifyRepository spotifyRepository;

    @Autowired
    public SpotifyImportService(DiscRepository discRepository,
                                SpotifyRepository spotifyRepository,
                                GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.discRepository = discRepository;
        this.spotifyRepository = spotifyRepository;
    }

    public void importDisksByGenres() {

        if (discRepository.count() > 0) {
            System.out.println("Already imported discs");
            return;
        }

        genreRepository.findAll().forEach(genre -> {
            try {
                importGenre(genre);
            } catch (IOException | SpotifyWebApiException e) {
                e.printStackTrace();
            }
        });
    }

    private void importGenre(GenreEntity genre) throws IOException, SpotifyWebApiException {
        getFirstsItemsByGenre(genre).parallelStream().forEach(track -> saveDisc(track, genre));
    }

    private List<Track> getFirstsItemsByGenre(GenreEntity genre) throws IOException, SpotifyWebApiException {
        return Arrays.asList(spotifyRepository.findTrackByGenre(genre.getName()).getItems());
    }

    private void saveDisc(Track track, GenreEntity genreEntity) {
        DiscEntity discEntity = convertToDisc(track, genreEntity);
        discRepository.save(discEntity);
    }

    private DiscEntity convertToDisc(Track track, GenreEntity genreEntity) {
        DiscEntity discEntity = trackToDiscEntity(track);
        discEntity.setGenreEntity(genreEntity);
        discEntity.setPrice(PriceUtil.generateRandomPriceDisc());
        return discEntity;
    }


}
