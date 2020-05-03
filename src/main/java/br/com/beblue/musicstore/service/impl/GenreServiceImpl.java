package br.com.beblue.musicstore.service.impl;

import br.com.beblue.musicstore.controller.dto.GenreDTO;
import br.com.beblue.musicstore.model.repository.GenreRepository;
import br.com.beblue.musicstore.service.GenreService;
import br.com.beblue.musicstore.util.mapper.GenreMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@Autowired))
class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public List<GenreDTO> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), true)
                .map(GenreMapper::genreEntityToGenreDTO)
                .collect(Collectors.toList());
    }
}
