package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.controller.dto.GenreDTO;
import br.com.beblue.musicstore.model.repository.GenreRepository;
import br.com.beblue.musicstore.service.GenreService;
import br.com.beblue.musicstore.util.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    GenreServiceImpl(final GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> getAllGenres() {
        List<GenreDTO> list = new ArrayList<>();
        genreRepository.findAll().forEach(genreEntity -> list.add(GenreMapper.genreEntityToGenreDTO(genreEntity)));
        return list;
    }
}
