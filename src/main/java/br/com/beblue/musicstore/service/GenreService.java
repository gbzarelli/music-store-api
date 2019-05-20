package br.com.beblue.musicstore.service;

import br.com.beblue.musicstore.dto.GenreDTO;
import br.com.beblue.musicstore.model.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> getAllGenres() {
        List<GenreDTO> list = new ArrayList<>();
        genreRepository.findAll().forEach(genreEntity -> list.add(new GenreDTO(genreEntity.getName())));
        return list;
    }
}
